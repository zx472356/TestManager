package com.zz.htmanager.service;


import com.zz.htmanager.entity.UserEntity;

/**
 * 用户接口
 */
public interface UserService {
    //用户登录
    String login(UserEntity userEntity);

    /**
     * 获取框架目录
     */
    String getFrameworkDirectory();
}
