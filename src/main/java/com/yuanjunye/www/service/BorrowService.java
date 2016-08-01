package com.yuanjunye.www.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanjunye.www.dao.IBookDao;
import com.yuanjunye.www.dao.IBorrowDao;
import com.yuanjunye.www.dto.BorrowUser;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;

@Service
@Transactional
public class BorrowService implements IBorrowService{
	
	@Resource
	private IBorrowDao borrowDao;
	@Resource
	private IBookDao bookDao;
	
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
	
	/**
	 * 更新
	 * @param book
	 * @param stBorrow
	 * @param maBorrow
	 * @param identity
	 * @return
	 */
	@Transactional
	public boolean updateAll(String bookId, StudentBorrow stBorrow, ManagerBorrow maBorrow, String identity,String readerId) {
		boolean bool = true;
		int t1 = 0;
		int t2 = 0;
		//申请时间
		Date applyTime = new Date();
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime  = new Date();
			String s = df.format(currentTime);
			applyTime = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int amount = bookDao.findAmountDao(bookId);
		amount--;
		String newStatus = "";
		if(amount == 0) {
			newStatus = "无库存";
		}else {
			newStatus = "可借阅";
		}
		Books book = new Books();
		book.setBookId(bookId);
		book.setAmount(amount);
		book.setStatus(newStatus);
		if(identity.equals("学生")) {
			long studentId = Long.valueOf(readerId);
			stBorrow.setReaderId(studentId);
			stBorrow.setApplyTime(applyTime);
			t1 = borrowDao.addStudentBorrowDao(stBorrow);
			t2 = bookDao.updateAmountDao(book);			
		}else {
			int managerId = Integer.valueOf(readerId);		
			maBorrow.setReaderId(managerId);
			maBorrow.setApplyTime(applyTime);
			t1 = borrowDao.addManagerBorrowDao(maBorrow);
			t2 = bookDao.updateAmountDao(book);		
		}
		if(t1==0 || t2==0) {
			bool = false;
		}
		return bool;
	}

	/**
	 * 传递借阅信息
	 * @param bookId
	 * @param identity
	 * @param userName
	 * @return
	 */
	@Override
	public BorrowUser showBorrowUserMessage(String bookId, String identity,String userName) {
		BorrowUser borrowUser = new BorrowUser();
		String bookName = borrowDao.findBookNameDao(bookId);
		if(identity.equals("学生")) {
			Student student = borrowDao.findStudentDao(userName);
			String studentName = student.getStudentName();
			long studentId = student.getStudentId();
			borrowUser.setName(studentName);
			borrowUser.setReaderId(String.valueOf(studentId));
		}else {
			Manager manager = borrowDao.findManagerDao(userName);
			String managerName = manager.getManagerName();
			int managerId = manager.getManagerId();
			borrowUser.setName(managerName);
			borrowUser.setReaderId(String.valueOf(managerId));
		}
		borrowUser.setUserName(userName);
		borrowUser.setIdentity(identity);
		borrowUser.setBookName(bookName);
		return borrowUser;
	}

