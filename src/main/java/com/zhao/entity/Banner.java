package com.zhao.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author aotu
 * @Date 2018/12/20 11:36
 * @Description: 轮播图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cf_banner")
public class Banner {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "图片", type = 2, width = 40, height = 20)
    private String img_path;
    @Excel(name = "状态")
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "创建时间", format = "yyyy-MM-dd")
    private Date pub_date;
    @Excel(name = "描述")
    private String description;

}
