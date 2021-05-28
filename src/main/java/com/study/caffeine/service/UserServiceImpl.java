package com.study.caffeine.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.study.caffeine.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * email markcwg7@gmail.com
 *
 * @author markcwg
 * @date 2021/5/12 16:55
 */
@Service("service1")
@Slf4j
public class UserServiceImpl implements UserService {
    //模拟数据库存储数据
    private Map<Integer, UserInfo> map = new HashMap<>();

    @Autowired
    Cache<String, Object> cache;

    @Override
    public void addUserInfo(UserInfo userInfo) {
        log.debug("create...");
        map.put(userInfo.getId(), userInfo);
        cache.put(String.valueOf(userInfo.getId()), userInfo);
    }

    @Override
    public UserInfo getById(Integer id) {
        cache.getIfPresent(id);
        UserInfo userInfo = (UserInfo) cache.asMap().get(String.valueOf(id));
        if (userInfo != null) {
            return userInfo;
        }
        log.debug("get......");
        try {
            log.debug("getting data, need 3s ......");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo = map.get(id);
        if (userInfo != null) {
            cache.put(String.valueOf(userInfo.getId()),userInfo);
        }
        return userInfo;
    }

    @Override
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
        //存入缓存
        cache.put(String.valueOf(oldUserInfo.getId()), oldUserInfo);
        return oldUserInfo;
    }

    @Override
    public void deleteById(Integer id) {
        log.debug("delete......");
        map.remove(id);
        cache.asMap().remove(String.valueOf(id));
    }
}
