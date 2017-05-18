package com.souche.ons;

import com.aliyun.openservices.ons.api.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Properties;

/**
 * @author guishangquan
 * @Description 阿里云消息队列消费者
 * @Package com.souche.ons
 * @date 17/5/18
 **/
public class ConsumerTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, "CID_A569329637_TEST_1");
        properties.put(PropertyKeyConst.AccessKey, "LTAIRhFJFFpFY2UF");
        properties.put(PropertyKeyConst.SecretKey, "klh1wC03YzJhNtDFLYz548NlN6jFbB");

        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("a569329637_test", "TagC", new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext consumeContext) {
                System.out.println("receive: ");
                System.out.println("message = " + message);
                byte[] bodyByte = message.getBody();
//                String bodyStr = new String(bodyByte);
//                System.out.println("message.getBody() = " + bodyStr);

                ByteArrayInputStream bin = new ByteArrayInputStream((byte[])bodyByte);
                try {
                    ObjectInputStream obin = new ObjectInputStream(bin);
                    User user = (User) obin.readObject();
                    System.out.println("user = " + user);
                    System.out.println("user.getUsername() = " + user.getUsername());
                    System.out.println("user.getPassword() = " + user.getPassword());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Action.CommitMessage;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
    }
}
