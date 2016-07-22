package com.yuanjunye.www.service;

import java.util.Date;
import java.util.Map;

import com.yuanjunye.www.dao.BorrowDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.SomeName;
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
		return borrowDao.addStudentBorrowDao(borrow);
	}
	
	/**
	 * 管理员提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addManagerBorrow(ManagerBorrow borrow) {
		return borrowDao.addManagerBorrowDao(borrow);
	}
	
	/**
	 * 查询学生借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus1(String bookId,String userName) {
		return borrowDao.findStatus1Dao(bookId, userName);
	}
	
	/**
	 * 查询管理员借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus2(String bookId,String userName) {
		return borrowDao.findStatus2Dao(bookId, userName);
	}
	
	/**
	 * 查询学生个人借阅信息
	 * @param userName
	 * @return
	 */
	public Map<StudentBorrow,String> myStudentBorrow(String userName) {
		return borrowDao.myStudentBorrowDao(userName);
	}
	
	/**
	 * 查询管理员个人借阅信息
	 * @param userName
	 * @return
	 */
	public Map<ManagerBorrow,String> myManagerBorrow(String userName) {
		return borrowDao.myManagerBorrowDao(userName);
	}
	
	/**
	 * 查询学生借阅申请
	 * @param userName
	 * @return
	 */
	public Map<StudentBorrow,SomeName> applyStudentBorrowDao() {
		return borrowDao.applyStudentBorrowDao();
	}
	
	/**
	 * 查询管理员借阅申请
	 * @param userName
	 * @return
	 */
	public Map<ManagerBorrow,SomeName> applyManagerBorrowDao() {
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
		return borrowDao.passStudentBorrowDao(borrow);
	}
	
	/**
	 * 管理员借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passManagerBorrow(ManagerBorrow borrow) {
		return borrowDao.passManagerBorrowDao(borrow);
	}
	
	/**
	 * 学生借阅申请失败
	 * @param borrow
	 * @return
	 */
	public boolean rejectStudentBorrow(int id) {
		return borrowDao.rejectStudentBorrowDao(id);
	}
	
	/**
	 * 管理员借阅申请失败
	 * @param borrow
	 * @return
	 */
	public boolean rejectManagerBorrow(int id) {
		return borrowDao.rejectManagerBorrowDao(id);
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
	public boolean back1(int id, boolean due, Date inTime) {
		return borrowDao.back1Dao(id, due, inTime);
	}
	
	/**
	 * 管理员归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back2(int id, boolean due, Date inTime) {
		return borrowDao.back2Dao(id, due, inTime);
	}
}
