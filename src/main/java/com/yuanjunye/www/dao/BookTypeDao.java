package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.BookType;
import com.yuanjunye.www.util.MybatisUtil;

public class BookTypeDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 查询所有图书类型名称
	 * @return
	 */
	public Set<String> allTypeNameDao() {
		Set<String> typeNameSet = new HashSet<String>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			typeNameSet = session.getMapper(IBookTypeDao.class).allTypeNameDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return typeNameSet;
	}
	
	/**
	 * 显示所有的图书类型
	 * @return
	 */
	public List<BookType> showBookTypeDao() {
		List<BookType> bookTypeList = new LinkedList<BookType>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			bookTypeList = session.getMapper(IBookTypeDao.class).showBookTypeDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return bookTypeList;
	}
	
	/**
	 * 查询图书类型名称
	 * @param typeName
	 * @return
	 */
	public String findTypeNameDao(int typeId) {
		String typeName = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			typeName = session.getMapper(IBookTypeDao.class).findTypeNameDao(typeId);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return typeName;
	}
	
	/**
	 *修改图书类型 
	 * @param bookType
	 * @return
	 */
	public int updateBookTypeDao(BookType bookType) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookTypeDao.class).updateBookTypeDao(bookType);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 判断类型名称是否存在
	 * @param typeName
	 * @return
	 */
	public int judgeTypeNameDao(String typeName) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookTypeDao.class).judgeTypeNameDao(typeName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 添加图书类型
	 * @param bookType
	 * @return
	 */
	public int addBookTypeDao(String typeName) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookTypeDao.class).addBookTypeDao(typeName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 删除图书类型
	 * @param bookType
	 * @return
	 */
	public int deleteBookTypeDao(int typeId) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookTypeDao.class).deleteBookTypeDao(typeId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
