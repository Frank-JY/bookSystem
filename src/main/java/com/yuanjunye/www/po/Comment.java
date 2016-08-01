package com.yuanjunye.www.po;

import java.util.Date;
import java.util.List;

public class Comment{
	
	private int id;
	private String bookId;
	private String userName;
	private String comments;
	private String identity;
	private String photo;
	private Date time;
	private Books book;
	
	public Books getBook() {
		return book;
	}


	public void setBook(Books book) {
		this.book = book;
	}


	public Comment() {
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getIdentity() {
		return identity;
	}


	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}
	
}
