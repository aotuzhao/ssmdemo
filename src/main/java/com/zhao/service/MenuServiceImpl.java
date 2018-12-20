package com.zhao.service;

import com.zhao.entity.Menu;
import com.zhao.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 21:19
 * @Description:
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Menu> queryAll() {

        return menuMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Menu> queryAllParent() {
        Menu menu = new Menu();
        menu.setParentId(0);
        return menuMapper.select(menu);
    }

    @Override
    public List<Menu> querySonMenu(int parentId) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        return menuMapper.select(menu);
    }
}
