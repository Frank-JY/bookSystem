package com.yuanjunye.www.service;

import java.util.Date;
import java.util.List;

import com.yuanjunye.www.dao.BorrowDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;

public class BorrowService {

	private BorrowDao borrowDao = new BorrowDao();
	
	/**
	 * 查询学生用户的姓名学号  
	 * @param userName
	 * @return
	 */
	public Student findStudent(String userName) {
		return borrowDao.findStudentDao(userName);
	}
	
	/**
	 * 查询管理员用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Manager findManager(String userName) {
		return borrowDao.findManagerDao(userName);
	}
	
	/**
	 * 查询图书名
	 * @param bookId
	 * @return
	 */
	public String findBookName(String bookId) {
		return borrowDao.findBookNameDao(bookId);
	}
	
	/**
	 * 学生提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addStudentBorrow(StudentBorrow borrow) {
		boolean bool = true;
		int t = borrowDao.addStudentBorrowDao(borrow);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 管理员提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addManagerBorrow(ManagerBorrow borrow) {
		boolean bool = true;
		int t = borrowDao.addManagerBorrowDao(borrow);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询学生借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus1(StudentBorrow sborrow) {
		String status =  borrowDao.findStatus1Dao(sborrow);
		if(null == status) {
			status = "";
		}
		return status;
	}
	
	/**
	 * 查询管理员借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus2(ManagerBorrow mborrow) {
		String status =  borrowDao.findStatus2Dao(mborrow);
		if(null == status) {
			status = "";
		}
		return status;
	}
	
	/**
	 * 查询学生个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> myStudentBorrow(String userName) {
		return borrowDao.myStudentBorrowDao(userName);
	}
	
	/**
	 * 查询管理员个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> myManagerBorrow(String userName) {
		return borrowDao.myManagerBorrowDao(userName);
	}
	
	/**
	 * 查询学生借阅申请
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> applyStudentBorrowDao() {
		return borrowDao.applyStudentBorrowDao();
	}
	
	/**
	 * 查询管理员借阅申请
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> applyManagerBorrowDao() {
		return borrowDao.applyManagerBorrowDao();
	}
	
	/**
	 * 查询学生借阅天数
	 * @param id
	 * @return
	 */
	public int findDay1(int id) {
		return borrowDao.findDay1Dao(id);
	}
	
	/**
	 * 查询管理员借阅天数
	 * @param id
	 * @return
	 */
	public int findDay2(int id) {
		return borrowDao.findDay2Dao(id);
	}
	
	/**
	 * 学生借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passStudentBorrow(StudentBorrow borrow) {
		boolean bool = true;
		int t = borrowDao.passStudentBorrowDao(borrow);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 管理员借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passManagerBorrow(ManagerBorrow borrow) {
		boolean bool = true;
		int t = borrowDao.passManagerBorrowDao(borrow);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 学生借阅申请失败
	 * @param borrow
	 * @return
	 */
	public boolean rejectStudentBorrow(int id) {
		boolean bool = true;
		int t = borrowDao.rejectStudentBorrowDao(id);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 管理员借阅申请失败
	 * @param borrow
	 * @return
	 */
	public boolean rejectManagerBorrow(int id) {
		boolean bool = true;
		int t = borrowDao.rejectManagerBorrowDao(id);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询学生借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId1(int id) {
		return borrowDao.findBookId1Dao(id);
	}
	
	/**
	 * 查询管理员借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId2(int id) {
		return borrowDao.findBookId2Dao(id);
	}
	
	/**
	 * 查询学生借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime1(int id) {
		return borrowDao.findEndTime1Dao(id);
	}
	
	/**
	 * 查询管理员借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime2(int id) {
		return borrowDao.findEndTime2Dao(id);
	}
	
	/**
	 * 学生归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back1(StudentBorrow borrow) {
		boolean bool = true;
		int t = borrowDao.back1Dao(borrow);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 管理员归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back2(ManagerBorrow borrow) {
		boolean bool = true;
		int t = borrowDao.back2Dao(borrow);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
}
