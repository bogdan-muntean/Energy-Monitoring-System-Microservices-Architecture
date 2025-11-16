package com.example.Chat_Microservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String MESSAGE_QUEUE_PREFIX = "message_queue:";

    public void saveMessage(String sessionKey, String message) {
        String redisQueueKey = MESSAGE_QUEUE_PREFIX + sessionKey;
        redisTemplate.opsForList().leftPush(redisQueueKey, message);
    }

    public List<Object> getMessages(String sessionKey) {
        String redisQueueKey = MESSAGE_QUEUE_PREFIX + sessionKey;
        return redisTemplate.opsForList().range(redisQueueKey, 0, -1);
    }
}