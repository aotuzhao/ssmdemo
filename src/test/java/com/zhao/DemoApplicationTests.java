package com.zhao;

import com.zhao.entity.User;
import com.zhao.mapper.UserMapper;
import com.zhao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testReg() {
        User user = new User();
        user.setPhone("18838185470");
        user.setPassword("121212");
        userService.regUser(user);
    }

    @Test
    public void testSel() {
        User user = new User();
        user.setCity("ss");
        user.setProvince("ss");
        userMapper.selectOne(user);
    }

}

