package com.company.project.configurer.token;


import com.company.project.core.ProjectConstant;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.api.model.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: gre_yu@163.com
 * @Date: Created in 8:26 2018/2/1
 */
@Component
public class RedisTokenManager implements TokenManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisTemplate<String, String> redis;

    @Autowired
    @Qualifier("redisTemplate")
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
//        泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel createToken(String userId) {
//        使用UUID作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
//        存储到redis并设置过期时间
        redis.boundValueOps(userId).set(token, ProjectConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
//        使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
//        如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUserId()).expire(ProjectConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public void deleteToken(String userId) {
        if (userId == null) {
            throw new ServiceException("userId为空");
        } else {
            redis.delete(userId);
            logger.info("userId为：" + userId + "的token被清除");
        }

    }
}
