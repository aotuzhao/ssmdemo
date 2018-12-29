package com.zhao.controller;

import com.zhao.entity.Album;
import com.zhao.entity.Article;
import com.zhao.mapper.ArticleMapper;
import com.zhao.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aotu
 * @date 2018/12/29 15:35
 */
@RestController
@RequestMapping("/detail")
public class DetailController {


    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/wen")
    public Album getAlum(Integer id, Integer uid) {
        return albumService.getOne(id);
    }

    @RequestMapping("/si")
    public Article getArticle(Integer id, Integer uid) {
        return articleMapper.selectByPrimaryKey(id);
    }
}
