package com.yuanjunye.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuanjunye.www.po.Comment;

public interface ICommentDao {

	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	public int publishDao(Comment comment);
	
	/**
	 * 查询一本图书的评论
	 * @param bookId
	 * @return
	 */
	public List<Comment> bookCommentDao(@Param("bookId")String bookId,@Param("fromIndex")int fromIndex, @Param("pageSize")int pageSize);
	
	/**
	 * 查询一本图书的评论数量
	 * @param bookId
	 * @return
	 */
	public int findbookCommentNumberDao(String bookId);
	
	
	/**
	 * 管理员删除评论
	 * @param id
	 * @return
	 */
	public int deleteCommentDao(int id);
	
	/**
	 * 查询个人评论
	 * @param userName
	 * @return
	 */
	public List<Comment> myCommentDao(@Param("userName")String userName,@Param("fromIndex")int fromIndex, @Param("pageSize")int pageSize);
	
	/**
	 * 用户删除个人评论
	 * @param id
	 * @return
	 */
	public int deleteComment1Dao(Comment comment);
	
	/**
	 * 查询个人评论数量
	 * @param userName
	 * @return
	 */
	public int findMyCommentNumberDao(String userName);
}
