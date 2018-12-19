package com.zhao.service;

import com.zhao.entity.Admin;
import com.zhao.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author aotu
 * @Date 2018/12/19 20:29
 * @Description:
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    /**
     * @Author aotu
     * @Description 管理员登陆
     * @Date 2018/12/19 20:29
     * @Param
     * @return 管理员
     **/

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Admin loginAdmin(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setName(admin.getName());
        Admin admin2 = adminMapper.selectOne(admin1);
        if (admin2 == null) {
            throw new RuntimeException("用户不存在！");
        }
        if (!admin2.getPassword().equals(admin.getPassword())) {

            throw new RuntimeException("密码错误！");
        }
        return admin2;
    }
}
