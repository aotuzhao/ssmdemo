package com.zhao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author aotu
 * @date 2018/12/29 13:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage implements Serializable {
    private Integer code;
    private String message;
}
