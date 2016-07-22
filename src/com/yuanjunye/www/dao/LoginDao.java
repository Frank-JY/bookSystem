package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.DbUtil;

public class LoginDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public boolean verifyUserDao(User user){
		String sql = "select * from t_user where userName = ? and password = ? and identity = ? "
				+ "and status = '已通过申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, user.getUserName());
			fos.setString(2, user.getPassword());
			fos.setString(3, user.getIdentity());
			rs = fos.executeQuery();
			if(rs.next()) {
				return true;
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
		return false;
	}
}
