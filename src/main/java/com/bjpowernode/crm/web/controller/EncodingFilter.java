package com.bjpowernode.crm.web.controller;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //过滤post请求的中文参数
        servletRequest.setCharacterEncoding("UTF-8");
        //过滤响应流中的中文乱码
        servletResponse.setContentType("text/html;charset=utf-8");
        //过滤器放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
