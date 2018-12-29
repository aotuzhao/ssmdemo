package com.zhao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author aotu
 * @date 2018/12/29 13:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cf_article")
public class Article implements Serializable {

    @Id
    private Integer id;
    private String link;
    private String ext;
    private String author;
    private Integer gid;
    @JSONField(format = "yyyy-MM-dd")
    private Date create_date;


}
