package com.zhao.mapper;

import com.zhao.entity.User;
import com.zhao.entity.UserMap;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @Author aotu
 * @Date 2018/12/19 17:47
 * 用户数据库操作
 */
public interface UserMapper extends Mapper<User> {

    /**
     * 男女地方统计
     *
     * @param sex 性别
     * @return 统计数据
     * @author aotu
     * @date 2018/12/25 15:09
     **/
    List<UserMap> queryUser(String sex);


    /**
     * 男女注册统计
     *
     * @param sex 性别
     * @param day 天数
     * @return 数量
     * @author
     * @date 2018/12/25 16:35
     **/
    int countSexReg(@Param("sex") String sex, @Param("day") int day);

    /**
     * 注册统计
     *
     * @param day 天数
     * @return 数量
     * @author aotu
     * @date 2018/12/25 16:42
     **/
    int countAllReg(int day);

}
