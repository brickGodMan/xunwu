package com.qcy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisSessionConfig
 * @Description session会话
 * @Author qiancy
 * @Date 2018/12/25 17:46
 * @Version 1.0
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400)
public class RedisSessionConfig {


    @Bean
    public JedisCluster jedisCluster() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(RedisConfig.POOL_MAX_ACTIVE);
        config.setMaxIdle(RedisConfig.POOL_MAX_IDLE);
        config.setMaxWaitMillis(RedisConfig.POOL_MAX_WAIT);
        config.setMinIdle(RedisConfig.POOL_MIN_IDLE);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        JedisCluster cluster = new JedisCluster(RedisClusterConfig.NODES, config);
        return cluster;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }
}
