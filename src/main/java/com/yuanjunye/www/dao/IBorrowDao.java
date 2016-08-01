package com.yuanjunye.www.dao;

import java.util.Date;
import java.util.List;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;

public interface IBorrowDao {

	/**
	 * 查询学生用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Student findStudentDao(String userName);
	
	/**
	 * 查询管理员用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Manager findManagerDao(String userName);
	
	/**
	 * 查询图书名
	 * @param bookId
	 * @return
	 */
	public String findBookNameDao(String bookId);
	
	/**
	 * 学生提交借阅申请
	 * @param borrow
	 * @return
	 */
	public int addStudentBorrowDao(StudentBorrow borrow);
	
	/**
	 * 管理员提交借阅申请
	 * @param borrow
	 * @return
	 */
	public int addManagerBorrowDao(ManagerBorrow borrow);
	
	/**
	 * 查询学生指定图书状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus1Dao(StudentBorrow sborrow);
	
	/**
	 * 查询管理员指定图书借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus2Dao(ManagerBorrow mborrow);
	
	/**
	 * 查询学生个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> myStudentBorrowDao(String userName);
	
	/**
	 * 查询管理员个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> myManagerBorrowDao(String userName);
	
	/**
	 * 查询学生借阅申请
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> applyStudentBorrowDao();
	
	/**
	 * 查询管理员借阅申请
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> applyManagerBorrowDao();
	
	/**
	 * 查询学生借阅天数
	 * @param id
	 * @return
	 */
	public int findDay1Dao(int id);
	
	/**
	 * 查询管理员借阅天数
	 * @param id
	 * @return
	 */
	public int findDay2Dao(int id);
	
	/**
	 * 学生借阅申请成功
	 * @param borrow
	 * @return
	 */
	public int passStudentBorrowDao(StudentBorrow borrow);
	
	/**
	 * 管理员借阅申请成功
	 * @param borrow
	 * @return
	 */
	public int passManagerBorrowDao(ManagerBorrow borrow);
	
	/**
	 * 学生借阅申请失败
	 * @param borrow
	 * @return
	 */
	public int rejectStudentBorrowDao(int id);
	
	/**
	 * 管理员借阅申请失败
	 * @param borrow
	 * @return
	 */
	public int rejectManagerBorrowDao(int id);
	
	/**
	 * 查询学生借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId1Dao(int id);
	
	/**
	 * 查询管理员借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId2Dao(int id);
	
	/**
	 * 查询学生借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime1Dao(int id);
	
	/**
	 * 查询管理员借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime2Dao(int id);
	
	/**
	 * 学生归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public int back1Dao(StudentBorrow borrow);
	
	/**
	 * 管理员归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public int back2Dao(ManagerBorrow borrow);
}
