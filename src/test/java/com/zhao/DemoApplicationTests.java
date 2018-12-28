package com.zhao;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zhao.entity.Album;
import com.zhao.entity.Chapter;
import com.zhao.entity.User;
import com.zhao.mapper.AlbumMapper;
import com.zhao.mapper.ChapterMapper;
import com.zhao.mapper.UserMapper;
import com.zhao.service.UserService;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
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

    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @Test
    public void testGoEasy() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-6672cd4d416c4980b3921e3f24d341cd");
        goEasy.publish("test1", "hehehehhehehhe");
    }

    @Test
    public void testFastdfs() throws IOException {
        File file = new File("D:/1.jpg");
        File file2 = new File("D:/2.jpg");
        File file3 = new File("D:/3.jpg");
        File file4 = new File("D:/3.jpg");
        StorePath path = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);
        StorePath path1 = fastFileStorageClient.uploadFile(new FileInputStream(file2), file.length(), "jpg", null);
        StorePath path2 = fastFileStorageClient.uploadFile(new FileInputStream(file3), file.length(), "jpg", null);
        StorePath path3 = fastFileStorageClient.uploadFile(new FileInputStream(file4), file.length(), "jpg", null);
        System.out.println(path1.getFullPath());
        System.out.println(path2.getFullPath());
        System.out.println(path3.getFullPath());
        System.out.println(path.getFullPath());
    }

    @Test
    public void testDown() throws IOException {
        byte[] bytes = fastFileStorageClient.downloadFile("group1", "M00/00/00/wKhuiFwjs32AGRCcAAADymVFWWw908.jpg", new DownloadByteArray());
        // fastFileStorageClient.deleteFile("group1","M00/00/00/wKhuiFwjsZGAPkYXAAADymVFWWw298.jsp");
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/2.jpg"));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }


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

