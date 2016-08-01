package com.yuanjunye.www.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuanjunye.www.dto.BorrowUser;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;

public interface IBorrowService {

	/**
	 * 查询学生用户的姓名学号  
	 * @param userName
	 * @return
	 */
	public Student findStudent(String userName);
	
	/**
	 * 查询管理员用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Manager findManager(String userName);
	
	/**
	 * 查询图书名
	 * @param bookId
	 * @return
	 */
	public String findBookName(String bookId);
	
	/**
	 * 学生提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addStudentBorrow(StudentBorrow borrow);
	
	/**
	 * 管理员提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addManagerBorrow(ManagerBorrow borrow);
	
	/**
	 * 查询学生借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus1(StudentBorrow sborrow);
	
	/**
	 * 查询管理员借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus2(ManagerBorrow mborrow);
	
	/**
	 * 查询学生个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<StudentBorrow> myStudentBorrow(String userName);
	
	/**
	 * 查询管理员个人借阅信息
	 * @param userName
	 * @return
	 */
	public List<ManagerBorrow> myManagerBorrow(String userName);
	
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
	public int findDay1(int id);
	
	/**
	 * 查询管理员借阅天数
	 * @param id
	 * @return
	 */
	public int findDay2(int id);
	
	/**
	 * 学生借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passStudentBorrow(StudentBorrow borrow);
	
	/**
	 * 管理员借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passManagerBorrow(ManagerBorrow borrow);
	
	
	
	/**
	 * 查询学生借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId1(int id);
	
	/**
	 * 查询管理员借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId2(int id);
	
	/**
	 * 查询学生借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime1(int id);
	
	/**
	 * 查询管理员借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime2(int id);
	
	/**
	 * 学生归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back1(StudentBorrow borrow);
	
	/**
	 * 管理员归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back2(ManagerBorrow borrow);
	
	/**
	 * 更新
	 * @param book
	 * @param stBorrow
	 * @param maBorrow
	 * @param identity
	 * @return
	 */
	public boolean updateAll(String bookId, StudentBorrow stBorrow, ManagerBorrow maBorrow, String identity,String readerId);
	
	/**
	 * 传递借阅信息
	 * @param bookId
	 * @param identity
	 * @param userName
	 * @return
	 */
	public BorrowUser showBorrowUserMessage(String bookId, String identity,String userName);
	
	/**
	 * 同意图书借阅申请
	 * @param identity
	 * @param id
	 * @return
	 */
	public boolean passBorrow(String identity,Integer id);
	
	/**
	 * 不同意图书借阅申请
	 * @param identity
	 * @param id
	 * @return
	 */
	public boolean rejectBorrow(String identity,Integer id);
	
	/**
	 * 归还图书
	 * @param identity
	 * @param id
	 * @return
	 */
	public boolean backBook(String identity,Integer id);
	
}
