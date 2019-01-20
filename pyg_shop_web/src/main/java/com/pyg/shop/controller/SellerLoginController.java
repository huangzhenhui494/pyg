package com.pyg.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.manager.service.SellerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class SellerLoginController {

	//	注入远程service
	@Reference(timeout = 1000000)
	private SellerService sellerService;

	/**
	 * 需求:获取当前用户的登录信息
	 * @return
	 */
	@RequestMapping("/loadLoginName")
	public Map loadLoginName() {
		
		//获取用户名
		//使用安全框架获取用户登录名
		String username = 
				SecurityContextHolder.getContext().getAuthentication().getName();
		//创建map对象
		Map map = new HashMap();
		//把用户名放入map
		map.put("loginName", username);
		return map;
	}
}
