package com.zhao.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
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

/**
 * @Author aotu
 * @Date 2018/12/19 17:38
 * @Description: 普通用户
 */
@Data
@Table(name = "cf_user")
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("user")
public class User implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;

    @Excel(name = "手机")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "头像")
    private String headImg;
    @Excel(name = "昵称")
    private String name;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "状态", replace = "正常_1,冻结_0")
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "注册日期", format = "yyyy-MM-dd")
    private Date regDate;
    @Transient
    @ExcelIgnore
    private Guru guru;
    private Integer dharme_id;

}
