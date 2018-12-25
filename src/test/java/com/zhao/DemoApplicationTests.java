package com.zhao;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.zhao.entity.Album;
import com.zhao.entity.Chapter;
import com.zhao.entity.User;
import com.zhao.mapper.AlbumMapper;
import com.zhao.mapper.ChapterMapper;
import com.zhao.mapper.UserMapper;
import com.zhao.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public void testImport() {
        String filePath = "D:/2.xls";
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(2);
        List<Album> objects = ExcelImportUtil.importExcel(new File(filePath), Album.class, params);
        for (Album object : objects) {
            System.out.println(object);
        }
        System.out.println(JSON.toJSONString(objects));
    }


    @Test
    public void testAlbum() {
        List<Album> albums = albumMapper.exportAll();
        for (Album album : albums) {
            album.setCoverImg("D:/ProgramData/idea/workspace/ssmdemo/src/main/webapp/album/image/" + album.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑", "专辑列表"),
                Album.class, albums);
        try {
            workbook.write(new FileOutputStream(new File("D:/album.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

