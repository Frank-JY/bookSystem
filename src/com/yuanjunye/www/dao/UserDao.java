package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.DbUtil;

public class UserDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 显示等待申请的学生
	 * @return
	 */
	public Map<Student,User> showApplyStudentDao() {
		Map<Student,User> studentMap = new LinkedHashMap<Student,User>();
		String sql = "select * from t_user a,t_student b where a.userName = b.userName and status = '等待申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				User user = new User();
				student.setStudentId(rs.getLong("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setStatus(rs.getString("status"));
				user.setIdentity(rs.getString("identity"));
				studentMap.put(student, user);
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
		return studentMap;
	}
	
	/**
	 * 显示等待申请的管理员
	 * @return
	 */
	public Map<Manager,User> showApplyManagerDao() {
		Map<Manager,User> managerMap = new LinkedHashMap<Manager,User>();
		String sql = "select * from t_user a,t_manager b where a.userName = b.userName and status = '等待申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				User user = new User();
				manager.setManagerId(rs.getInt("managerId"));
				manager.setManagerName(rs.getString("managerName"));
				manager.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setStatus(rs.getString("status"));
				user.setIdentity(rs.getString("identity"));
				managerMap.put(manager, user);
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
		return managerMap;
	}
	
	/**
	 * 同意用户注册申请
	 * @param userName
	 * @return
	 */
	public boolean passUserDao(String userName) {
		String sql = "update t_user set status = '已通过申请' where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
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
	 * 不同意用户注册申请
	 * @param userName
	 * @return
	 */
	public boolean rejectUserDao(String userName) {
		String sql = "delete from t_user where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
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
	 * 显示通过申请的学生
	 * @return
	 */
	public Map<Student,User> showAllStudentDao() {
		Map<Student,User> studentMap = new LinkedHashMap<Student,User>();
		String sql = "select * from t_user a,t_student b where a.userName = b.userName and status = '已通过申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				User user = new User();
				student.setStudentId(rs.getLong("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setSex(rs.getString("sex"));
				student.setStudentMajor(rs.getString("studentMajor"));
				student.setStudentClass(rs.getString("studentClass"));
				student.setBirthday(rs.getDate("birthday"));
				student.setMobile(rs.getLong("mobile"));
				student.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				studentMap.put(student, user);
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
		return studentMap;
	}
	
	/**
	 * 显示通过申请的管理员
	 * @return
	 */
	public Map<Manager,User> showAllManagerDao() {
		Map<Manager,User> managerMap = new LinkedHashMap<Manager,User>();
		String sql = "select * from t_user a,t_manager b where a.userName = b.userName and status = '已通过申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				User user = new User();
				manager.setManagerId(rs.getInt("managerId"));
				manager.setManagerName(rs.getString("managerName"));
				manager.setSex(rs.getString("sex"));
				manager.setAge(rs.getInt("age"));
				manager.setBirthday(rs.getDate("birthday"));
				manager.setMobile(rs.getLong("mobile"));
				manager.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				managerMap.put(manager, user);
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
		return managerMap;
	}
	
	/**
	 * 学生用户个人信息
	 * @param userName
	 * @return
	 */
	public Map<Student,User> myStudentDao(String userName) {
		Map<Student,User> studentMap = new LinkedHashMap<Student,User>();
		String sql = "select * from t_user a,t_student b where a.userName = ? and a.userName = b.userName "
				+ "and status = '已通过申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				User user = new User();
				student.setStudentId(rs.getLong("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setSex(rs.getString("sex"));
				student.setStudentMajor(rs.getString("studentMajor"));
				student.setStudentClass(rs.getString("studentClass"));
				student.setBirthday(rs.getDate("birthday"));
				student.setMobile(rs.getLong("mobile"));
				student.setEmail(rs.getString("email"));
				student.setPhoto(rs.getString("photo"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				studentMap.put(student, user);
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
		return studentMap;
	}
	
	/**
	 * 管理员用户个人信息
	 * @param userName
	 * @return
	 */
	public Map<Manager,User> myManagerDao(String userName) {
		Map<Manager,User> managerMap = new LinkedHashMap<Manager,User>();
		String sql = "select * from t_user a,t_manager b where a.userName = ? and a.userName = b.userName and status = '已通过申请'";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				User user = new User();
				manager.setManagerId(rs.getInt("managerId"));
				manager.setManagerName(rs.getString("managerName"));
				manager.setSex(rs.getString("sex"));
				manager.setAge(rs.getInt("age"));
				manager.setBirthday(rs.getDate("birthday"));
				manager.setMobile(rs.getLong("mobile"));
				manager.setEmail(rs.getString("email"));
				manager.setPhoto(rs.getString("photo"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				managerMap.put(manager, user);
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
		return managerMap;
	}
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public boolean updateStudentDao(Student student) {
		String sql = "update t_student set sex=?, studentMajor=?, studentClass=?, birthday=?, mobile=? where userName = ? ";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, student.getSex());
			fos.setString(2, student.getStudentMajor());
			fos.setString(3, student.getStudentClass());
			fos.setDate(4, new java.sql.Date (student.getBirthday().getTime()));
			fos.setLong(5, student.getMobile());
			fos.setString(6, student.getUserName());
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
	 * 更新管理员信息
	 * @param student
	 * @return
	 */
	public boolean updateManagerDao(Manager manager) {
		String sql = "update t_manager set sex=?, age=?, birthday=?, mobile=? where userName = ? ";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, manager.getSex());
			fos.setInt(2, manager.getAge());
			fos.setDate(3, new java.sql.Date (manager.getBirthday().getTime()));
			fos.setLong(4, manager.getMobile());
			fos.setString(5, manager.getUserName());
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
	 *修改用户密码
	 * @param user
	 * @return
	 */
	public boolean updatePasswordDao(User user) {
		String sql = "update t_user set password = ? where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, user.getPassword());
			fos.setString(2, user.getUserName());
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
	 * 查询用户身份
	 * @param userName
	 * @return
	 */
	public String findIdentiyDao(String userName) {
		String identity = "";
		String sql = "select identity from t_user where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			rs.next();
			identity = rs.getString("identity");
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
		return identity;
	}
	
	
	/**
	 * 模糊查询管理员
	 * @param keyword
	 * @return
	 */
	public Map<Manager,User> searchManagerDao(String keyword) {
		Map<Manager,User> managerMap = new LinkedHashMap<Manager,User>();
		String sql = "select * from t_user a,t_manager b where a.userName = b.userName and status = '已通过申请'"
				+ "and (managerName LIKE '%' ? '%' OR age LIKE '%' ? '%' OR managerId = ? "
				+ "OR sex LIKE '%' ? '%' OR a.userName LIKE '%' ? '%')";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, keyword);
			fos.setString(2, keyword);
			fos.setString(3, keyword);
			fos.setString(4, keyword);
			fos.setString(5, keyword);
			rs = fos.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				User user = new User();
				manager.setManagerId(rs.getInt("managerId"));
				manager.setManagerName(rs.getString("managerName"));
				manager.setSex(rs.getString("sex"));
				manager.setAge(rs.getInt("age"));
				manager.setBirthday(rs.getDate("birthday"));
				manager.setMobile(rs.getLong("mobile"));
				manager.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				managerMap.put(manager, user);
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
		return managerMap;
	}
	
	/**
	 * 模糊查询学生
	 * @param userName
	 * @return
	 */
	public Map<Student,User> searchStudentDao(String keyword) {
		Map<Student,User> studentMap = new LinkedHashMap<Student,User>();
		String sql = "select * from t_user a,t_student b where a.userName = b.userName and status = '已通过申请'"
				+ "and (studentName LIKE '%' ? '%' OR studentMajor LIKE '%' ? '%' OR studentId = ? "
				+ "OR sex LIKE '%' ? '%' OR studentClass LIKE '%' ? '%' OR a.userName LIKE '%' ? '%')";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, keyword);
			fos.setString(2, keyword);
			fos.setString(3, keyword);
			fos.setString(4, keyword);
			fos.setString(5, keyword);
			fos.setString(6, keyword);
			rs = fos.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				User user = new User();
				student.setStudentId(rs.getLong("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setSex(rs.getString("sex"));
				student.setStudentMajor(rs.getString("studentMajor"));
				student.setStudentClass(rs.getString("studentClass"));
				student.setBirthday(rs.getDate("birthday"));
				student.setMobile(rs.getLong("mobile"));
				student.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				studentMap.put(student, user);
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
		return studentMap;
	}
	
	/**
	 * 更改学生用户头像
	 * @param student
	 * @return
	 */
	public boolean updateStudentPhotoDao(Student student) {
		String sql = "update t_student set photo = ? where studentId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, student.getPhoto());
			fos.setLong(2, student.getStudentId());
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
	 * 更改管理员用户头像
	 * @param student
	 * @return
	 */
	public boolean updateManagerPhotoDao(Manager manager) {
		String sql = "update t_manager set photo = ? where managerId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, manager.getPhoto());
			fos.setLong(2, manager.getManagerId());
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
	 * 查询学生头像路径
	 * @param userName
	 * @return
	 */
	public String findStudentPhotoDao(String userName) {
		String photo = "";
		String sql = "select photo from t_student where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			rs.next();
			photo = rs.getString("photo");
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
		return photo;
	}
	
	/**
	 * 查询管理员头像路径
	 * @param userName
	 * @return
	 */
	public String findManagerPhotoDao(String userName) {
		String photo = "";
		String sql = "select photo from t_manager where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			rs.next();
			photo = rs.getString("photo");
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
		return photo;
	}
	
	/**
	 * 多条件查询学生
	 * @param userName
	 * @return
	 */
	public Map<Student,User> conditionStudentDao(String sql) {
		Map<Student,User> studentMap = new LinkedHashMap<Student,User>();
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Student student = new Student();
				User user = new User();
				student.setStudentId(rs.getLong("studentId"));
				student.setStudentName(rs.getString("studentName"));
				student.setSex(rs.getString("sex"));
				student.setStudentMajor(rs.getString("studentMajor"));
				student.setStudentClass(rs.getString("studentClass"));
				student.setBirthday(rs.getDate("birthday"));
				student.setMobile(rs.getLong("mobile"));
				student.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				studentMap.put(student, user);
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
		return studentMap;
	}
	
	/**
	 * 多条件查询管理员
	 * @param keyword
	 * @return
	 */
	public Map<Manager,User> conditionManagerDao(String sql) {
		Map<Manager,User> managerMap = new LinkedHashMap<Manager,User>();
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				User user = new User();
				manager.setManagerId(rs.getInt("managerId"));
				manager.setManagerName(rs.getString("managerName"));
				manager.setSex(rs.getString("sex"));
				manager.setAge(rs.getInt("age"));
				manager.setBirthday(rs.getDate("birthday"));
				manager.setMobile(rs.getLong("mobile"));
				manager.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setIdentity(rs.getString("identity"));
				managerMap.put(manager, user);
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
		return managerMap;
	}
}
