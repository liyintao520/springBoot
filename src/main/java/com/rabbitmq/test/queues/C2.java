package com.rabbitmq.test.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 工人（消费者C1和C2）C2.java
 * Created by liyintao on 2018/5/3.
 */
public class C2 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("工人--消费者2 [*] 等待消息。要退出，请按CTRL + C");
        // 每次从队列中获取数量
        channel.basicQos(1);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.print("工人--消费者2 [x] 收到 【" + message + "】");
                try {
                    doWork(message);
                } finally {
                    System.out.println(", 并且完成！");
                    // 消息处理完成确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        // 消息消费完成确认
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
    }

    /**
     * 任务处理
     * @param task
     */
    private static void doWork(String task) {
        try {
            Thread.sleep(5000); // 暂停1秒钟
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}