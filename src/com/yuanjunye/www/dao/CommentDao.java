package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.util.DbUtil;

public class CommentDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	public boolean publishDao(Comment comment) {
		String sql = "insert into t_comment values(null,?,?,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, comment.getBookId());
			fos.setString(2, comment.getUserName());
			fos.setString(3, comment.getComments());
			fos.setTimestamp(4, new java.sql.Timestamp(comment.getTime().getTime()));
			int t = fos.executeUpdate();
			if(t == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return true;
	}
	
	/**
	 * 查询一本图书的评论
	 * @param bookId
	 * @return
	 */
	public List<Comment> bookCommentDao(String bookId) {
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select * from t_comment a, t_user b where a.userName = b.userName and a.bookId = ? ORDER BY a.id";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setBookId(rs.getString("bookId"));
				comment.setUserName(rs.getString("userName"));
				comment.setComments(rs.getString("comments"));
				comment.setIdentity(rs.getString("identity"));
				comment.setTime(rs.getTimestamp("time"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(rs, fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return commentList;
	}
	
	/**
	 * 管理员删除评论
	 * @param id
	 * @return
	 */
	public boolean deleteCommentDao(int id) {
		String sql = "delete from t_comment where id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			int t = fos.executeUpdate();
			if(t == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return true;
	}
	
	/**
	 * 查询个人评论
	 * @param userName
	 * @return
	 */
	public List<Comment> myCommentDao(String userName) {
		List<Comment> myCommentList = new ArrayList<Comment>();
		String sql = "select * from t_comment a, t_user b where a.userName = b.userName and a.userName = ? ORDER BY a.id";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("id"));
				comment.setBookId(rs.getString("bookId"));
				comment.setUserName(rs.getString("userName"));
				comment.setComments(rs.getString("comments"));
				comment.setIdentity(rs.getString("identity"));
				comment.setTime(rs.getTimestamp("time"));
				myCommentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(rs, fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myCommentList;
	} 
	
	/**
	 * 用户删除个人评论
	 * @param id
	 * @return
	 */
	public boolean deleteComment1Dao(int id, String userName) {
		String sql = "delete from t_comment where id = ? and userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			fos.setString(2, userName);
			int t = fos.executeUpdate();
			if(t == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DbUtil.close(fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return true;
	}
}
