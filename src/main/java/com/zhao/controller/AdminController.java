package com.zhao.controller;

import com.zhao.entity.Admin;
import com.zhao.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author aotu
 * @Date 2018/12/19 20:34
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session, String code, Model model) {
        String sessionCode = (String) session.getAttribute("code");
        if (!sessionCode.equalsIgnoreCase(code)) {
            model.addAttribute("error", "验证码错误！");
            return "login";
        } else {
            Admin root = adminService.loginAdmin(admin);
            session.setAttribute("user", root);
            return "main/main";
        }

    }

    @RequestMapping("logOut")
    public String logOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }


}