	/**
	 * 同意图书借阅申请
	 */
	@Override
	@Transactional
	public boolean passBorrow(String identity, Integer id) {
		int day = 0;
		if(identity.equals("student")) {
			day = borrowDao.findDay1Dao(id);
		}else {
			day = borrowDao.findDay2Dao(id);
		}
		//当前日期加上借阅天数
		Calendar c=Calendar.getInstance();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Date endTime = new Date();
		Date outTime = new Date();
		try {
		c.setTime(now);  
		c.add(Calendar.DATE,day);   
		Date end=c.getTime();   
		String ends = df.format(end); 
		String outs = df.format(now);
		endTime = df.parse(ends);
		outTime = df.parse(outs);
		} catch (ParseException e) {
			e.printStackTrace();
		}				
		if(identity.equals("student")) {	
			StudentBorrow borrow = new StudentBorrow();
			borrow.setId(id);
			borrow.setEndTime(endTime);
			borrow.setOutTime(outTime);
			String bookId = borrowDao.findBookId1Dao(id);
			int t1 = bookDao.updateNumberDao(bookId);
			int t2 = borrowDao.passStudentBorrowDao(borrow);
			if(t1 != 0 && t2 != 0) {
				return true;
			}
		}else if(identity.equals("manager")){
			ManagerBorrow borrow = new ManagerBorrow();
			borrow.setId(id);
			borrow.setEndTime(endTime);
			borrow.setOutTime(outTime);
			String bookId = borrowDao.findBookId2Dao(id);		
			int t1 = bookDao.updateNumberDao(bookId);
			int t2 = borrowDao.passManagerBorrowDao(borrow);
			if(t1 != 0 && t2 != 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 不同意图书借阅申请
	 * @param identity
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean rejectBorrow(String identity,Integer id) {
		if(identity.equals("student")) {
			int t1 = borrowDao.rejectStudentBorrowDao(id);
			String bookId = borrowDao.findBookId1Dao(id);                                              //库存加1
			String status = bookDao.findStatusDao(bookId);
			if(status.equals("已下架")) {
				status = "已下架";
			}else if(status.equals("无库存")){
				status = "可借阅";
			}else if(status.equals("可借阅")) {
				status = "可借阅";
			}
			Books book = new Books();
			book.setBookId(bookId);
			book.setStatus(status);
			int t2 = bookDao.updateAmountDao(book);
			if(t1 != 0 && t2 != 0) {
				return true;
			}
		}else if(identity.equals("manager")){
			int t1 = borrowDao.rejectManagerBorrowDao(id);
			String bookId = borrowDao.findBookId2Dao(id);                                                //库存加1
			String status = bookDao.findStatusDao(bookId);
			if(status.equals("已下架")) {
				status = "已下架";
			}else if(status.equals("无库存")){
				status = "可借阅";
			}else if(status.equals("可借阅")) {
				status = "可借阅";
			}
			Books book = new Books();
			book.setBookId(bookId);
			book.setStatus(status);
			int t2 = bookDao.updateAmountDao(book);
			if(t1 != 0 && t2 != 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 归还图书
	 * @param identity
	 * @param id
	 * @return
	 */
	public boolean backBook(String identity,Integer id) {
		Date inTime = new Date();
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime  = new Date();
			String s = df.format(currentTime);
			inTime = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		if(identity.equals("student")) {
			Date endTime = borrowDao.findEndTime1Dao(id);
			boolean due = inTime.after(endTime);
			StudentBorrow borrow = new StudentBorrow();
			borrow.setId(id);
			borrow.setDue(due);
			borrow.setInTime(inTime);
			int t1 = borrowDao.back1Dao(borrow);
			String bookId = borrowDao.findBookId1Dao(id);                                              //库存加1
			String status = bookDao.findStatusDao(bookId);
			if(status.equals("已下架")) {
				status = "已下架";
			}else if(status.equals("无库存")){
				status = "可借阅";
			}else if(status.equals("可借阅")) {
				status = "可借阅";
			}
			Books book = new Books();
			book.setBookId(bookId);
			book.setStatus(status);
			int t2 = bookDao.updateAmountDao(book);
			if(t1 != 0 && t2 != 0) {
				return true;
			}
		}else if(identity.equals("manager")) {
			Date endTime = borrowDao.findEndTime2Dao(id);
			boolean due = inTime.after(endTime);
			ManagerBorrow borrow = new ManagerBorrow();
			borrow.setId(id);
			borrow.setDue(due);
			borrow.setInTime(inTime);
			int t1 = borrowDao.back2Dao(borrow);
			String bookId = borrowDao.findBookId2Dao(id);                                             //库存加1
			String status = bookDao.findStatusDao(bookId);
			if(status.equals("已下架")) {
				status = "已下架";
			}else if(status.equals("无库存")){
				status = "可借阅";
			}else if(status.equals("可借阅")) {
				status = "可借阅";
			}
			Books book = new Books();
			book.setBookId(bookId);
			book.setStatus(status);
			int t2 = bookDao.updateAmountDao(book);
			if(t1 != 0 && t2 != 0) {
				return true;
			}
		}
		return false;
	}
	
}
