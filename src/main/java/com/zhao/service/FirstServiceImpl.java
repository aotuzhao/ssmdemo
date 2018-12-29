package com.zhao.service;

import com.zhao.entity.Album;
import com.zhao.entity.Article;
import com.zhao.entity.Banner;
import com.zhao.entity.User;
import com.zhao.mapper.AlbumMapper;
import com.zhao.mapper.ArticleMapper;
import com.zhao.mapper.BannerMapper;
import com.zhao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aotu
 * @date 2018/12/29 13:01
 */
@Service
@Transactional
public class FirstServiceImpl implements FirstService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> firstPage(Integer uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        switch (type) {
            case "all":
                List<Banner> banners = bannerMapper.selectAll();
                List<Album> albums = albumMapper.selectAll();
                List<Article> articles = articleMapper.selectAll();
                map.put("banner", banners);
                map.put("album", albums);
                map.put("article", articles);
                break;
            case "wen":
                List<Album> albums1 = albumMapper.selectAll();
                map.put("album", albums1);
                break;
            case "si":
                Article article = new Article();
                if ("xmfy".equals(sub_type)) {

                    List<Article> articleList1 = articleMapper.select(article);
                    map.put("xmfy", articleList1);
                } else {

                    User user = userMapper.selectByPrimaryKey(uid);
                    article.setGid(user.getDharme_id());
                    List<Article> articleList = articleMapper.select(article);
                    map.put("ssyi", articleList);
                }
                break;

            default:
                break;
        }
        return map;
    }
}
