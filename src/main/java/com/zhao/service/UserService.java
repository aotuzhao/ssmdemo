package com.zhao.service;

import com.zhao.entity.User;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 17:54
 * @Description: 用户服务接口
 */
public interface UserService {
    /**
     * @return 该页码的数据
     * @Author aotu
     * @Description 分页查询
     * @Date 2018/12/19 17:49
     * @Param page 页码；row 行数
     **/
    List<User> queryAllPage(int page, int row);

    /**
     * @return 用户对象
     * @Author aotu
     * @Description 用户登陆
     * @Date 2018/12/19 17:51
     * @Param phone 用户账户
     **/
    User loginOne(String phone);

    /**
     * @return 无
     * @Author aotu
     * @Description 注册用户
     * @Date 2018/12/19 17:52
     * @Param user 用户对象
     **/
    void regUser(User user);
}
