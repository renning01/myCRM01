package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入setting模块。。。。");
        String path = request.getServletPath();
        if ("/setting/user/login.do".equals(path)){
            login(request,response);
        }else if ("".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入严重模式。。");
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //装换成MD5格式
        String pwd = MD5Util.getMD5(loginPwd);
       // String pwd=MD5Util.getMD5("21313");
        //接受ip地址
        String ip = request.getRemoteAddr();
        System.out.println("ip:"+ip);
        //业务层开发，统一使用代理类形态的接口对象
        UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());
        try {
            User user=us.login(loginAct,pwd,ip);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        } catch (Exception e) {
            e.printStackTrace();
            //失败，抛出异常
            String msg = e.getMessage();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
