package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.yuanjunye.www.po.Friend;
import com.yuanjunye.www.util.DbUtil;

public class FriendDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 好友申请
	 * @param friend
	 * @return
	 */
	public boolean addFriendDao(Friend friend) {
		String sql = "insert into t_friends values(null,?,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, friend.getMyUserName());
			fos.setString(2, friend.getFriendUserName());
			fos.setString(3, friend.getStatus());
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
	 * 查询状态
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public String findStatusDao(String myUserName,String friendUserName){
		String status = "";
		String sql = "select status from t_friends where myUserName = ? and friendUserName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, myUserName);
			fos.setString(2, friendUserName);
			rs = fos.executeQuery();
			if(rs.next()) {
				status = rs.getString("status");
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
		return status;
	}
	
	/**
	 * 查询个人好友
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showFriendDao(String myUserName) {
		List<Friend> friendList = new LinkedList<Friend>();
		String sql = "select * from t_friends a, t_user b where a.friendUserName = b.userName and a.status = '已通过申请' and myUserName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, myUserName);
			rs = fos.executeQuery();
			while(rs.next()) {
				Friend friend = new Friend();
				friend.setMyUserName(rs.getString("myUserName"));
				friend.setFriendUserName(rs.getString("friendUserName"));
				friend.setIdentity(rs.getString("identity"));
				friendList.add(friend);
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
		return friendList;
	}
	
	/**
	 * 查看好友申请
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showApplyFriendDao(String myUserName) {
		List<Friend> applyFriendList = new LinkedList<Friend>();
		String sql = "select * from t_friends a, t_user b where a.myUserName = b.userName and a.status = '等待申请' and friendUserName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, myUserName);
			rs = fos.executeQuery();
			while(rs.next()) {
				Friend friend = new Friend();
				friend.setMyUserName(rs.getString("myUserName"));
				friend.setFriendUserName(rs.getString("friendUserName"));
				friend.setIdentity(rs.getString("identity"));
				applyFriendList.add(friend);
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
		return applyFriendList;
	}
	
	/**
	 * 不同意好友申请/删除
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean rejectFriendDao(String myUserName,String friendUserName) {
		String sql = "delete from t_friends where myUserName = ? and friendUserName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, myUserName);
			fos.setString(2, friendUserName);
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
	 * 通过申请
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean updateStatusDao(String myUserName,String friendUserName) {
		String sql = "update t_friends set status = ? where myUserName = ? and friendUserName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, "已通过申请");
			fos.setString(2, myUserName);
			fos.setString(3, friendUserName);
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
