package net.engineeringdigest.journalApp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // Store JSON as String

    @Autowired
    private ObjectMapper objectMapper;

    // ---------- GET ----------
    public <T> T get(String key, Class<T> entityClass) {

        try {
            String json = redisTemplate.opsForValue().get(key);

            if (json == null) return null; // no cache stored

            return objectMapper.readValue(json, entityClass);

        } catch (Exception e) {
            log.error("Redis Get Error: ", e);
            return null;
        }
    }

    // ---------- SET ----------
    public void set(String key, Object value, Long ttlSeconds) {

        try {
            String json = objectMapper.writeValueAsString(value);

            redisTemplate.opsForValue().set(key, json, ttlSeconds, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Redis Set Error: ", e);
        }
    }
}