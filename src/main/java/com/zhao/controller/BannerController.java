package com.zhao.controller;

import com.zhao.entity.Banner;
import com.zhao.entity.BannerTDO;
import com.zhao.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

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
        System.out.println(img.getName());
        System.out.println(img.getContentType());
        System.out.println(img.getOriginalFilename());
        String imgName = System.currentTimeMillis() + img.getOriginalFilename();
        String realPath = session.getServletContext().getRealPath("/img");
        File to = new File(realPath + "/" + imgName);
        img.transferTo(to);
        banner.setImg_path(imgName);
        bannerService.addOne(banner);
        return "添加成功！";
    }

}
