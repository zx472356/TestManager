package com.zz.htmanager.controller;

import com.zz.htmanager.entity.UserEntity;
import com.zz.htmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

//    @RequestMapping("/")
//    public String index() {
//        return "login";
//    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(UserEntity userEntity) {
        return userService.login(userEntity);
    }
}
