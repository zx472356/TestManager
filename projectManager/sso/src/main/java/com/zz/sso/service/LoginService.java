package com.zz.sso.service;

import com.zz.sso.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录服务接口
 */
public interface LoginService {
    boolean login(User user);

    String getUserByToken(HttpServletResponse response, HttpServletRequest request);
}
