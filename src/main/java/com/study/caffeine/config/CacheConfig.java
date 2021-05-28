package com.study.caffeine.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * email markcwg7@gmail.com
 *
 * @author markcwg
 * @date 2021/5/12 16:50
 */
@Configuration
public class CacheConfig {
    @Bean
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
                //设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(60, TimeUnit.SECONDS)
                //出事的缓存空间大小
                .initialCapacity(100)
                //缓存的最大条数
                .maximumSize(1000)
                .build();
    }

    @Bean("caffeineCacheManager")
    public CacheManager cacheManager () {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder()
                            .expireAfterWrite(60, TimeUnit.SECONDS)
                            .initialCapacity(100)
                            .maximumSize(1000)
        );
        return manager;
    }
}
