package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Friend;
import com.yuanjunye.www.util.MybatisUtil;

public class FriendDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 查询状态
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public String findStatusDao(Friend friend) {
		String photo = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			photo = session.getMapper(IFriendDao.class).findStatusDao(friend);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return photo;
	}
	
	/**
	 * 好友申请
	 * @param friend
	 * @return
	 */
	public int addFriendDao(Friend friend) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFriendDao.class).addFriendDao(friend);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查看好友申请
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showApplyFriendDao(String myUserName) {
		List<Friend> applyFriendList = new LinkedList<Friend>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			applyFriendList = session.getMapper(IFriendDao.class).showApplyFriendDao(myUserName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return applyFriendList;
	}
	
	/**
	 * 查询个人好友
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showFriendDao(String myUserName) {
		List<Friend> friendList = new LinkedList<Friend>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			friendList = session.getMapper(IFriendDao.class).showFriendDao(myUserName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return friendList;
	}
	
	/**
	 * 通过申请
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public int updateStatusDao(Friend friend) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFriendDao.class).updateStatusDao(friend);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 不同意好友申请/删除
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public int rejectFriendDao(Friend friend) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFriendDao.class).rejectFriendDao(friend);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
}
