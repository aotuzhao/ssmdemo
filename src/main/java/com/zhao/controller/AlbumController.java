package com.zhao.controller;

import com.zhao.entity.Album;
import com.zhao.entity.AlbumDTO;
import com.zhao.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;


/**
 * @author aotu
 * @date 2018/12/21 15:24
 */
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;


    @RequestMapping("/queryPage")
    public AlbumDTO queryPage(int page, int rows) {
        return albumService.queryPage(page, rows);
    }

    @RequestMapping("/queryOne")
    public Album queryOne(int id) {
        return albumService.queryOne(id);
    }


    @RequestMapping("/addOne")
    public String addOne(Album album, MultipartFile img, HttpSession session) throws Exception {

        String imgName = System.currentTimeMillis() + img.getOriginalFilename();
        String realPath = session.getServletContext().getRealPath("/album/image");
        File to = new File(realPath + "/" + imgName);
        img.transferTo(to);
        album.setCoverImg(imgName);
        albumService.addOne(album);
        return "添加成功！";
    }

}
