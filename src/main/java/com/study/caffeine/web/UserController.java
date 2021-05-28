package com.study.caffeine.web;

import com.study.caffeine.domain.UserInfo;
import com.study.caffeine.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * email markcwg7@gmail.com
 *
 * @author markcwg
 * @date 2021/5/12 17:12
 */
@RestController
@RequestMapping("/v1/user")
@Api(value = "/va/user",tags = "用户接口")
public class UserController {
    @Autowired
    @Qualifier("service1")
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "查")
    public UserInfo getUserInfo (Integer id) {
        return userService.getById(id);
    }

    @PostMapping
    @ApiOperation(value = "增")
    public String addUserInfo (@RequestBody UserInfo userInfo) {
        userService.addUserInfo(userInfo);
        return "success";
    }

    @PutMapping
    @ApiOperation(value = "改")
    public UserInfo update (@RequestBody UserInfo userInfo) {
        return userService.updateUserInfo(userInfo);
    }

    @DeleteMapping
    @ApiOperation(value = "删")
    public String deleteInfo (Integer id) {
        userService.deleteById(id);
        return "success";
    }
}
