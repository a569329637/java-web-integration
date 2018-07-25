package com.souche.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @author guishangquan
 * @date 2018/7/25
 */
public class HelloWorldProducer2 {

    public static void main(String[] args) {
        long events = 11L;
        Random rnd = new Random();

        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //配置 partitionner 选择策略，可选配置
        props.put("partitioner.class", "com.souche.kafka.SimplePartitioner2");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            ProducerRecord<String, String> data = new ProducerRecord<>("my-replicated-topic", ip, msg);
            producer.send(data,
                    new Callback() {
                        public void onCompletion(RecordMetadata metadata, Exception e) {
                            if(e != null) {
                                e.printStackTrace();
                            } else {
                                // 返回分区的偏移量
                                System.out.println("The offset of the record we just sent is: " + metadata.offset());
                            }
                        }
                    });
        }
        producer.close();
    }
}
