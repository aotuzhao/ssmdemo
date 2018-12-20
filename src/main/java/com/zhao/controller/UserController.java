package com.zhao.controller;

import com.zhao.entity.User;
import com.zhao.service.UserService;
import com.zhao.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author aotu
 * @Date 2018/12/19 18:08
 * @Description: 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CreateValidateCode vCode;

    @RequestMapping("/login")
    public String login(User user, String code, Model model, HttpSession session) {
        String sessionCode = (String) session.getAttribute("code");
        if (!sessionCode.equals(code.toLowerCase())) {
            model.addAttribute("error", "验证码错误！");
            return "login";
        } else {
            userService.loginOne(user);
            return "main/main";
        }

    }

    @RequestMapping("/regUser")
    public String regUser(User user) {
        userService.regUser(user);
        return "login";
    }

    @RequestMapping("/code")
    @ResponseBody
    public void createCode(HttpSession session, HttpServletResponse response) throws Exception {
        // CreateValidateCode vCode = new CreateValidateCode(100, 25, 4, 10);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
    }

}
