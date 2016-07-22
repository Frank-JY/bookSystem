package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Param2;
import com.yuanjunye.www.util.MybatisUtil;

/**
 * @author hasee
 *
 */
public class BookDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 显示所有图书信息(无顺序)
	 * @return
	 */
	public Set<Books> showAllBooks1Dao() {
		Set<Books> booksSet = new HashSet<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			booksSet = session.getMapper(IBookDao.class).showAllBooks1Dao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return booksSet;
	}
	
	/**
	 * 显示所有图书信息（有顺序）
	 * @return
	 */
	public List<Books> showAllBooksDao() {
		List<Books> booksList = new LinkedList<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			booksList = session.getMapper(IBookDao.class).showAllBooksDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return booksList;
	}
	
	/**
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public int judgeBookIdDao(String bookId) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).judgeBookIdDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public int addBookDao(Books book) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).addBookDao(book);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询图书库存
	 * @param bookId
	 * @return
	 */
	public int findAmountDao(String bookId) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).findAmountDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public int inBookDao(Books book) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).inBookDao(book);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public int outBookDao(String bookId) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).outBookDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books showBookDao(String bookId) {
		Books book = new Books();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			book = session.getMapper(IBookDao.class).showBookDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return book;
	}
	
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public int updateBookDao(Books book) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).updateBookDao(book);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public int updatePhotoDao(Books book) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).updatePhotoDao(book);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public int deleteBookDao(String bookId){
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).deleteBookDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询图书总数和库存
	 * @param bookId
	 * @return
	 */
	public Books checkAmountTotalDao(String bookId) {
		Books book = new Books();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			book = session.getMapper(IBookDao.class).checkAmountTotalDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return book;
	}
	
	/**
	 * 模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooksDao(String keyword) {
		List<Books> booksList = new LinkedList<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			booksList = session.getMapper(IBookDao.class).searchBooksDao(keyword);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return booksList;
	}
	
	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public List<Books> searchBooks1Dao(Param2 param) {
		List<Books> booksList = new LinkedList<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			booksList = session.getMapper(IBookDao.class).searchBooks1Dao(param);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return booksList;
	}
	
	/**
	 * 每日新书
	 * @param time
	 * @return
	 */
	public List<Books> newBookDao(Date time) {
		List<Books> newBookList = new LinkedList<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			newBookList = session.getMapper(IBookDao.class).newBookDao(time);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return newBookList;
	}
	
	/**
	 * 热门图书推介
	 * @return
	 */
	public List<Books> hotBookDao() {
		List<Books> hotBookList = new LinkedList<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			hotBookList = session.getMapper(IBookDao.class).hotBookDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return hotBookList;
	}
	
	/**
	 * 根据图书类型查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooks2Dao(String typeName) {
		List<Books> searchBooksList = new LinkedList<Books>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			searchBooksList = session.getMapper(IBookDao.class).searchBooks2Dao(typeName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return searchBooksList;
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books findBookDao(String bookId) {
		Books book = new Books();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			book = session.getMapper(IBookDao.class).findBookDao(bookId);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return book;
	}
	
	/**
	 * 查询图书状态
	 * @param bookId
	 * @return
	 */
	public String findStatusDao(String bookId) {
		String status = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			status = session.getMapper(IBookDao.class).findStatusDao(bookId);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return status;
	}
	
	/**
	 * 图书操作，改变库存
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public int updateAmountDao(Books book) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).updateAmountDao(book);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询图书借阅次数
	 * @param bookId
	 * @return
	 */
	public int findNumberDao(String bookId) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).findNumberDao(bookId);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 通过借阅申请，借阅次数加一
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public int updateNumberDao(Books book) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBookDao.class).updateNumberDao(book);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
