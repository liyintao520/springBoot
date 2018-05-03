package com.rabbitmq.test.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者----发送消息
 * 【运行RoutingSendDirect.java发送消息】
 * Created by liyintao on 2018/5/3.
 */
public class RoutingSendDirect {

    private static final String EXCHANGE_NAME = "direct_logs";
    // 路由关键字
    private static final String[] routingKeys = new String[]{"info" ,"warning", "error"};

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//      声明交换器  类型是直连【direct】
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//      发送消息
        for(String severity :routingKeys){
            String message = "生产者发送的日志级别:" + severity;
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" 发送级别【" + severity + "】，发送内容【" + message + "】");
        }
        channel.close();
        connection.close();
    }
}
