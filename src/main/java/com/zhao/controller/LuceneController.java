package com.zhao.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zhao.entity.Product;
import com.zhao.service.LuceneService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aotu
 * @date 2019/1/2 16:51
 */
@Controller
@RequestMapping("lucene")
public class LuceneController {

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("add")
    public String add(Product product, MultipartFile img) {
        try {
            String extension = FilenameUtils.getExtension(img.getOriginalFilename());
            StorePath storePath = fastFileStorageClient.uploadFile(img.getInputStream(), img.getSize(), extension, null);
            product.setImgPath(storePath.getFullPath());
            luceneService.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "lucene/index";
    }

    @RequestMapping("search")
    @ResponseBody
    public List<Product> search(String keywords, int page, int rows) {
        List<Product> list = luceneService.search(keywords);
        return list;
    }

}
