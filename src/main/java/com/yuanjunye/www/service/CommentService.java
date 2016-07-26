package com.yuanjunye.www.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuanjunye.www.dao.IBookDao;
import com.yuanjunye.www.dao.ICommentDao;
import com.yuanjunye.www.dao.IUserDao;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Comment;

@Service
public class CommentService implements ICommentService{
	@Resource
	private ICommentDao commentDAO;
	@Resource
	private IUserDao userDao;
	@Resource
	private IBookDao bookDao;
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	public boolean publish(Comment comment,String userName) {
		boolean bool = true;
		Date time = new Date();
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentTime  = new Date();
			String s = df.format(currentTime);
			time = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		comment.setTime(time);
		comment.setUserName(userName);
		int t = commentDAO.publishDao(comment);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询一本图书的评论
	 * @param bookId
	 * @return
	 */
	public List<Comment> bookComment(String bookId) {
		List<Comment> commentList = commentDAO.bookCommentDao(bookId);
		for(Comment comment : commentList) {
			String commentphoto = "";
			if(comment.getIdentity().equals("学生")) {
					commentphoto = userDao.findStudentPhotoDao(comment.getUserName());		
				}else {
					commentphoto = userDao.findManagerPhotoDao(comment.getUserName());
				}
			comment.setPhoto(commentphoto);
		}
		return commentList;
	}
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */ 
	public boolean deleteComment(int id) {
		boolean bool = true;
		int t = commentDAO.deleteCommentDao(id);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询个人评论
	 * @param userName
	 * @return
	 */
	public List<Comment> myComment(String userName) {
		List<Comment> myCommentList = commentDAO.myCommentDao(userName);
		for(Comment comment : myCommentList) {
			Books book  = bookDao.findBookDao(comment.getBookId());
			comment.setBook(book);
			String commentphoto = "";
			if(comment.getIdentity().equals("学生")) {
					commentphoto = userDao.findStudentPhotoDao(comment.getUserName());
				}else {
					commentphoto = userDao.findManagerPhotoDao(comment.getUserName());
				}
			comment.setPhoto(commentphoto);
		}
		Collections.reverse(myCommentList);
		return myCommentList;
	}
	
	/**
	 * 用户删除个人评论
	 * @param id
	 * @return
	 */
	public boolean deleteComment1(Comment comment) {
		boolean bool = true;
		int t = commentDAO.deleteComment1Dao(comment);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
}
