package com.zr.service.impl;

import com.zr.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServerImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * set redis: string类型
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void setString(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * get redis: string类型
     *
     * @param key key
     * @return value
     */
    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
