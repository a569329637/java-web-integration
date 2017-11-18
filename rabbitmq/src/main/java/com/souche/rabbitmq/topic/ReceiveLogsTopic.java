package com.souche.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/18.
 * 使用主题进行消息分发
 */
public class ReceiveLogsTopic {

    private final static String TOPIC_EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        if (args.length < 1) {
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            System.exit(1);
        }

        // 通过exchange_name和route_name一起来进行绑定
        for(String routingKey : args){
            // "#" "kern.*" "*.critical" "kern.*"
            channel.queueBind(queueName, TOPIC_EXCHANGE_NAME, routingKey);
        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 接受exchange_name为"topic_logs",routing_name为"#" "kern.*" "*.critical" "kern.*"
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
