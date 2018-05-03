package com.rabbitmq.test.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 【生产者程序，他负责发送日志消息】
 *  先启动接收者1 2 在启动生产者
 *
 *  【logs】交换器和【fanout】分发类型
 * Created by liyintao on 2018/5/3.
 */
public class EmitLog {

    private static final String QUEUE_NAME = ""; // 队列名称
    private static final String EXCHANGE_NAME = "logs"; // 交换器名字

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // fanout--分发类型

        //      分发消息
        for(int i = 0 ; i < 5; i++){
            String message = "Hello World! " + i;
            // 发送到一个名为【logs】的交换器中。我们提供一个空字符串的routingkey，它的功能被交换器的分发类型代替了
            // 第二个参数是【routingKey】路由线索  匿名交换器规则：发送到routingKey名称对应的队列。
            // TODO QUEUE_NAME为空，叫临时队列 一旦我们断开消费者，队列应该立即被删除
            channel.basicPublish(EXCHANGE_NAME, QUEUE_NAME, null, message.getBytes());
            System.out.println(" EmitLog负责发送日志消息 【" + message + "】");
        }
        channel.close();
        connection.close();
    }
}
