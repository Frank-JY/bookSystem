package com.yuanjunye.www.service;

import com.yuanjunye.www.po.User;

public interface ILoginService {

	/**
	 * 核对登录信息
	 * @param user
	 * @return
	 */
	public boolean verifyUser(User user);
	
	
}
