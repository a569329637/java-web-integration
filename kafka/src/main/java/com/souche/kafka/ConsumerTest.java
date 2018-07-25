package com.souche.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * @author guishangquan
 * @date 2018/7/25
 */
public class ConsumerTest implements Runnable {

    private KafkaStream m_stream;
    private int m_threadNumber;

    public ConsumerTest(KafkaStream m_stream, int m_threadNumber) {
        this.m_stream = m_stream;
        this.m_threadNumber = m_threadNumber;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> iterator = m_stream.iterator();
        while (iterator.hasNext()) {
            System.out.println("Thread " + m_threadNumber + ": " + new String(iterator.next().message()));
        }
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}
