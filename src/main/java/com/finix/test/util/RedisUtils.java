package com.finix.test.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private RedisService redisTemplate;

    public void set(final String key, final String value, final long expiredTimeSeconds) {
        ValueOperations valueOper = redisTemplate.boundValueOps(key);
        if (expiredTimeSeconds <= 0) {
            valueOper.set(value);
        } else {
            valueOper.set(value, expiredTimeSeconds, TimeUnit.SECONDS);
        }
    }

    public String get(final String key) {
        ValueOperations valueOper = redisTemplate.boundValueOps(key);
        return valueOper.get();
    }
}
