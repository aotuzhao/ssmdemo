package com.zhao.controller;

import com.zhao.entity.ErrorMessage;
import com.zhao.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页接口
 *
 * @author aotu
 * @date 2018/12/29 12:48
 */
@RestController
@RequestMapping("/first")
public class FirstController {

    @Autowired
    private FirstService firstService;

    @RequestMapping("/page")
    public Object firstPage(Integer uid, String type, String sub_type) {
        if (uid == null || type == null) {
            return new ErrorMessage(404, "参数异常");
        }
        if ("si".equals(type) && sub_type == null) {
            return new ErrorMessage(404, "参数异常");
        }
        return firstService.firstPage(uid, type, sub_type);
    }


}
