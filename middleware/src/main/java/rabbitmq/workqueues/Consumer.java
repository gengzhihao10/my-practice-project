package rabbitmq.workqueues;

import com.rabbitmq.client.*;
import org.junit.Test;
import rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    @Test
    public void consumer1() throws IOException, TimeoutException {
        //1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2. 构建channel
        Channel channel = connection.createChannel();
        //3.构建队列
        channel.queueDeclare(rabbitmq.helloworld.Publisher.QUEUE_NAME,false,false,false,null);
        //设置消息的流控
        channel.basicQos(1);
        //4.监听消息
        DefaultConsumer callback = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1号消费者接受消息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(Publisher.QUEUE_NAME,false,callback);
        System.in.read();
    }


    @Test
    public void consumer2() throws IOException, TimeoutException {
        //1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2. 构建channel
        Channel channel = connection.createChannel();
        //3.构建队列
        channel.queueDeclare(rabbitmq.helloworld.Publisher.QUEUE_NAME,false,false,false,null);
        //设置消息的流控
        channel.basicQos(1);
        //4.监听消息
        DefaultConsumer callback = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2号消费者接受消息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(Publisher.QUEUE_NAME,false,callback);
        System.in.read();
    }
}
