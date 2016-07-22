package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.util.MybatisUtil;

public class CommentDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 查询一本图书的评论
	 * @param bookId
	 * @return
	 */
	public List<Comment> bookCommentDao(String bookId) {
		List<Comment> commentList = new LinkedList<Comment>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			commentList = session.getMapper(ICommentDao.class).bookCommentDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return commentList;
	}
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	public int publishDao(Comment comment) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(ICommentDao.class).publishDao(comment);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 管理员删除评论
	 * @param id
	 * @return
	 */
	public int deleteCommentDao(int id) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(ICommentDao.class).deleteCommentDao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询个人评论
	 * @param userName
	 * @return
	 */
	public List<Comment> myCommentDao(String userName) {
		List<Comment> myCommentList = new ArrayList<Comment>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			myCommentList = session.getMapper(ICommentDao.class).myCommentDao(userName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return myCommentList;
	}
	
	/**
	 * 用户删除个人评论
	 * @param comment
	 * @return
	 */
	public int deleteComment1Dao(Comment comment) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(ICommentDao.class).deleteComment1Dao(comment);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
