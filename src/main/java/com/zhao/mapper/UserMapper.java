package com.zhao.mapper;

import com.zhao.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 17:47
 * @Description: 用户数据库操作
 */
public interface UserMapper extends Mapper<User> {
    /**
     * @return 该页码的数据
     * @Author aotu
     * @Description 分页查询
     * @Date 2018/12/19 17:49
     * @Param page 页码；row 行数
     **/
    List<User> queryAllPage(int page, int row);


}
