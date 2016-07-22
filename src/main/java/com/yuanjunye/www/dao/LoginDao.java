package com.yuanjunye.www.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.MybatisUtil;

public class LoginDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public int verifyUserDao(User user) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			ILoginDao mapper = session.getMapper(ILoginDao.class);
			t = mapper.verifyUserDao(user);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
