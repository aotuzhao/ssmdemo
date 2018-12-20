package com.zhao.controller;

import com.zhao.entity.Menu;
import com.zhao.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 21:21
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping("/queryAll")
    public List<Menu> queryAll() {
        return menuService.queryAll();
    }

    @RequestMapping("/queryParent")
    public List<Menu> queryParent() {
        return menuService.queryAllParent();
    }


    @RequestMapping("/querySon")
    public List<Menu> querySon(int parentId) {
        return menuService.querySonMenu(parentId);
    }
}
