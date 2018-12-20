package com.zhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author aotu
 * @Date 2018/12/19 21:15
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cf_menu")
public class Menu implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Column(name = "title")
    private String text;
    @Column(name = "iconcls")
    private String iconCls;
    @Column(name = "url")
    private String href;
    private Integer parentId;
}
