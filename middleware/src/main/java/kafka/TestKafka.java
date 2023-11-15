package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

public class TestKafka {

    String topic = "items-01";

//    @Before
//    public void setUp() {
//        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//        logger.isInfoEnabled();
//    }

    @Test
    public void producer() throws ExecutionException, InterruptedException {
//        String topic = "msb-items";
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.184.129:9092,192.168.184.130:9092,192.168.184.131:9092");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(p);
        while(true){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j <3; j++) {
                    ProducerRecord<String, String> record = new ProducerRecord<>(topic, "item"+j,"val" + i);
                    Future<RecordMetadata> send = producer
                            .send(record);

                    RecordMetadata rm = send.get();
                    int partition = rm.partition();
                    long offset = rm.offset();
                    System.out.println("key: "+ record.key()+" val: "+record.value()+" partition: "+partition + " offset: "+offset);

                }
            }
        }
    }





    @Test
    public void consumer(){
        /*
        kafka-consumer-groups.sh --bootstrap-server node02:9092  --list
         */

        //基础配置
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.184.129:9092,192.168.184.130:9092,192.168.184.131:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //消费的细节
        String group = "user-center";
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,group);
        //KAKFA IS MQ  IS STORAGE
        /**
         *         "What to do when there is no initial offset in Kafka or if the current offset
         *         does not exist any more on the server
         *         (e.g. because that data has been deleted):
         *         <ul>
         *             <li>earliest: automatically reset the offset to the earliest offset
         *             <li>latest: automatically reset the offset to the latest offset</li>
         *             <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li>
         *         </ul>";
         */
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");//第一次启动，米有offset
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");//自动提交时异步提交，丢数据&&重复数据
//        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"15000");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"100");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(0));
            if (!records.isEmpty()){
                System.out.println();
                System.out.println("-----------------" + records.count() + "------------------------------");

//                Iterator<ConsumerRecord<String,String>> iterator = records.iterator();
//                while (iterator.hasNext()){
//                    ConsumerRecord<String,String> record = iterator.next();
//                    int partition = record.partition();
//                    long offset = record.offset();
//                    System.out.println("key: " + record.key() + " val: " + record.value() + " partition: " + partition + " offset: " + offset);
//                }
                Set<TopicPartition> partitions = records.partitions();
                System.out.println("partition的数量为 " + partitions.size());
                for (TopicPartition partition : partitions){
                    List<ConsumerRecord<String,String>> pRecords = records.records(partition);

                    Iterator<ConsumerRecord<String,String>> pIterator = pRecords.iterator();
                    while (pIterator.hasNext()){
                        ConsumerRecord<String,String> next = pIterator.next();
                        int p = next.partition();
                        long offset = next.offset();
                        String key = next.key();
                        String value = next.value();
                        System.out.println("key: " + key + " val: " + value + " partition: " + p + " offset: " + offset);

                        TopicPartition sp = new TopicPartition(topic,p);
                        OffsetAndMetadata om = new OffsetAndMetadata(offset);
                        HashMap<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                        map.put(sp,om);
                        consumer.commitSync(map);
                    }
                    //按partition提交
                    long offset = pRecords.get(pRecords.size() - 1).offset();
                    OffsetAndMetadata om = new OffsetAndMetadata(offset);
                    HashMap<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                    map.put(partition,om);
//                    consumer.commitSync();
                }
                //按批次提交
//                consumer.commitSync();
            }
        }

        //kafka 的consumer会动态负载均衡
//        consumer.subscribe(Arrays.asList("msb-items"), new ConsumerRebalanceListener() {
//            @Override
//            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
//                System.out.println("---onPartitionsRevoked:");
//                Iterator<TopicPartition> iter = partitions.iterator();
//                while(iter.hasNext()){
//                    System.out.println(iter.next().partition());
//                }
//
//            }
//
//            @Override
//            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//                System.out.println("---onPartitionsAssigned:");
//                Iterator<TopicPartition> iter = partitions.iterator();
//
//                while(iter.hasNext()){
//                    System.out.println(iter.next().partition());
//                }
//
//
//            }
//        });
    }


    //按条提交，手动同步提交
    @Test
    public void test1(){

        //基础配置
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.184.129:9092,192.168.184.130:9092,192.168.184.131:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //消费的细节
        String group = "user-center";
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,group);
        //KAKFA IS MQ  IS STORAGE
        /**
         *         "What to do when there is no initial offset in Kafka or if the current offset
         *         does not exist any more on the server
         *         (e.g. because that data has been deleted):
         *         <ul>
         *             <li>earliest: automatically reset the offset to the earliest offset
         *             <li>latest: automatically reset the offset to the latest offset</li>
         *             <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li>
         *         </ul>";
         */
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");//第一次启动，米有offset

        //自动提交时异步提交，丢数据&&重复数据
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");

