package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.po.Comment;

public interface ICommentService {

	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	public boolean publish(Comment comment,String userName);
	
	/**
	 * 查询一本图书的评论
	 * @param bookId
	 * @return
	 */
	public List<Comment> bookComment(String bookId);
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */ 
	public boolean deleteComment(int id);
	
	/**
	 * 查询个人评论
	 * @param userName
	 * @return
	 */
	public List<Comment> myComment(String userName);
	
	/**
	 * 用户删除个人评论
	 * @param id
	 * @return
	 */
	public boolean deleteComment1(Comment comment);
}
