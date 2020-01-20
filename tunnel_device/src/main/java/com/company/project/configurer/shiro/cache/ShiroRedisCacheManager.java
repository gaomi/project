package com.company.project.configurer.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author Chen
 * @created 2019-08-25-21:18.
 */
public class ShiroRedisCacheManager implements CacheManager, Destroyable {
    private static Logger LOGGER = LoggerFactory.getLogger(ShiroRedisCacheManager.class);

    private JedisConnectionFactory jedisConnectionFactory;

    public ShiroRedisCacheManager(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        LOGGER.debug("shiro redis cache manager get cache. name={} ", name);
        return new ShiroRedisCache<>(name,jedisConnectionFactory);
    }

    @Override
    public void destroy() throws Exception {
        // TODO seer 2018/3/24 12:43 destory
    }
}
