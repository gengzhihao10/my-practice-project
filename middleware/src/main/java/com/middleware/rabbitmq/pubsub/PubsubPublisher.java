package com.middleware.rabbitmq.pubsub;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import com.middleware.rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PubsubPublisher {

    public static final String EXCHANGE_NAME = "pubsub";
    private static final String QUEUE_NAME1 = "pubsub1";
    private static final String QUEUE_NAME2 = "pubsub2";

    @Test
    public void publish() throws IOException, TimeoutException {
        //1.获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2.构建channel
        Channel channel = connection.createChannel();
        //3.构建交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //4.构建队列
        channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
        //5.绑定交换机和队列
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"");
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"");
        //6. 发消息到交换机
        channel.basicPublish(EXCHANGE_NAME,"",null,"pub and sub!".getBytes());
        System.out.println("消息成功发送");
    }
}
