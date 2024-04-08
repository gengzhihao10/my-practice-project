package rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static  final  String QUEUE_NAME = "work";

    @Test
    public void publish() throws IOException, TimeoutException {

        //1. 获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2. 构建channel
        Channel channel = connection.createChannel();
        //3. 构建队列
        channel.queueDeclare(QUEUE_NAME, false,false,false,null);
        //4. 发布消息
        for (int i = 0; i < 10; i++){
            String message = "hello world " + i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        }
        System.out.println("消息发送结束");
        System.in.read();
    }
}
