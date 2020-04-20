package com.zz.htmanager.controller;

import com.zz.htmanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private UserService userService;

    @RequestMapping("/")
    public String index(String url, Model model) {
        if(url!=null||!url.equals("")){
            model.addAttribute("url",url);
        }
        return "user/userManager";
    }

    @RequestMapping("/userManager")
    public String userManager() {
        return "user/userManager";
    }
}
