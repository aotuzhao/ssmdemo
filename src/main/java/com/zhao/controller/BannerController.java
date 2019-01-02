package com.zhao.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zhao.entity.Banner;
import com.zhao.entity.BannerTDO;
import com.zhao.service.BannerService;
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
 * @date 2018/12/20 14:03
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/queryAll")
    public BannerTDO queryPage(int page, int rows) {
        return bannerService.queryPage(page, rows);
    }

    @RequestMapping("/deleteOne")
    public String deleteOne(int id) {
        bannerService.deleteOne(id);
        return "删除成功！";
    }

    @RequestMapping("/updateOne")
    public String updateOne(Banner banner) {
        bannerService.updateOne(banner);
        return "更新成功！";
    }

    @RequestMapping("/addOne")
    public String addOne(Banner banner, MultipartFile img, HttpSession session) throws Exception {
        //String imgName = System.currentTimeMillis() + imgPath.getOriginalFilename();
        /*String realPath = session.getServletContext().getRealPath("/imgPath");
        File to = new File(realPath + "/" + imgName);
        imgPath.transferTo(to);*/
        String extension = FilenameUtils.getExtension(img.getOriginalFilename());
        StorePath storePath = fastFileStorageClient.uploadFile(img.getInputStream(), img.getSize(), extension, null);
        banner.setImg_path(storePath.getFullPath());
        bannerService.addOne(banner);
        return "添加成功！";
    }

    @RequestMapping("/export")
    public void export(HttpSession session, HttpServletResponse response) {
        String filePath = session.getServletContext().getRealPath("/imgPath");
        List<Banner> list = bannerService.exportAll();
        for (Banner banner : list) {
            // banner.setImg_path(filePath + "/" + banner.getImg_path());
            banner.setImg_path("http://192.168.110.138/" + banner.getImg_path());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播", "轮播图"), Banner.class, list);

        try {
            String fileName = URLEncoder.encode("轮播图.xls", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
