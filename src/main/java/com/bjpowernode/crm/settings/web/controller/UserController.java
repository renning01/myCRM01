package com.bjpowernode.crm.settings.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入setting模块。。。。");
        String path = request.getServletPath();
        if ("/setting/user.do".equals(path)){
            System.out.println("user成功......");
        }else if ("".equals(path)){

        }
    }
}
