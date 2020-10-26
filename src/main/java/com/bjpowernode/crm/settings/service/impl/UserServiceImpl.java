package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //map.put("allowIps",ip);
        User user=userDao.login(map);
        if (user==null){
            throw new LoginException("账号密码错误》》》");
        }
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime)<0){
            throw new LoginException("账号失效。。");
        }
        String lockState = user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号已经上锁。。");
        }
        String allowIps = user.getAllowIps();
        /*if (!ip.contains(allowIps)){
            throw new LoginException("账号登录ip地址不在登录范围之内。。");
        }*/
        return user;
    }
}
