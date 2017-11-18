package com.souche.rabbitmq.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/18.
 */
public class RPCServer {

    private final static String RPC_QUEUE = "rpc_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RPC_QUEUE, false, false, false, null);
        channel.basicQos(1);
        System.out.println(" [x] Awaiting RPC requests");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 打开应答机制
        channel.basicConsume(RPC_QUEUE, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            AMQP.BasicProperties properties = delivery.getProperties();
            AMQP.BasicProperties replyProperties = new AMQP.BasicProperties()
                    .builder()
                    .correlationId(properties.getCorrelationId())
                    .build();
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [.] fib(" + message + ")");
            System.out.println("properties.getReplyTo() = " + properties.getReplyTo());
            System.out.println("properties.getCorrelationId() = " + properties.getCorrelationId());
            int response = fib(Integer.valueOf(message));
            // 返回出来结果
            channel.basicPublish("", properties.getReplyTo(), replyProperties, ("" + response).getBytes("UTF-8"));
            // 返回应答ack
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
