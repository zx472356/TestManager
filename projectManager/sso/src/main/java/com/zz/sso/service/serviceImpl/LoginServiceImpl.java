package com.zz.sso.service.serviceImpl;

import com.zz.sso.entity.User;
import com.zz.sso.service.LoginService;
import com.zz.sso.utils.RedisUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean login(User user) {
        if(user!=null){
            if(user.getName().equals("12345")&&user.getPassword().equals("12345")){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getUserByToken(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("ZZZ_TOKEN")){
                Jedis jedis = RedisUtils.getJedis();
                String userInfo = jedis.get("SESSION_KEY:" + cookie.getValue());
                return userInfo;
            }
        }
        return null;
    }
}
