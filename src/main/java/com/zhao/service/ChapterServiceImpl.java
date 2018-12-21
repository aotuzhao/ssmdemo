package com.zhao.service;

import com.zhao.entity.Album;
import com.zhao.entity.Chapter;
import com.zhao.mapper.AlbumMapper;
import com.zhao.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 章节
 *
 * @author aotu
 * @date 2018/12/21 14:23
 */

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void addChapter(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        Album album = albumMapper.selectByPrimaryKey(chapter.getAlbumId());
        Album album1 = new Album();
        album1.setId(album.getId());
        album1.setCount(album.getCount() + 1);
        albumMapper.updateByPrimaryKeySelective(album1);
        chapterMapper.insertSelective(chapter);
    }
}
