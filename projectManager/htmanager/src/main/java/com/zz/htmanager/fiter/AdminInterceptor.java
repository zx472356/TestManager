package com.zz.htmanager.fiter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String loginState = (String) session.getAttribute("LoginState");
        if(loginState==null||loginState.equals("No")){
            response.sendRedirect("http://localhost:8082?url="+request.getRequestURL());
            //request.getRequestDispatcher("http://localhost:8082?url"+request.getRequestURL()).forward(request,response);
        }
        return true;
    }
}
