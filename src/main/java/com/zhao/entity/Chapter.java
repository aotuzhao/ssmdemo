package com.zhao.entity;

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
public class Chapter implements Serializable {


    @Id
    private String id;
    private String title;
    private Integer size;
    private String duration;
    private String url;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date uploadDate;
    private Integer albumId;
}
