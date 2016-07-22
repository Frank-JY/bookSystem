package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.util.MybatisUtil;

public class BorrowDao {

	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 查询图书名
	 * @param bookId
	 * @return
	 */
	public String findBookNameDao(String bookId) {
		String bookName = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			bookName = session.getMapper(IBorrowDao.class).findBookNameDao(bookId);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return bookName;
	}
	
	/**
	 * 查询学生用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Student findStudentDao(String userName) {
		Student student = new Student();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			student = session.getMapper(IBorrowDao.class).findStudentDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return student;
	}
	
	/**
	 * 查询管理员用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Manager findManagerDao(String userName) {
		Manager manager = new Manager();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			manager = session.getMapper(IBorrowDao.class).findManagerDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return manager;
	}
	
	/**
	 * 查询学生指定图书状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus1Dao(StudentBorrow sborrow) {
		String status = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			status = session.getMapper(IBorrowDao.class).findStatus1Dao(sborrow);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return status;
	}
	
	/**
	 * 查询管理员指定图书借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus2Dao(ManagerBorrow mborrow) {
		String status = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			status = session.getMapper(IBorrowDao.class).findStatus2Dao(mborrow);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return status;
	}
	
	/**
	 * 学生提交借阅申请
	 * @param borrow
	 * @return
	 */
	public int addStudentBorrowDao(StudentBorrow borrow) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).addStudentBorrowDao(borrow);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 管理员提交借阅申请
	 * @param borrow
	 * @return
	 */
	public int addManagerBorrowDao(ManagerBorrow borrow) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).addManagerBorrowDao(borrow);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询学生借阅申请
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> applyStudentBorrowDao() {
		List<StudentBorrow> stBorrowList = new LinkedList<StudentBorrow>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			stBorrowList = session.getMapper(IBorrowDao.class).applyStudentBorrowDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return stBorrowList;
	}
	
	/**
	 * 查询管理员借阅申请
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> applyManagerBorrowDao() {
		List<ManagerBorrow> maBorrowList = new LinkedList<ManagerBorrow>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			maBorrowList = session.getMapper(IBorrowDao.class).applyManagerBorrowDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return maBorrowList;
	}
	
	/**
	 * 查询学生借阅天数
	 * @param id
	 * @return
	 */
	public int findDay1Dao(int id) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).findDay1Dao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询管理员借阅天数
	 * @param id
	 * @return
	 */
	public int findDay2Dao(int id) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).findDay2Dao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询学生借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId1Dao(int id) {
		String bookId = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			bookId = session.getMapper(IBorrowDao.class).findBookId1Dao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return bookId;
	}
	
	/**
	 * 查询管理员借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId2Dao(int id) {
		String bookId = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			bookId = session.getMapper(IBorrowDao.class).findBookId2Dao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return bookId;
	}
	
	/**
	 * 学生借阅申请成功
	 * @param borrow
	 * @return
	 */
	public int passStudentBorrowDao(StudentBorrow borrow) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).passStudentBorrowDao(borrow);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 管理员借阅申请成功
	 * @param borrow
	 * @return
	 */
	public int passManagerBorrowDao(ManagerBorrow borrow) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).passManagerBorrowDao(borrow);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 学生借阅申请失败
	 * @param borrow
	 * @return
	 */
	public int rejectStudentBorrowDao(int id) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).rejectStudentBorrowDao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 管理员借阅申请失败
	 * @param borrow
	 * @return
	 */
	public int rejectManagerBorrowDao(int id) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).rejectManagerBorrowDao(id);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 查询学生个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> myStudentBorrowDao(String userName) {
		List<StudentBorrow> myBorrowList = new LinkedList<StudentBorrow>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			myBorrowList = session.getMapper(IBorrowDao.class).myStudentBorrowDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return myBorrowList;
	}
	
	/**
	 * 查询管理员个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> myManagerBorrowDao(String userName) {
		List<ManagerBorrow> myBorrowList = new LinkedList<ManagerBorrow>();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			myBorrowList = session.getMapper(IBorrowDao.class).myManagerBorrowDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return myBorrowList;
	}
	
	/**
	 * 查询学生借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime1Dao(int id) {
		Date endTime = new Date();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			endTime = session.getMapper(IBorrowDao.class).findEndTime1Dao(id);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return endTime;
	}
	
	/**
	 * 查询管理员借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime2Dao(int id) {
		Date endTime = new Date();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			endTime = session.getMapper(IBorrowDao.class).findEndTime2Dao(id);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return endTime;
	}

	/**
	 * 学生归还图书时间
	 * @param borrow
	 * @return
	 */
	public int back1Dao(StudentBorrow borrow) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).back1Dao(borrow);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 管理员归还图书时间
	 * @param borrow
	 * @return
	 */
	public int back2Dao(ManagerBorrow borrow) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IBorrowDao.class).back2Dao(borrow);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
}
