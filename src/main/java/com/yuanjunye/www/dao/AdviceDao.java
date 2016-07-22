package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.util.MybatisUtil;

public class AdviceDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 添加意见反馈
	 * @param advice
	 * @return
	 */
	public int addAdviceDao(Advice advice) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IAdviceDao.class).addAdviceDao(advice);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询所有意见反馈
	 * @return
	 */
	public List<Advice> ShowAdviceDao() {
		List<Advice> adviceList = new LinkedList<Advice>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			adviceList = session.getMapper(IAdviceDao.class).ShowAdviceDao();
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return adviceList;
	}
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public int deleteAdviceDao(int id) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IAdviceDao.class).deleteAdviceDao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
