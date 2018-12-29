package com.zhao.controller;

import com.zhao.entity.ErrorMessage;
import com.zhao.entity.User;
import com.zhao.mapper.UserMapper;
import com.zhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aotu
 * @date 2018/12/29 16:18
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public Object login(String phone, String password, String code) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);

        User user1 = null;
        try {
            user1 = userService.loginOne(user);
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage(-200, e.getMessage());
        }

    }

    @RequestMapping("/regist")
    public User regist(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        userService.regUser(user);
        return userMapper.selectOne(user);

    }

    @RequestMapping("/modify")
    public User modify(User user) {
        userMapper.updateByPrimaryKey(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

}
