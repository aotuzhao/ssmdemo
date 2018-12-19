package com.zhao.service;

import com.zhao.entity.User;
import com.zhao.mapper.UserMapper;
import com.zhao.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 17:57
 * @Description: 用户服务实现类
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllPage(int page, int row) {

        return null;
    }

    @Override
    public User loginOne(String phone) {

        return null;
    }

    @Override
    public void regUser(User user) {
        User example = new User();
        example.setPhone(user.getPhone());
        User user1 = userMapper.selectOne(example);
        if (user1 != null) {
            throw new RuntimeException("用户已存在，请直接登陆！");
        }
        String salt = Md5Util.getSalt();
        String newPass = Md5Util.encryption(user.getPassword() + salt);
        user.setSalt(salt);
        user.setPassword(newPass);
        userMapper.insert(user);

    }
}
