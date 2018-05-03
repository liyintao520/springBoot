package com.rabbitmq.test.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者2--消费信息
 * 先运行ReceiveLogsTopic1.java
 * 再运行ReceiveLogsTopic2
 * 然后运行TopicSend.java发送7条消息
 * Created by liyintao on 2018/5/3.
 */
public class ReceiveLogsTopic2 {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//      声明一个匹配模式的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        // 路由关键字
        String[] routingKeys = new String[]{"*.*.rabbit", "lazy.#"};
        // 对比 消费者一的a.*.#有什么不同
//        String[] routingKeys = new String[]{"a.#"};

        System.out.println("【消费者2】--------开始绑定路由关键字。。。。。。。。。");
//      绑定路由关键字
        for (String bindingKey : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            System.out.println("ReceiveLogsTopic2 exchange:【"+EXCHANGE_NAME+"】, queue:【"+queueName+"】, BindRoutingKey:【" + bindingKey + "】");
        }

        System.err.println("【消费者2】 [*] 等待消息。要退出，请按CTRL + C");
        Consumer consumer = new DefaultConsumer(channel) {
            int count = 0;
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                count++;
                System.out.println("【ReceiveLogsTopic2】 收到--路由关键字为：【" + envelope.getRoutingKey() + "】，内容：【" + message + "】");
                System.out.println("接受总消息数：" + count);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
