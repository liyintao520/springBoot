package com.rabbitmq.test.topic;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
/**
 * 生产者--提供消息
 * 先运行ReceiveLogsTopic1.java
 * 再运行ReceiveLogsTopic2
 * 然后运行TopicSend.java发送7条消息
 * Created by liyintao on 2018/5/3.
 */
public class TopicSend {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setPort(5672);
            connection = factory.newConnection();
            channel = connection.createChannel();
//          声明一个匹配模式的交换器
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            // 待发送的消息
            String[] routingKeys = new String[]{"quick.orange.rabbit",
                    "lazy.orange.elephant",
                    "quick.orange.fox",
                    "lazy.brown.fox",
                    "quick.brown.fox",
                    "quick.orange.male.rabbit",
                    "lazy.orange.male.rabbit",
                    ""};
//          发送消息
            for(String severity :routingKeys){
                String message = "From "+severity+" routingKey' s message!";
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
                System.out.println("生产者发送消息，routingKeys关键字 = 【" + severity + "】，内容--【" + message + "】");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignore) {
                }
            }
        }
    }
}
