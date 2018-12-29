package com.zhao.service;

import java.util.Map;

/**
 * @author aotu
 * @date 2018/12/29 12:57
 */
public interface FirstService {
    /**
     * 一级页面接口
     *
     * @param uid      用户ID标识
     * @param type     请求数据类型
     * @param sub_type 上师
     * @return JSON
     * @author aotu
     * @date 2018/12/29 12:58
     **/
    Map<String, Object> firstPage(Integer uid, String type, String sub_type);
}
