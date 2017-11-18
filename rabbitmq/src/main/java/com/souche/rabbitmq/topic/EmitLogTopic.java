package com.souche.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/18.
 */
public class EmitLogTopic {

    private final static String TOPIC_EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");

        String routingKey = getRouting(args);
        String message = getMessage(args);

        channel.basicPublish(TOPIC_EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        channel.close();
        connection.close();
    }

    private static String getRouting(String[] strings) {
        if (strings.length < 1) {
            return "kern.critical";
        }
        return strings[0];
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 2) {
            return "A critical kernel error";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0 || length == 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(strings[1]);
        for (int i = 2; i < length; ++ i) {
            stringBuilder.append(delimiter).append(strings[i]);
        }
        return stringBuilder.toString();
    }
}
