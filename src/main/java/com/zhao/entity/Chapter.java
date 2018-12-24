package com.zhao.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author aotu
 * @date 2018/12/21 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cf_chapter")
@ExcelTarget("chapter")
public class Chapter implements Serializable {


    @Id
    @Excel(name = "编号", width = 35)
    private String id;
    @Excel(name = "名称")
    private String title;
    @Excel(name = "大小", suffix = "M")
    private Integer size;
    @Excel(name = "时长", suffix = "分钟")
    private String duration;
    @Excel(name = "路径", width = 40)
    private String url;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd")
    private Date uploadDate;
    @ExcelIgnore
    private Integer albumId;
}
