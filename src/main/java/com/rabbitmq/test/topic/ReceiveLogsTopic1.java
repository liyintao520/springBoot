package com.rabbitmq.test.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者1--消费信息
 * 先运行ReceiveLogsTopic1.java
 * 再运行ReceiveLogsTopic2
 * 然后运行TopicSend.java发送7条消息
 * Created by liyintao on 2018/5/3.
 */
public class ReceiveLogsTopic1 {


    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //创建一个通道
//      声明一个匹配模式的交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        // 路由关键字
        String[] routingKeys = new String[]{"*.orange.*"};
        // 在匹配交互器模式下，消费者端路由关键字 “*” 能接收到生产者端发来路由关键字为空的消息吗？
//        String[] routingKeys = new String[]{"*"}; // TODO 什么都接受不到了！！！
//      在匹配交换器模式下，消费者端路由关键字“#.*”能接收到生产者端以“..”为关键字的消息吗？
//      如果发送来的消息只有一个单词，能匹配上吗？
//        String[] routingKeys = new String[]{"#.*"}; // TODO 全都接受了...
        // 对比 消费者二的a.# 有什么不同
//        String[] routingKeys = new String[]{"a.*.#"}; // TODO 效果一样，全部取不到值

        System.out.println("【消费者1】--------开始绑定路由关键字。。。。。。。。。");
//      绑定路由关键字
        for (String bindingKey : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            System.out.println("ReceiveLogsTopic1 exchange:【"+EXCHANGE_NAME+"】, queue:【"+queueName+"】, BindRoutingKey:【" + bindingKey + "】");
        }

        System.err.println("【消费者1】 [*] 等待消息。要退出，请按CTRL + C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("【ReceiveLogsTopic1】 收到--路由关键字为：【" + envelope.getRoutingKey() + "】，内容：【" + message + "】");
            }
        };
        System.out.println("接受总消息数：" + channel.getChannelNumber());
        channel.basicConsume(queueName, true, consumer);
    }
}
