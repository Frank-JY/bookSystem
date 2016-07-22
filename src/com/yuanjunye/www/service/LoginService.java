package com.yuanjunye.www.service;

import com.yuanjunye.www.dao.LoginDao;
import com.yuanjunye.www.po.User;

public class LoginService {

	private LoginDao loginDao = new LoginDao();
	
	/**
	 * 核对登录信息
	 * @param user
	 * @return
	 */
	public boolean verifyUser(User user) {
		boolean bool = loginDao.verifyUserDao(user);
		return bool;
	}
}
