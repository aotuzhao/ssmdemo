package com.zhao.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author aotu
 * @date 2018/12/21 14:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cf_album")
@ExcelTarget("album")
public class Album implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号", needMerge = true)
    private Integer id;
    @Excel(name = "名称", needMerge = true)
    private String title;
    @Excel(name = "章节数量", needMerge = true)
    private Integer count;
    @Excel(name = "封面", type = 2, width = 20, height = 20, needMerge = true)
    private String coverImg;
    @Excel(name = "评分", suffix = "星", needMerge = true)
    private Integer score;
    @Excel(name = "作者", needMerge = true)
    private String author;
    @Excel(name = "播音", needMerge = true)
    private String broadcast;
    @Excel(name = "简介", needMerge = true)
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd", needMerge = true)
    private Date pubDate;

    @Transient
    @ExcelCollection(name = "章节列表")
    private List<Chapter> children;
}
