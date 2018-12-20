package com.zhao.service;

import com.zhao.entity.Banner;
import com.zhao.entity.BannerTDO;
import com.zhao.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author aotu
 * @date 2018/12/20 13:36
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public BannerTDO queryPage(int page, int rows) {
        BannerTDO bannerTDO = new BannerTDO();
        bannerTDO.setTotal(bannerMapper.selectCount(null));
        bannerTDO.setRows(bannerMapper.queryPage(page, rows));
        return bannerTDO;
    }

    @Override
    public void deleteOne(int id) {
        bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateOne(Banner banner) {
        Banner b = new Banner();
        b.setId(banner.getId());
        b.setStatus(banner.getStatus());
        bannerMapper.updateByPrimaryKeySelective(b);
    }

    @Override
    public void addOne(Banner banner) {
        banner.setStatus("n");
        bannerMapper.insertSelective(banner);
    }
}
