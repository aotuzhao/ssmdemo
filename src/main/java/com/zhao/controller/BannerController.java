package com.zhao.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zhao.entity.Banner;
import com.zhao.entity.BannerTDO;
import com.zhao.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
        String imgName = System.currentTimeMillis() + img.getOriginalFilename();
        String realPath = session.getServletContext().getRealPath("/img");
        File to = new File(realPath + "/" + imgName);
        img.transferTo(to);
        banner.setImg_path(imgName);
        bannerService.addOne(banner);
        return "添加成功！";
    }

    @RequestMapping("/export")
    public void export(HttpSession session, HttpServletResponse response) {
        String filePath = session.getServletContext().getRealPath("/img");
        List<Banner> list = bannerService.exportAll();
        for (Banner banner : list) {
            banner.setImg_path(filePath + "/" + banner.getImg_path());
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