//        p.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"15000");
//        p.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"10");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(0));
            if (!records.isEmpty()){
                System.out.println();
                System.out.println("-----------------" + records.count() + "------------------------------");

                Iterator<ConsumerRecord<String,String>> iterator = records.iterator();
                while (iterator.hasNext()){
                    ConsumerRecord<String,String> next = iterator.next();
                    int p = next.partition();
                    long offset = next.offset();
                    String key = next.key();
                    String value = next.value();
                    System.out.println("key: " + key + " val: " + value + " partition: " + p + " offset: " + offset);

                    TopicPartition sp = new TopicPartition(topic,p);
                    OffsetAndMetadata om = new OffsetAndMetadata(offset);
                    HashMap<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                    map.put(sp,om);
                    consumer.commitSync(map);

                }
            }
        }
    }


    //按partition提交和按批次，手动同步提交
    @Test
    public void test2(){
        //基础配置
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.184.129:9092,192.168.184.130:9092,192.168.184.131:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //消费的细节
        String group = "user-center";
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,group);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");

        //自动提交时异步提交，丢数据&&重复数据
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");

//        p.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"15000");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"10");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(0));
            if (!records.isEmpty()){
                System.out.println();
                System.out.println("-----------------" + records.count() + "------------------------------");

                Set<TopicPartition> partitions = records.partitions();
                for (TopicPartition partition : partitions){
                    List<ConsumerRecord<String,String>> pRecords = records.records(partition);

                    Iterator<ConsumerRecord<String,String>> pIterator = pRecords.iterator();
                    while (pIterator.hasNext()){
                        ConsumerRecord<String,String> next = pIterator.next();
                        int p = next.partition();
                        long offset = next.offset();
                        String key = next.key();
                        String value = next.value();
                        System.out.println("key: " + key + " val: " + value + " partition: " + p + " offset: " + offset);
                    }
                    //按partition提交
                    long offset = pRecords.get(pRecords.size() - 1).offset();
                    OffsetAndMetadata om = new OffsetAndMetadata(offset);
                    HashMap<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                    map.put(partition,om);
                    consumer.commitSync(map);
                }
                //按批次提交
//                consumer.commitSync();
            }
        }
    }


    //动态负载均衡
    @Test
    public void test3(){
        //基础配置
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.184.129:9092,192.168.184.130:9092,192.168.184.131:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //消费的细节
        String group = "user-center";
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,group);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");

        //自动提交时异步提交，丢数据&&重复数据
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");

        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"15000");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"10");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //kafka 的consumer会动态负载均衡
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            //Revoked，取消的回调函数
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.println("---onPartitionsRevoked:");
                Iterator<TopicPartition> iter = partitions.iterator();
                while(iter.hasNext()){
                    System.out.println(iter.next().partition());
                }
                System.out.println();
            }

            //Assigned 指定的回调函数
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.println("---onPartitionsAssigned:");
                Iterator<TopicPartition> iter = partitions.iterator();

                while(iter.hasNext()){
                    System.out.println(iter.next().partition());
                }
                System.out.println();
            }
        });

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(0));
            if (!records.isEmpty()){
                System.out.println();
                System.out.println("-----------------" + records.count() + "------------------------------");

                Iterator<ConsumerRecord<String,String>> iterator = records.iterator();
                while (iterator.hasNext()){
                    ConsumerRecord<String,String> next = iterator.next();
                    int p = next.partition();
                    long offset = next.offset();
                    String key = next.key();
                    String value = next.value();
                    System.out.println("key: " + key + " val: " + value + " partition: " + p + " offset: " + offset);
                    TopicPartition sp = new TopicPartition(topic,p);
                    OffsetAndMetadata om = new OffsetAndMetadata(offset);
                    HashMap<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                    map.put(sp,om);
                    consumer.commitSync(map);
                }
            }
        }
    }


}
