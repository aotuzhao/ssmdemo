package com.zhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author aotu
 * @date 2018/12/25 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMap implements Serializable {

    private String name;
    private Integer value;
}
