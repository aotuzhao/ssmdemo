package com.zhao.service;

import com.zhao.entity.Banner;
import com.zhao.entity.BannerTDO;

/**
 * @author aotu
 * @date 2018/12/20 12:55
 */
public interface BannerService {

    /**
     * 分页查询
     *
     * @param page 页码
     * @param rows 行数
     * @return 结果DTO, easyui分页使用
     * @author aotu
     * @date 2018/12/20 13:35
     **/
    BannerTDO queryPage(int page, int rows);

    /**
     * 根据id 删除轮播图
     *
     * @param id 轮播id
     * @return void
     * @author aotu
     * @date 2018/12/20 15:47
     **/
    void deleteOne(int id);

    /**
     * 更新状态
     *
     * @param banner 轮播图
     * @return void
     * @author aotu
     * @date 2018/12/20 16:07
     **/
    void updateOne(Banner banner);

    /**
     * @param banner 轮播对象
     * @return void
     * @author aotu
     * @date 2018/12/20 16:43
     **/
    void addOne(Banner banner);
}
