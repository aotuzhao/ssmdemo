package com.zhao.service;

import com.zhao.entity.User;

/**
 * @author aotu
 * @date 2018/12/29 16:30
 */
public interface AccountService {
    User login(String phone, String password, String code);
}
