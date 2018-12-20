package com.zhao.entity;

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
    private Integer id;
    private String title;
    private String img_path;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date pub_date;
    private String description;

}
