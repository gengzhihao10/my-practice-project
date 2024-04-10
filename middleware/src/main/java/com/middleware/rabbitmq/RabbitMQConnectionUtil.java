package com.middleware.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @author gengzhihao
 * @date 2024/3/14 15:25
 * @description
**/

public class RabbitMQConnectionUtil {

    public static final String RABBITMQ_HOST = "192.168.18.128";
    public static final int RABBITMQ_PORT = 5672;
    public static final String RABBITMQ_USERNAME = "guest";
    public static final String RABBITMQ_PASSWORD = "guest";
    public static final String RABBITMQ_VIRTUAL_HOST = "/";

    /*
     * @author gengzhihao
     * @date 2024/3/14 15:26
     * @description 构建rabbitmq的连接
     * @return Connection
     **/
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        factory.setPort(RABBITMQ_PORT);
        factory.setUsername(RABBITMQ_USERNAME);
        factory.setPassword(RABBITMQ_PASSWORD);
        factory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);
        return factory.newConnection();
    }
}
