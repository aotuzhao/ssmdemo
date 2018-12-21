package com.zhao.controller;

import com.zhao.entity.Chapter;
import com.zhao.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author aotu
 * @date 2018/12/21 16:56
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @RequestMapping("/addOne")
    @ResponseBody
    public String addOne(Chapter chapter, MultipartFile audio, HttpSession session) throws Exception {
        String audioName = System.currentTimeMillis() + audio.getOriginalFilename();
        String filePath = session.getServletContext().getRealPath("/chapter/audio");
        File to = new File(filePath + "/" + audioName);
        audio.transferTo(to);
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(to);

        long ls = m.getDuration();
        int minute = (int) (ls / 1000) / 60;
        int sounds = (int) (ls / 1000) % 60;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(minute + ":");
        stringBuilder.append(sounds);
        chapter.setDuration(stringBuilder.toString());
        int size = (int) (audio.getSize() / (1024 * 1024));
        chapter.setSize(size);
        chapter.setUrl(audioName);
        chapterService.addChapter(chapter);
        return "添加成功！";
    }

    @RequestMapping("/downLoad")
    public void downLoad(String url, HttpServletResponse response, HttpSession session) throws Exception {
        String filePath = session.getServletContext().getRealPath("/chapter/audio");
        byte[] bytes = FileUtils.readFileToByteArray(new File(filePath + "/" + url));
        response.setHeader("content-disposition", "attachment;filename=" + url);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }


}
