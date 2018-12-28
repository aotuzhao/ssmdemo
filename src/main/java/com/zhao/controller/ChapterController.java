package com.zhao.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zhao.entity.Chapter;
import com.zhao.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;

/**
 * @author aotu
 * @date 2018/12/21 16:56
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/addOne")
    @ResponseBody
    public String addOne(Chapter chapter, MultipartFile audio, HttpSession session) {
        //String audioName = System.currentTimeMillis() + audio.getOriginalFilename();
        //String filePath = session.getServletContext().getRealPath("/chapter/audio");
        String extension = FilenameUtils.getExtension(audio.getOriginalFilename());
        //File to = new File(filePath + "/" + audioName);
        File tmp = null;
        try {
            tmp = File.createTempFile("tmp", extension);
            audio.transferTo(tmp);
            StorePath storePath = fastFileStorageClient.uploadFile(audio.getInputStream(), audio.getSize(), extension, null);
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(tmp);
            long ls = m.getDuration();
            int minute = (int) (ls / 1000) / 60;
            int sounds = (int) (ls / 1000) % 60;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(minute + ":");
            stringBuilder.append(sounds);
            chapter.setDuration(stringBuilder.toString());
            int size = (int) (audio.getSize() / (1024 * 1024));
            chapter.setSize(size);
            chapter.setUrl(storePath.getFullPath());
            chapterService.addChapter(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tmp != null) {
                tmp.deleteOnExit();
            }
        }
        //audio.transferTo(to);
        return "添加成功！";
    }

    @RequestMapping("/downLoad")
    public void downLoad(String url, String title, HttpServletResponse response, HttpSession session) throws Exception {
        String filePath = session.getServletContext().getRealPath("/chapter/audio");
        File file = new File(filePath + "/" + url);
        String extension = FilenameUtils.getExtension(url);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(title + "." + extension, "UTF-8"));
        response.setContentType("audio/mpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }


}
