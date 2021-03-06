package com.rabbitmq.test.routing;

/**
 * 消费者1-- 消费消息
 * 根据路由关键字进行多重绑定--接受绑定的路由类型
 * 先运行【ReceiveLogsDirect1 和 ReceiveLogsDirect2】
 * Created by liyintao on 2018/5/3.
 */

import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsDirect1 {
    // 交换器名称
    private static final String EXCHANGE_NAME = "direct_logs";
    // 路由关键字-- 关注类型有"info", "warning", "error"
    private static final String[] routingKeys = new String[]{"info", "warning", "error"};

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//      声明交换器  类型是直连【direct】
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//      获取匿名队列名称
        String queueName = channel.queueDeclare().getQueue();
//      根据路由关键字进行多重绑定--接受绑定的路由类型
        for (String severity : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, severity);
            System.out.println("ReceiveLogsDirect1 exchange:" + EXCHANGE_NAME + ", queue:" + queueName + ", BindRoutingKey:" + severity);
        }
        System.out.println("ReceiveLogsDirect1--消费者1 [*] 等待消息。要退出，请按CTRL + C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] 收到【" + envelope.getRoutingKey() + "】，内容--【" + message + "】");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
