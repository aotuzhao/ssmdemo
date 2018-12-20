package com.zhao.mapper;

import com.zhao.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author aotu
 * @Date 2018/12/20 12:54
 * 轮播图
 */
public interface BannerMapper extends Mapper<Banner> {
    /**
     * 轮播图分页查询
     *
     * @param page 页码
     * @param rows 行数
     * @return List<Banner> 结果集
     * @author auto
     * @date 2018/12/20 13:06
     **/
    List<Banner> queryPage(@Param("page") int page, @Param("rows") int rows);
}
