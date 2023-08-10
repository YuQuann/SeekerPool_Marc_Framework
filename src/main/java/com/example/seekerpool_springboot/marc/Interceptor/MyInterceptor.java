package com.example.seekerpool_springboot.marc.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class MyInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession(false).getAttribute("memId") == null){
            response.sendRedirect("/front-end/member/member/memberlogin.html");
            return false;
        }
        return true;
    }
}
