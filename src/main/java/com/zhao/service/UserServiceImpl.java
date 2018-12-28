package com.zhao.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.zhao.entity.User;
import com.zhao.entity.UserDTO;
import com.zhao.entity.UserMap;
import com.zhao.mapper.UserMapper;
import com.zhao.util.Md5Util;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/19 17:57
 * @Description: 用户服务实现类
 */

@Service
@Log4j
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    @Autowired
    DruidDataSource druidDataSource;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDTO queryAllPage(int page, int row) {
        UserDTO dto = new UserDTO();
        PageHelper.startPage(page, row);
        List<User> list = userMapper.selectAll();
        dto.setTotal(userMapper.selectCount(new User()));
        dto.setRows(list);
        return dto;
    }

    @Override
    public List<User> exportAll() {
        return userMapper.selectAll();
    }

    @Override
    public void importUser(List<User> list) {
        /*SqlSession sqlSession = sessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        userMapper = sqlSession.getMapper(UserMapper.class);
        for (User user : list) {
            userMapper.insertSelective(user);
        }
        sqlSession.commit();
        sqlSession.clearCache();*/
        for (User user : list) {
            userMapper.insertSelective(user);
        }

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User loginOne(User user) {
        User example = new User();
        example.setPhone(user.getPhone());
        User user1 = userMapper.selectOne(example);
        if (user1 == null) {
            throw new RuntimeException("用户不存在！");
        }
        String salt = user1.getSalt();
        String password = user.getPassword() + salt;
        log.debug(user.toString());
        log.debug(salt);
        if (!Md5Util.checkPassword(password, user1.getPassword())) {
            throw new RuntimeException("密码错误！");
        }
        return user1;
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

    @Override
    public List<UserMap> queryUser(String sex) {
        return userMapper.queryUser(sex);
    }

    @Override
    public List<Integer> countSexReg(String sex) {
        List<Integer> list = new ArrayList<>();
        list.add(userMapper.countSexReg(sex, 7));
        list.add(userMapper.countSexReg(sex, 15));
        list.add(userMapper.countSexReg(sex, 30));
        list.add(userMapper.countSexReg(sex, 90));
        list.add(userMapper.countSexReg(sex, 180));
        list.add(userMapper.countSexReg(sex, 365));
        return list;
    }

    @Override
    public List<Integer> countAllReg() {
        List<Integer> list = new ArrayList<>();
        list.add(userMapper.countAllReg(7));
        list.add(userMapper.countAllReg(15));
        list.add(userMapper.countAllReg(30));
        list.add(userMapper.countAllReg(90));
        list.add(userMapper.countAllReg(180));
        list.add(userMapper.countAllReg(365));
        return list;
    }


}
