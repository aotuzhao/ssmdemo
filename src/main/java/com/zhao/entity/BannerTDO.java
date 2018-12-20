package com.zhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author aotu
 * @date 2018/12/20 14:06
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerTDO implements Serializable {
    private Integer total;
    private List<Banner> rows;
}
