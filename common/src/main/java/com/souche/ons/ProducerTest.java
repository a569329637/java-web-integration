package com.souche.ons;

import com.aliyun.openservices.ons.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
 * @author guishangquan
 * @Description 阿里云消息队列生产者
 * @Package com.souche.ons
 * @date 17/5/18
 **/
public class ProducerTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, "CID_A569329637_TEST_1");
        properties.put(PropertyKeyConst.AccessKey, "LTAIRhFJFFpFY2UF");
        properties.put(PropertyKeyConst.SecretKey, "klh1wC03YzJhNtDFLYz548NlN6jFbB");

        Producer producer = ONSFactory.createProducer(properties);
        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可
        producer.start();
        {
            User user = new User();
            user.setUsername("a569329637");
            user.setPassword("123456");
            ByteArrayOutputStream obj = new ByteArrayOutputStream();
            byte[] bytes = null;
            try {
                ObjectOutputStream out = new ObjectOutputStream(obj);
                out.writeObject(user);
                bytes = obj.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Message msg = new Message( //
                    // 在控制台创建的Topic，即该消息所属的Topic名称
                    "a569329637_test",
                    // Message Tag,
                    // 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                    "TagC",
                    // Message Body
                    // 任何二进制形式的数据， MQ不做任何干预，
                    // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                    bytes);
            // 设置代表消息的业务关键属性，请尽可能全局唯一，以方便您在无法正常收到消息情况下，可通过MQ控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("ORDERID_100");
            // 发送消息，只要不抛异常就是成功
            // 打印Message ID，以便用于消息发送状态查询
            SendResult sendResult = producer.send(msg);
            System.out.println("Send Message success. Message ID is: " + sendResult.getMessageId());
        }
        // 在应用退出前，可以销毁Producer对象
        // 注意：如果不销毁也没有问题
        // producer.shutdown();
    }
}
