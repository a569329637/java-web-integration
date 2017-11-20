package com.souche.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/18.
 * topic模式（*可以表示一个单词，#可以表示一个或多个单词）
 * 消费者创建主题模式交换机topic_logs，创建队列queueName
 * 将队列queueName与交换机topic_logs和多个routing_key（该字符串包含*#）进行绑定
 * 消息就可以通过topic_logs和多个routing_key（该字符串包含*#）来路由到队列queueName
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
