package com.zhao.service;

import com.github.pagehelper.PageHelper;
import com.zhao.entity.Album;
import com.zhao.entity.AlbumDTO;
import com.zhao.entity.Chapter;
import com.zhao.mapper.AlbumMapper;
import com.zhao.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author aotu
 * @date 2018/12/21 14:51
 */

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public AlbumDTO queryPage(int page, int rows) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setTotal(albumMapper.selectCount(new Album()));
        PageHelper.startPage(page, rows);
        List<Album> list = albumMapper.selectAll();

        for (Album album : list) {
            Chapter chapter = new Chapter();
            chapter.setAlbumId(album.getId());
            album.setChildren(chapterMapper.select(chapter));
        }
        albumDTO.setRows(list);
        return albumDTO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Album queryOne(int id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addOne(Album album) {
        albumMapper.insertSelective(album);
    }
}
