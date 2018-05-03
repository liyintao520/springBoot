package com.rabbitmq.test.publish;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者2--负责接收日志2
 * 【logs】交换器和【fanout】分发类型
 * Created by liyintao on 2018/5/3.
 */
public class ReceiveLogs2 {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // fanout--分发类型
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println("ReceiveLogs2--接收日志1 [*] 等待消息。要退出，请按CTRL + C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" ReceiveLogs2 收到 【" + message + "】");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
