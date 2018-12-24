package com.zhao.service;

import com.zhao.entity.Admin;

/**
 * @Author aotu
 * @Date 2018/12/19 20:28
 * @Description: 管理员服务
 */
public interface AdminService {

    /**
     * 登陆查询
     *
     * @param user 管理员
     * @return 管理员
     * @author aotu
     * @date 2018/12/24 18:03
     **/
    Admin loginAdmin(Admin user);


}
