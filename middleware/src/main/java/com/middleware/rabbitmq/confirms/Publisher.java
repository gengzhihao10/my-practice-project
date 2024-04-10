package com.middleware.rabbitmq.confirms;

import com.middleware.rabbitmq.RabbitMQConnectionUtil;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static final String QUEUE_NAME = "confirms";

    @Test
    public void publish() throws IOException, TimeoutException {
        //1. 获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2. 构建channel
        Channel channel = connection.createChannel();
        //3. 构建队列
        //durable为true只能保证rabbit重启后队列还在，但是消息没了。
        channel.queueDeclare(QUEUE_NAME, true,false,false,null);

        //4.开启confirms
        channel.confirmSelect();

        //5. 设置confirms的异步回调
        //5.1 消息发送到交换机的异步回调
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息成功发送到交换机");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息没有发送到交换机，尝试重试或者保存到数据库等补偿操作");
            }
        });

        //5.2 消息没有路由到了队列，会执行回调
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息没有路由到指定队列，做补偿措施");
            }
        });
        //6.设置消息持久化
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
                .builder()
                .deliveryMode(2)
                .build();

        //7. 发布消息
        String message = "hello world";
        //mandatory为true，开启return回调机制
        channel.basicPublish("",QUEUE_NAME,true,basicProperties,message.getBytes());
//        System.out.println("消息发送结束");
        System.in.read();
    }
}
