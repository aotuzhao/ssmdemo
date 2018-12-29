package com.zhao.mapper;

import com.zhao.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @author aotu
 * @date 2018/12/21 14:44
 */
public interface AlbumMapper extends Mapper<Album> {

    /**
     * 专辑表连接的分页
     *
     * @param page 页码
     * @param rows 行号
     * @return 当页数据
     * @author aotu
     * @date 2018/12/24 17:25
     **/
    List<Album> showAll(@Param("page") int page, @Param("rows") int rows);

    /**
     * 导出所有专辑
     *
     * @return 所有专辑
     * @author aotu
     * @date 2018/12/24 18:05
     **/
    List<Album> exportAll();

    /**
     * 接口
     *
     * @param id 标识
     * @return 专辑
     * @author aotu
     * @date 2018/12/29 15:47
     **/
    Album getOne(int id);

}
