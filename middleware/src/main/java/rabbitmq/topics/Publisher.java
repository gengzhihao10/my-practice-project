package rabbitmq.topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import rabbitmq.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static final String EXCHANGE_NAME = "topic";
    private static final String QUEUE_NAME1 = "topic1";
    private static final String QUEUE_NAME2 = "topic2";

    @Test
    public void publish() throws IOException, TimeoutException {
        //1.获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //2.构建channel
        Channel channel = connection.createChannel();
        //3.构建交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //4.构建队列
        channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
        //5.绑定交换机和队列
        //TOPIC类型的交换机，在和队列绑定时，需要以aaa.bbb.ccc的方式编写routingkey
        //其中有2个特殊字符，
        // *，相当于占位符
        // #，相当于通配符
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"*.orange.*");
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"lazy.#");
        //6. 发消息到交换机
        channel.basicPublish(EXCHANGE_NAME,"big.orange.rabbit",null,"橙兔!".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"small.white.rabbit",null,"白兔!".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"lazy.dog.dog",null,"懒狗!".getBytes());
        System.out.println("消息成功发送");
    }
}
