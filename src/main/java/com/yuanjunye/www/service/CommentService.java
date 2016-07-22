package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.dao.CommentDao;
import com.yuanjunye.www.po.Comment;

public class CommentService {

	private CommentDao commentDAO = new CommentDao();
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	public boolean publish(Comment comment) {
		boolean bool = true;
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
		return commentDAO.bookCommentDao(bookId);
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
		return commentDAO.myCommentDao(userName);
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
