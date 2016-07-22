package com.yuanjunye.www.dao;

import com.yuanjunye.www.po.User;

public interface ILoginDao {
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public int verifyUserDao(User user);

}
