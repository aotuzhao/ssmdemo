package com.zhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author aotu
 * @date 2018/12/21 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO implements Serializable {

    private Integer total;
    private List<Album> rows;
}
