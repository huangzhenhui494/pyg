package com.pyg.sso.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class loginController {
    /**
     * 需求:获取用户名称
     */
    @RequestMapping("/loadLoginName")
    public Map<String,String> loadLoginName(){

        // 从安全框架中获取用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // 创建一个map对象
        Map<String, String> map = new HashMap<>();
        map.put("loginName",username);
        return map;
    }
}
