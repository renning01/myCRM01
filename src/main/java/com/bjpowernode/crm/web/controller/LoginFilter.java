package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入验证有没有登录过滤器。。");
        HttpServletRequest httprequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpresponse=(HttpServletResponse)servletResponse;

        String path=httprequest.getServletPath();
        if ("/setting/user/login.do".equals(path) || "/login.jsp".equals(path)){
            //放行，不能让过滤器全吃进入死循环
            filterChain.doFilter(servletRequest,servletResponse);
            //
        }else {
            HttpSession session = httprequest.getSession();
            User user = (User) session.getAttribute("user");
            if (user!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                //重新返回登录页面,重定向
                httpresponse.sendRedirect( httprequest.getContextPath()+"/login.jsp");
            }
        }
     /*filterChain.doFilter(servletRequest,servletResponse);*/

    }
}
