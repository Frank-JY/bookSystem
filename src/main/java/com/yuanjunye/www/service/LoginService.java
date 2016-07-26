package com.yuanjunye.www.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.yuanjunye.www.dao.ILoginDao;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.po.User1;

@Service
public class LoginService implements ILoginService{

	@Resource
	private ILoginDao loginDao ;
	
	/**
	 * 核对登录信息
	 * @param user
	 * @return
	 */
	public boolean verifyUser(User user) {
		
		boolean bool = true;
		int t = loginDao.verifyUserDao(user);
		if(t == 0) { 
			bool = false;
		}
		return bool;
	}
	
}
