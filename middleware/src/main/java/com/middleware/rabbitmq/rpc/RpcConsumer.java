package com.middleware.rabbitmq.rpc;

import com.rabbitmq.client.*;
import org.junit.Test;
import com.middleware.rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RpcConsumer {

    public static final String QUEUE_PUBLISHER = "rpc_publisher";

    @Test
    public void consumer() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_PUBLISHER, false,false,false,null);

        DefaultConsumer callback = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接受消息：" + new String(body));
                String res = "获取到了client发出的请求，返回响应";
                String resQueueName = properties.getReplyTo();
                String uuid = properties.getCorrelationId();
                AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
                        .builder()
                        .correlationId(uuid)
                        .build();
                channel.basicPublish("",resQueueName,basicProperties,res.getBytes());
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_PUBLISHER,false,callback);
        System.in.read();
    }
}
