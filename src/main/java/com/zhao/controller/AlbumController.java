package com.zhao.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zhao.entity.Album;
import com.zhao.entity.AlbumDTO;
import com.zhao.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


/**
 * @author aotu
 * @date 2018/12/21 15:24
 */
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

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

       /* String imgName = System.currentTimeMillis() + imgPath.getOriginalFilename();

        String realPath = session.getServletContext().getRealPath("/album/image");
        File to = new File(realPath + "/" + imgName);
        imgPath.transferTo(to);*/
        String extension = FilenameUtils.getExtension(img.getOriginalFilename());
        StorePath storePath = fastFileStorageClient.uploadFile(img.getInputStream(), img.getSize(), extension, null);
        album.setCoverImg(storePath.getFullPath());
        albumService.addOne(album);
        return "添加成功！";
    }

    @RequestMapping("/export")
    public void exportAll(HttpSession session, HttpServletResponse response) {
        String imgPath = session.getServletContext().getRealPath("/album/image");
        List<Album> albums = albumService.exportAlbum();
        for (Album album : albums) {
            album.setCoverImg(imgPath + "/" + album.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑", "专辑列表"),
                Album.class, albums);
        try {
            String fileName = URLEncoder.encode("专辑详情.xls", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
