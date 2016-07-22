package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.util.DbUtil;

public class AdviceDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 添加意见反馈
	 * @param advice
	 * @return
	 */
	public boolean addAdviceDao(Advice advice) {
		String sql = "insert into t_advice values(null,?,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, advice.getUserName());
			fos.setString(2, advice.getAdvice());
			fos.setTimestamp(3, new java.sql.Timestamp(advice.getTime().getTime()));
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
	 * 查询所有意见反馈
	 * @return
	 */
	public List<Advice> ShowAdviceDao() {
		List<Advice> adviceList = new LinkedList<Advice>();
		String sql = "select * from t_advice a, t_user b where a.userName = b.userName order by a.id";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Advice advice = new Advice();
				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String identity = rs.getString("identity");
				String advices = rs.getString("advice");
				Date time = rs.getTimestamp("time");
				advice.setId(id);
				advice.setUserName(userName);
				advice.setIdentity(identity);
				advice.setAdvice(advices);
				advice.setTime(time);
				adviceList.add(advice);
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
		return adviceList;
	}
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public boolean deleteAdviceDao(int id) {
		String sql = "delete from t_advice where id = ?";
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
	
	
	
	
}
