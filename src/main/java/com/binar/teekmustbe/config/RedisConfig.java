package com.binar.teekmustbe.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

public class RedisConfig {
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1))
                .disableCachingNullValues().serializeValuesWith(RedisSerializationContext
                        .SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer cacheManagerBuilderCustomizer() {
        // TODO: Rename cache name
        return (builder) -> builder.withCacheConfiguration("CacheFilm",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }
}