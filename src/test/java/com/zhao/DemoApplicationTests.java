package com.zhao;

import com.github.pagehelper.PageHelper;
import com.zhao.entity.Chapter;
import com.zhao.entity.User;
import com.zhao.mapper.AlbumMapper;
import com.zhao.mapper.ChapterMapper;
import com.zhao.mapper.UserMapper;
import com.zhao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    AlbumMapper albumMapper;


    @Test
    public void testAlbum() {
    }

    @Test
    public void testChapter() {

        Chapter t = new Chapter();
        t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        t.setTitle("sssss");
        chapterMapper.insertSelective(t);
    }


    @Test
    public void testReg() {
        User user = new User();
        user.setPhone("18838185470");
        user.setPassword("121212");
        userService.regUser(user);
    }

    @Test
    public void testSel() {
        //user.setId(4);
        User user = new User();
        //user.setCity("ss");
        user.setProvince("ss");
        /*
        userMapper.selectCount(user);*/

        PageHelper.startPage(1, 2);

        List<User> users = userMapper.selectAll();


       /* userMapper.updateByPrimaryKey(user);
        Example example = new Example(User.class);*/

    }

}

