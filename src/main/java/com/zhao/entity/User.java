package com.zhao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author aotu
 * @Date 2018/12/19 17:38
 * @Description: 普通用户
 */
@Data
@Table(name = "cf_user")
public class User implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String phone;
    private String password;
    private String salt;
    private String headImg;
    private String name;
    private String sex;
    private String province;
    private String city;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date regDate;
    @Transient
    private Guru guru;

}
