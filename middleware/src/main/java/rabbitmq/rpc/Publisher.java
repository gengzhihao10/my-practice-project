package rabbitmq.rpc;

import com.rabbitmq.client.*;
import org.junit.Test;
import rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static final String QUEUE_PUBLISHER = "rpc_publisher";
    public static final String QUEUE_CONSUMER = "rpc_consumer";

    @Test
    public void publish() throws IOException, TimeoutException {
        //1. 获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2. 构建channel
        Channel channel = connection.createChannel();
        //3. 构建队列
        channel.queueDeclare(QUEUE_PUBLISHER, false,false,false,null);
        channel.queueDeclare(QUEUE_CONSUMER, false,false,false,null);
        //4. 发布消息
        String message = "hello rpc";
        String uuid = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties()
                .builder()
                .replyTo(QUEUE_CONSUMER)
                .correlationId(uuid)
                .build();
        channel.basicPublish("",QUEUE_PUBLISHER,props,message.getBytes());
        System.out.println("消息发送结束");
        //消费消息
        channel.basicConsume(QUEUE_CONSUMER,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String id = properties.getCorrelationId();
                if (id != null && id.equalsIgnoreCase(uuid)){
                    System.out.println("接受到服务端响应：" + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        });
        System.in.read();
    }
}
