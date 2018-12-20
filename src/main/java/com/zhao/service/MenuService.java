package com.zhao.service;

import com.zhao.entity.Menu;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 21:18
 * @Description:
 */
public interface MenuService {
    List<Menu> queryAll();

    List<Menu> queryAllParent();

    List<Menu> querySonMenu(int parentId);
}
