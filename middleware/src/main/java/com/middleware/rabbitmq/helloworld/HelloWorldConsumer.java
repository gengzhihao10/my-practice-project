package com.middleware.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import com.middleware.rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class HelloWorldConsumer {

    @Test
    public void consumer() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(HelloWorldPublisher.QUEUE_NAME,false,false,false,null);
        DefaultConsumer callback = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接受消息：" + new String(body));
            }
        };
        channel.basicConsume(HelloWorldPublisher.QUEUE_NAME,true,callback);
        System.in.read();
    }
}
