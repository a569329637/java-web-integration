package com.souche.rabbitmq.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/17.
 * 生产者创建消息持久化队列task_queue，并且直接发送消息到队列task_queue
 */
public class NewTask {
    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 首先创建connection和channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 然后创建queue，生产者和消费者都要创建，虽然只要一个能够创建成功
        // 第一个true表示消息持久化
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        // 发送消息message
        String message = getMessage(args);
        // 消息的类型是 "text/plain"
        channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        // 关闭连接
        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "Hello World!...";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(strings[0]);
        for (int i = 1; i < length; ++ i) {
            stringBuilder.append(delimiter).append(strings[i]);
        }
        return stringBuilder.toString();
    }
}
