package com.zhao.service;

import com.zhao.entity.User;
import com.zhao.entity.UserDTO;
import com.zhao.entity.UserMap;

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
    UserDTO queryAllPage(int page, int row);


    /**
     * 导出数据
     *
     * @param
     * @return 所有用户
     * @author aotu
     * @date 2018/12/25 10:36
     **/
    List<User> exportAll();


    /**
     * 导入数据
     *
     * @param list 用户集合
     * @return void
     * @author aotu
     * @date 2018/12/25 10:37
     **/
    void importUser(List<User> list);

    /**
     *
     * 用户登录
     *
     * @return 用户对象
     * @Author aotu
     * @Date 2018/12/19 17:51
     * @Param phone 用户账户
     **/
    User loginOne(User user);

    /**
     * 用户注册
     *
     * @return 无
     * @Author aotu
     * @Date 2018/12/19 17:52
     * @Param user 用户对象
     **/
    void regUser(User user);

    /**
     * 数据统计
     *
     * @param sex 性别
     * @return 数据结果
     * @author aotu
     * @date 2018/12/25 15:17
     **/
    List<UserMap> queryUser(String sex);


    /**
     * 男女注册统计
     *
     * @param sex 性别
     * @return 数量
     * @author
     * @date 2018/12/25 16:35
     **/
    List<Integer> countSexReg(String sex);

    /**
     * 注册统计
     *
     * @return 数量
     * @author aotu
     * @date 2018/12/25 16:42
     **/
    List<Integer> countAllReg();

}
