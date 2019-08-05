package com.simple.redis.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisHashCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean(name="stringObjectRedisTemplate")
    public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());
        return template;
    }

    @Autowired
    @Qualifier("stringObjectRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        RedisCallback<?> action = (RedisCallback<Object>) connection -> {
            RedisHashCommands redisHashCommands = connection.hashCommands();
            String key = "ACCOUNT_PREFIX + accountId";
            redisHashCommands.hSet(key.getBytes(), "accessToken".getBytes(), "authToken.getAccessToken()".getBytes());
            redisHashCommands.hSet(key.getBytes(), "expiresIn".getBytes(), "authToken.getExpiresIn()".getBytes());
            redisHashCommands.hSet(key.getBytes(), "refreshToken".getBytes(), "authToken.getRefreshToken()".getBytes());

            redisHashCommands.hSet(key.getBytes(), "tokenUtime".getBytes(), "DateFormatUtils.format(new Date(), JodaTimeUtil.YYYYMMDDHHMMSS)".getBytes());
            return null;
        };

        List<Object> objects = redisTemplate.executePipelined(action);
        System.out.println(objects);
    }
}
