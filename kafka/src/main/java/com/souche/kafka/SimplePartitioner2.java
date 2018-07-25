package com.souche.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * 通过 key 来选择分区
 * @author guishangquan
 * @date 2018/7/25
 */
public class SimplePartitioner2 implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {
        int partition = 0;
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        System.out.println("分区的数量 = " + numPartitions);
        String stringKey = (String) key;
        System.out.println("stringKey = " + stringKey);
        int offset = stringKey.lastIndexOf('.');
        if (offset > 0) {
            partition = Integer.parseInt( stringKey.substring(offset+1)) % numPartitions;
        }
        System.out.println("partition = " + partition);
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
