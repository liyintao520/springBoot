package com.rabbitmq.test.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * 工厂任务安排者（生产者P）
 * Created by liyintao on 2018/5/3.
 */
public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws java.io.IOException, Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true; //TODO 需要持久化，当服务器挂掉，队列将在服务器重新启动后存活
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
//      分发消息
        //TODO basicQos(int)方法 告诉RabbitMQ，不要一次将多个消息发送给一个消费者。这样做的好处是只有当消费者处理完成当前消息并反馈后，才会收到另外一条消息或任务。这样就避免了负载不均衡的事情了。
        int prefetchCount = 1;
        for (int i = 0; i < 500; i++) {
            String message = "Hello World! " + i;
            channel.basicQos(prefetchCount);
            //TODO  RabbitMQ选择了一个空“”字符串的默认交换器
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" 启动工厂任务安排者（生产者）：【" + message + "】");

        }
        channel.close();
        connection.close();
    }
}