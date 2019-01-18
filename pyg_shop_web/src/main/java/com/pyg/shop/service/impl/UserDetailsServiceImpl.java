package com.pyg.shop.service.impl;

import com.pyg.manager.service.SellerService;
import com.pyg.pojo.TbSeller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangzhenhui
 * @date 2019-01-10 11:50
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	
	// set,get注入商家的对象
	private SellerService sellerService;
	public SellerService getSellerService() {
		return sellerService;
	}
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		// 定义角色封装集合
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// 添加角色
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		//	调用商家的服务对象,查询商家的密码,主键(用户名查询)
		TbSeller seller = sellerService.findOne(username);
		//	动态匹配密码
		return new User(username, seller.getPassword(), authorities);
	}

}
