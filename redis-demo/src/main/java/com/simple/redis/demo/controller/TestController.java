package com.simple.redis.demo.controller;

import net.oschina.j2cache.cache.support.J2CacheCacheManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController{

    @Autowired
    private J2CacheCacheManger j2CacheCacheManger;

    @GetMapping("/getCache")
    public Object getCache() throws IOException {

        String cacheName = "ddd";

        Cache cache = j2CacheCacheManger.getCache(cacheName);

        String key = "dddd";
        cache.put(key, "你好j2cache"); // 存 null
        cache.put(key + "1", "你好j2cache"); // 存 null
        cache.put(key + "2", "你好j2cache"); // 存 null
        cache.put(key + "3", "你好j2cache"); // 存 null




        Cache.ValueWrapper valueWrapper = cache.get(key);
        return valueWrapper== null ? "" : valueWrapper.get();
    }

}
