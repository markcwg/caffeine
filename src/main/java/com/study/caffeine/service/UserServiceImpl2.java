package com.study.caffeine.service;

import com.study.caffeine.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * email markcwg7@gmail.com
 *
 * @author markcwg
 * @date 2021/5/12 16:55
 */
@Service("service2")
@Slf4j
@CacheConfig(cacheNames = "caffeineCacheManager")
public class UserServiceImpl2 implements UserService {
    //模拟数据库存储数据
    private Map<Integer, UserInfo> map = new HashMap<>();


    @Override
    @CachePut(key = "#userInfo.id")
    public void addUserInfo(UserInfo userInfo) {
        log.debug("create...");
        map.put(userInfo.getId(), userInfo);
    }

    @Override
    @Cacheable(key = "#id")
    public UserInfo getById(Integer id) {
        log.debug("get......");
        return map.get(id);
    }

    @Override
    @CachePut(key = "#userInfo.id")
    public UserInfo updateUserInfo(UserInfo userInfo) {
        log.debug("update......");
        if (!map.containsKey(userInfo.getId())) {
            return null;
        }
        //取旧的值
        UserInfo oldUserInfo = map.get(userInfo.getId());
        if (oldUserInfo.getAge() != null) {
            oldUserInfo.setAge(userInfo.getAge());
        }
        if (oldUserInfo.getName() != null) {
            oldUserInfo.setName(userInfo.getName());
        }
        if (oldUserInfo.getSex() != null) {
            oldUserInfo.setSex(userInfo.getSex());
        }
        //将新的对象存储，更新旧的值
        map.put(oldUserInfo.getId(), oldUserInfo);
        return oldUserInfo;
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteById(Integer id) {
        log.debug("delete......");
        map.remove(id);
    }
}
