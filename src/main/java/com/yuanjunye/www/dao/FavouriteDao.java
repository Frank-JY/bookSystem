package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Favourite;
import com.yuanjunye.www.util.MybatisUtil;

public class FavouriteDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 获取用户收藏夹图书信息
	 * @param userName
	 * @return
	 */
	public List<String> findCollectBookIdDao(String userName) {
		List<String> bookIdList = new ArrayList<String>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			bookIdList = session.getMapper(IFavouriteDao.class).findCollectBookIdDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return bookIdList;
	}
	
	/**
	 * 检查用户是否重复收藏图书
	 * @param userName
	 * @param bookId
	 * @return
	 */
	public int isRepeatDao(Favourite favourite) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFavouriteDao.class).isRepeatDao(favourite);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 检查用户是否可以收藏(数量少于等于10本)
	 * @param userName
	 * @return
	 */
	public int isCollectDao(String userName) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFavouriteDao.class).isCollectDao(userName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 插入用户收藏图书信息
	 * @param favourite
	 * @return
	 */
	public int collectDao(Favourite favourite) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFavouriteDao.class).collectDao(favourite);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 删除收藏夹图书信息
	 * @param favourite
	 * @return
	 */
	public int deleteFavouriteDao(Favourite favourite) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IFavouriteDao.class).deleteFavouriteDao(favourite);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
