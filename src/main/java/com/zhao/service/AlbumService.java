package com.zhao.service;

import com.zhao.entity.Album;
import com.zhao.entity.AlbumDTO;

/**
 * @author aotu
 * @date 2018/12/21 14:44
 */
public interface AlbumService {

    /**
     * 分页查询
     *
     * @param page 页码
     * @param rows 行数
     * @return 专辑dto
     * @author aotu
     * @date 2018/12/21 14:46
     **/
    AlbumDTO queryPage(int page, int rows);

    /**
     * 查详情
     *
     * @param id 主键
     * @return 专辑
     * @author aotu
     * @date 2018/12/21 16:11
     **/
    Album queryOne(int id);

    /**
     * 添加专辑
     *
     * @param album 专辑对象
     * @return void
     * @author aotu
     * @date 2018/12/21 16:35
     **/
    void addOne(Album album);
}
