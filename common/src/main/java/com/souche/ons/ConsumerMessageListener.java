package com.souche.ons;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.souche.redis.RedisValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guishangquan
 * @Description 阿里云消息队列结合 spring
 * @Package com.souche.ons
 * @date 17/5/18
 **/
@Slf4j
public class ConsumerMessageListener implements MessageListener {

    @Autowired
    private RedisValueRepository redisValueRepository;

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        System.out.println("----------------------------------------");
        System.out.println("Spring receive: ");
        System.out.println("message = " + message);

        byte[] bodyByte = message.getBody();
        String bodyStr = new String(bodyByte);
        System.out.println("message.getBody() = " + bodyStr);
        redisValueRepository.set("ons", bodyStr);
        return Action.CommitMessage;
    }
}
