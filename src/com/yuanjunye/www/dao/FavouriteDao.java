package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yuanjunye.www.po.Favourite;
import com.yuanjunye.www.util.DbUtil;

public class FavouriteDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs;
	
	/**
	 * 检查用户是否可以收藏(数量少于等于10本)
	 * @param userName
	 * @return
	 */
	public boolean isCollectDao(String userName) {
		String sql = "select * from t_favourite where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			int i = 0;
			while(rs.next()) {
				i++;
			}
			if(i >= 10) {
				return false;
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
		return true;
	}
	
	/**
	 * 检查用户是否重复收藏图书
	 * @param userName
	 * @param bookId
	 * @return
	 */
	public boolean isRepeatDao(Favourite favourite) {
		String sql = "select * from t_favourite where userName = ? and bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, favourite.getUerName());
			fos.setString(2, favourite.getBookId());
			rs = fos.executeQuery();
			while(rs.next()) {
				return false;
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
		return true;
	}
	
	/**
	 * 插入用户收藏图书信息
	 * @param favourite
	 * @return
	 */
	public boolean collectDao(Favourite favourite) {
		String sql = "insert into t_favourite values(null,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, favourite.getUerName());
			fos.setString(2, favourite.getBookId());
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
	 * 获取用户收藏夹图书信息
	 * @param userName
	 * @return
	 */
	public List<String> findCollectBookIdDao(String userName) {
		List<String> bookIdList = new ArrayList<String>();
		String sql = "select * from t_favourite where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			while(rs.next()) {
				String bookId = rs.getString("bookId");
				bookIdList.add(bookId);
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
		return bookIdList;
	}
	
	/**
	 * 删除收藏夹图书信息
	 * @param favourite
	 * @return
	 */
	public boolean deleteFavouriteDao(Favourite favourite) {
		String sql = "delete from t_favourite where userName = ? and bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, favourite.getUerName());
			fos.setString(2, favourite.getBookId());
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
