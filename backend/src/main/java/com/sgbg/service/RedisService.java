package com.sgbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;

    public void saveToken(Long userId, String type, String token, Long expires) {
        String key = userId + ":" + type;
        redisTemplate.opsForValue().set(key, token);
        redisTemplate.expire(key, expires, TimeUnit.SECONDS);
    }

    public String getToken(Long userId, String type) {
        String key = userId + ":" + type;
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void updateToken(Long userId, String type, String token, Long expires) {
        if (getToken(userId, type) != null) {
            deleteToken(userId, type);
        }
        saveToken(userId, type, token, expires);
    }

    public void deleteToken(Long userId, String type) {
        String key = userId + ":" + type;
        if (getToken(userId, type) != null) {
            redisTemplate.delete(key);
        }
    }
}
