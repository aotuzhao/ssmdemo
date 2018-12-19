package com.zhao.controller;

import com.zhao.entity.User;
import com.zhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author aotu
 * @Date 2018/12/19 18:08
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, String code, HttpSession session) {

        return "main/main";
    }

    @RequestMapping("/regUser")
    public String regUser(User user) {

        return "login";
    }


}
