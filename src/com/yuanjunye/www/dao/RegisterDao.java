package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.DbUtil;

public class RegisterDao {
	
	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 添加用户学信息
	 * @param user
	 * @return
	 */
	public boolean addUserDao(User user) {
		String sql = "insert into t_user values(null,?,?,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, user.getUserName());
			fos.setString(2, user.getPassword());
			fos.setString(3, user.getIdentity());
			fos.setString(4, "等待申请");
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
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	public boolean addStudentDao(Student student) {
		String sql = "insert into t_student(studentId,studentName,userName,email,photo) values(?,?,?,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setLong(1, student.getStudentId());
			fos.setString(2, student.getStudentName());
			fos.setString(3, student.getUserName());
			fos.setString(4, student.getEmail());
			fos.setString(5, student.getPhoto());
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
	 * 添加管理员信息
	 * @param manager
	 * @return
	 */
	public boolean addManagerDao(Manager manager) {
		String sql = "insert into t_manager(managerId,managerName,userName,email,photo) values(?,?,?,?,?)";	
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, manager.getManagerId());
			fos.setString(2, manager.getManagerName());
			fos.setString(3, manager.getUserName());
			fos.setString(4, manager.getEmail());
			fos.setString(5, manager.getPhoto());
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
	 * 判断用户名是否已被注册
	 * @param userName
	 * @return
	 */
	public boolean checkUserNameDao(String userName) {
		String sql = "select * from t_user where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
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
	
	/**
	 * 判断学生学会是否被使用
	 * @param studentId
	 * @return
	 */
	public boolean checkStudentIdDao(long studentId) {
		String sql1 = "select * from t_student where studentId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql1);
			fos.setLong(1, studentId);
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
	
	/**
	 * 判断管理员职工号是否被使用
	 * @param managerId
	 * @return
	 */
	public boolean checkManagerIdDao(int managerId) {
		String sql1 = "select * from t_manager where managerId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql1);
			fos.setInt(1, managerId);
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
	
	/**
	 * 判断邮箱是否被使用
	 * @param email
	 * @return
	 */
	public boolean checkEmailDao(String email) {
		String sql1 = "select * from t_student where email = ?";
		String sql2 = "select * from t_manager where email = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql1);
			fos.setString(1, email);
			rs = fos.executeQuery();
			if(rs.next()) {
				return true;
			}
			fos = conn.prepareStatement(sql2);
			fos.setString(1, email);
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
