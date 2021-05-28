package com.study.caffeine.service;

import com.study.caffeine.domain.UserInfo;
import org.springframework.stereotype.Service;

/**
 * email markcwg7@gmail.com
 *
 * @author markcwg
 * @date 2021/5/12 16:53
 */
public interface UserService {
    void addUserInfo (UserInfo userInfo);
    UserInfo getById(Integer id);
    UserInfo updateUserInfo(UserInfo userInfo);
    void deleteById(Integer id);
}
