package com.yuanjunye.www.po;

import java.util.Date;

/**
 * 学生借阅信息模型
 * @author hasee
 *
 */
public class StudentBorrow {

	private int id;
	private String bookId;
	private long readerId;
	private String userName;
	private int day;
	private Date applyTime;
	private Date outTime;
	private Date endTime;
	private Date inTime;
	private boolean due;
	private String status;
	private String bookName;
	private String readerName;
	
	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getReaderName() {
		return readerName;
	}


	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}


	/**
	 * 无参构造
	 */
	public StudentBorrow() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public long getReaderId() {
		return readerId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setReaderId(long readerId) {
		this.readerId = readerId;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public Date getApplyTime() {
		return applyTime;
	}


	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}


	public Date getOutTime() {
		return outTime;
	}


	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Date getInTime() {
		return inTime;
	}


	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}


	public boolean isDue() {
		return due;
	}


	public void setDue(boolean due) {
		this.due = due;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
