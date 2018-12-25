package com.zhao.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zhao.entity.User;
import com.zhao.entity.UserDTO;
import com.zhao.entity.UserMap;
import com.zhao.service.UserService;
import com.zhao.util.CreateValidateCode;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/queryPage")
    @ResponseBody
    public UserDTO queryPage(int page, int rows) {
        return userService.queryAllPage(page, rows);
    }

    @RequestMapping("/export")
    public void exprotAll(HttpServletResponse response) {
        List<User> list = userService.exportAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户", "用户详情"), User.class, list);
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("用户信息.xls", "UTF-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/import")
    @ResponseBody
    public String implortAll(MultipartFile user) {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        try {
            List<User> list = ExcelImportUtil.importExcel(user.getInputStream(), User.class, params);
            userService.importUser(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("/countSex")
    @ResponseBody
    public List<UserMap> countSex(String sex) {
        return userService.queryUser(sex);
    }

    @RequestMapping("/countReg")
    @ResponseBody
    public Map<String, List<Integer>> countSexReg(String sex) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("man", userService.countSexReg("男"));
        map.put("female", userService.countSexReg("女"));
        map.put("allUser", userService.countAllReg());
        return map;
    }


}
