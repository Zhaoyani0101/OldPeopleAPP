//package com.zhaoyani.springboot.utils;
//
//import com.zhaoyani.springboot.common.Constants;
//import com.zhaoyani.springboot.entity.User;
//import com.zhaoyani.springboot.exception.ServiceException;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class SessionUtils {
//
//    //获取当前登录的用户信息
//    public User getUser(){
//        User user;
//        try {
//            user = (User) StpUtil.getSession().get(Constants.LOGIN_USER_KEY);
//        }catch (Exception e){
//            log.error("获取用户信息失败",e);
//            throw new ServiceException("401","获取用户信息失败");
//        }
//        return user;
//    }
//}
