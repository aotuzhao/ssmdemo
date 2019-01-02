package com.zhao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author aotu
 * @date 2019/1/2 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
    private String imgPath;
    private String location;
    private String status;
}
