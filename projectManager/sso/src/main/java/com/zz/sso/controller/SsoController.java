package com.zz.sso.controller;

import com.zz.sso.entity.User;
import com.zz.sso.service.LoginService;
import com.zz.sso.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SsoController {

    private static StringBuilder URL;
    @Autowired
    private LoginService loginService;


    @RequestMapping("/")
    public String index(String url){
        if(url!=null){
            URL = new StringBuilder(url);
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(User user, HttpServletResponse response){
        boolean flag = loginService.login(user);
        if(flag){
            Jedis jedis = RedisUtils.getJedis();
            String token = UUID.randomUUID().toString();
            jedis.set("SESSION_KEY:"+token,user.getName());
            jedis.expire("SESSION_KEY:"+token,360);
            Cookie cookie = new Cookie("ZZZ_TOKEN",token);
            response.addCookie(cookie);
            try {
                response.sendRedirect(URL+"token="+token);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "success:"+token;
        }else{
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("/selectByToken")
    public String selectByToken(String token){
        Jedis jedis = RedisUtils.getJedis();
        String info = jedis.get("SESSION_KEY:"+token);
        if(info==null||info.equals("")){
            return "用户已经过期";
        }
        jedis.expire("SESSION_KEY:"+token,360);
        return info;
    }

    @ResponseBody
    @RequestMapping("/getState")
    public String getState(HttpServletRequest request,HttpServletResponse response){
        return loginService.getUserByToken(response,request);
    }
}
