package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.SomeName;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.util.DbUtil;

public class BorrowDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 查询学生用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Student findStudentDao(String userName) {
		String studentName = "";
		long studentId = 0;
		Student student = new Student();
		String sql = "select studentName,studentId from t_student where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			if (rs.next()) {
				studentName = rs.getString("studentName");
				studentId = rs.getLong("studentId");
				student.setStudentName(studentName);
				student.setStudentId(studentId);
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
		return student;
	}
	
	/**
	 * 查询管理员用户的姓名学号
	 * @param userName
	 * @return
	 */
	public Manager findManagerDao(String userName) {
		String managerName = "";
		int managerId = 0;
		Manager manager = new Manager();
		String sql = "select managerName,managerId from t_manager where userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			if (rs.next()) {
				managerName = rs.getString("managerName");
				managerId = rs.getInt("managerId");
				manager.setManagerName(managerName);
				manager.setManagerId(managerId);
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
		return manager;
	}
	
	/**
	 * 查询图书名
	 * @param bookId
	 * @return
	 */
	public String findBookNameDao(String bookId) {
		String bookName = "";
		String sql = "select bookName from t_books where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			if (rs.next()) {
				bookName = rs.getString("bookName");
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
		return bookName;
	}
	
	/**
	 * 学生提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addStudentBorrowDao(StudentBorrow borrow) {
		String sql = "insert into t_stborrow values(null,?,?,?,?,?,null,null,null,null,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setLong(1, borrow.getReaderId());
			fos.setString(2, borrow.getUserName());
			fos.setString(3, borrow.getBookId());
			fos.setInt(4, borrow.getDay());
			fos.setDate(5, new java.sql.Date(borrow.getApplyTime().getTime()));
			fos.setString(6, "等待申请");
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
	 * 管理员提交借阅申请
	 * @param borrow
	 * @return
	 */
	public boolean addManagerBorrowDao(ManagerBorrow borrow) {
		String sql = "insert into t_maborrow values(null,?,?,?,?,?,null,null,null,null,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, borrow.getReaderId());
			fos.setString(2, borrow.getUserName());
			fos.setString(3, borrow.getBookId());
			fos.setInt(4, borrow.getDay());
			fos.setDate(5, new java.sql.Date(borrow.getApplyTime().getTime()));
			fos.setString(6, "等待申请");
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
	 * 查询学生指定图书状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus1Dao(String bookId,String userName) {
		String status = "";
		String sql = "select status from t_stborrow where bookId = ? and userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			fos.setString(2, userName);
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
	 * 查询管理员指定图书借阅状态
	 * @param bookId
	 * @param userName
	 * @return
	 */
	public String findStatus2Dao(String bookId,String userName) {
		String status = "";
		String sql = "select status from t_maborrow where bookId = ? and userName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			fos.setString(2, userName);
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
	 * 查询学生个人借阅信息
	 * @param userName
	 * @return
	 */
	public Map<StudentBorrow,String> myStudentBorrowDao(String userName) {
		Map<StudentBorrow,String> myBorrowMap = new LinkedHashMap<StudentBorrow,String>();
		String sql = "select * from t_stborrow a, t_books b where a.bookId=b.bookId and userName = ? order by id";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			while(rs.next()) {
				StudentBorrow borrow = new StudentBorrow();
				borrow.setId(rs.getInt("id"));
				borrow.setReaderId(rs.getLong("readerId"));
				borrow.setUserName(userName);
				borrow.setBookId(rs.getString("bookId"));
				borrow.setDay(rs.getInt("day"));
				borrow.setApplyTime(rs.getDate("applyTime"));
				borrow.setOutTime(rs.getDate("outTime"));
				borrow.setEndTime(rs.getDate("endTime"));
				borrow.setInTime(rs.getDate("inTime"));
				borrow.setDue(rs.getBoolean("isdue"));
				borrow.setStatus(rs.getString("status"));
				String bookName = rs.getString("bookName");
				myBorrowMap.put(borrow, bookName);
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
		return myBorrowMap;
	}
	
	
	/**
	 * 查询管理员个人借阅信息
	 * @param userName
	 * @return
	 */
	public Map<ManagerBorrow,String> myManagerBorrowDao(String userName) {
		Map<ManagerBorrow,String> myBorrowMap = new LinkedHashMap<ManagerBorrow,String>();
		String sql = "select * from t_maborrow a, t_books b where a.bookId=b.bookId and userName = ? order by id";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, userName);
			rs = fos.executeQuery();
			while(rs.next()) {
				ManagerBorrow borrow = new ManagerBorrow();
				borrow.setId(rs.getInt("id"));
				borrow.setReaderId(rs.getInt("readerId"));
				borrow.setUserName(userName);
				borrow.setBookId(rs.getString("bookId"));
				borrow.setDay(rs.getInt("day"));
				borrow.setApplyTime(rs.getDate("applyTime"));
				borrow.setOutTime(rs.getDate("outTime"));
				borrow.setEndTime(rs.getDate("endTime"));
				borrow.setInTime(rs.getDate("inTime"));
				borrow.setDue(rs.getBoolean("isdue"));
				borrow.setStatus(rs.getString("status"));
				String bookName = rs.getString("bookName");
				myBorrowMap.put(borrow, bookName);
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
		return myBorrowMap;
	}
	
	/**
	 * 查询学生借阅申请
	 * @param userName
	 * @return
	 */
	public Map<StudentBorrow,SomeName> applyStudentBorrowDao() {
		Map<StudentBorrow,SomeName> stBorrowMap = new LinkedHashMap<StudentBorrow,SomeName>();
		String sql = "select * from t_stborrow a, t_books b,t_student c"
				+ " where a.bookId=b.bookId and a.readerId = c.studentId and a.status = '等待申请' order by applyTime";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				StudentBorrow borrow = new StudentBorrow();
				SomeName someName = new SomeName();
				borrow.setId(rs.getInt("id"));
				borrow.setReaderId(rs.getLong("readerId"));
				borrow.setUserName(rs.getString("userName"));
				borrow.setBookId(rs.getString("bookId"));
				borrow.setDay(rs.getInt("day"));
				borrow.setApplyTime(rs.getDate("applyTime"));
				borrow.setOutTime(rs.getDate("outTime"));
				borrow.setEndTime(rs.getDate("endTime"));
				borrow.setInTime(rs.getDate("inTime"));
				borrow.setDue(rs.getBoolean("isdue"));
				borrow.setStatus(rs.getString("status"));
				someName.setBookName(rs.getString("bookName"));
				someName.setReaderName(rs.getString("studentName"));
				stBorrowMap.put(borrow, someName);
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
		return stBorrowMap;
	}
	
	/**
	 * 查询管理员借阅申请
	 * @param userName
	 * @return
	 */
	public Map<ManagerBorrow,SomeName> applyManagerBorrowDao() {
		Map<ManagerBorrow,SomeName> maBorrowMap = new LinkedHashMap<ManagerBorrow,SomeName>();
		String sql = "select * from t_maborrow a, t_books b,t_Manager c"
				+ " where a.bookId=b.bookId and a.readerId = c.managerId and a.status = '等待申请' order by applyTime";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				ManagerBorrow borrow = new ManagerBorrow();
				SomeName someName = new SomeName();
				borrow.setId(rs.getInt("id"));
				borrow.setReaderId(rs.getInt("readerId"));
				borrow.setUserName(rs.getString("userName"));
				borrow.setBookId(rs.getString("bookId"));
				borrow.setDay(rs.getInt("day"));
				borrow.setApplyTime(rs.getDate("applyTime"));
				borrow.setOutTime(rs.getDate("outTime"));
				borrow.setEndTime(rs.getDate("endTime"));
				borrow.setInTime(rs.getDate("inTime"));
				borrow.setDue(rs.getBoolean("isdue"));
				borrow.setStatus(rs.getString("status"));
				someName.setBookName(rs.getString("bookName"));
				someName.setReaderName(rs.getString("managerName"));
				maBorrowMap.put(borrow, someName);
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
		return maBorrowMap;
	}
	
	/**
	 * 查询学生借阅天数
	 * @param id
	 * @return
	 */
	public int findDay1Dao(int id) {
		int day = 0;
		String sql = "select day from t_stborrow where  id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			rs = fos.executeQuery();
			rs.next();
			day = rs.getInt("day");
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
		return day;
	}
	
	/**
	 * 查询管理员借阅天数
	 * @param id
	 * @return
	 */
	public int findDay2Dao(int id) {
		int day = 0;
		String sql = "select day from t_maborrow where  id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			rs = fos.executeQuery();
			rs.next();
			day = rs.getInt("day");
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
		return day;
	}
	
	/**
	 * 学生借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passStudentBorrowDao(StudentBorrow borrow) {
		String sql = "update t_stborrow set endTime=?, outTime=?, isdue=?, status=? where id=?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setDate(1, new java.sql.Date(borrow.getEndTime().getTime()));
			fos.setDate(2, new java.sql.Date(borrow.getOutTime().getTime()));
			fos.setBoolean(3, false);
			fos.setString(4, "借阅中");
			fos.setInt(5, borrow.getId());
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
	 * 管理员借阅申请成功
	 * @param borrow
	 * @return
	 */
	public boolean passManagerBorrowDao(ManagerBorrow borrow) {
		String sql = "update t_maborrow set endTime=?, outTime=?, isdue=?, status=? where id=?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setDate(1, new java.sql.Date(borrow.getEndTime().getTime()));
			fos.setDate(2, new java.sql.Date(borrow.getOutTime().getTime()));
			fos.setBoolean(3, false);
			fos.setString(4, "借阅中");
			fos.setInt(5, borrow.getId());
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
	 * 学生借阅申请失败
	 * @param borrow
	 * @return
	 */
	public boolean rejectStudentBorrowDao(int id) {
		String sql = "update t_stborrow set isdue=?, status=? where id=?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setBoolean(1, false);
			fos.setString(2, "申请未通过");
			fos.setInt(3, id);
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
	 * 管理员借阅申请失败
	 * @param borrow
	 * @return
	 */
	public boolean rejectManagerBorrowDao(int id) {
		String sql = "update t_maborrow set isdue=?, status=? where id=?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setBoolean(1, false);
			fos.setString(2, "申请未通过");
			fos.setInt(3, id);
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
	 * 查询学生借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId1Dao(int id) {
		String bookId = "";
		String sql = "select bookId from t_stborrow where id = ?";
		try {
			conn =DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			rs = fos.executeQuery();
			rs.next();
			bookId = rs.getString("bookId");
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
		return bookId;
	}
	
	/**
	 * 查询管理员借阅信息的图书编号
	 * @param id
	 * @return
	 */
	public String findBookId2Dao(int id) {
		String bookId = "";
		String sql = "select bookId from t_maborrow where id = ?";
		try {
			conn =DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			rs = fos.executeQuery();
			rs.next();
			bookId = rs.getString("bookId");
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
		return bookId;
	}
	
	/**
	 * 查询学生借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime1Dao(int id) {
		Date endTime = new Date();
		String sql = "select endTime from t_stborrow where id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			rs = fos.executeQuery();
			rs.next();
			endTime = (Date)rs.getDate("endTime");
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
		return endTime;
	}
	
	/**
	 * 查询管理员借阅信息的归还时间
	 * @param id
	 * @return
	 */
	public Date findEndTime2Dao(int id) {
		Date endTime = new Date();
		String sql = "select endTime from t_maborrow where id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, id);
			rs = fos.executeQuery();
			rs.next();
			endTime = (Date)rs.getDate("endTime");
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
		return endTime;
	}
	
	/**
	 * 学生归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back1Dao(int id, boolean due, Date inTime) {
		String sql = "update t_stborrow set inTime = ?, isdue = ?, status = ? where id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setDate(1, new java.sql.Date(inTime.getTime()));
			fos.setBoolean(2, due);
			fos.setString(3, "已归还");
			fos.setInt(4, id);
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
	 * 管理员归还图书
	 * @param id
	 * @param due
	 * @param inTime
	 * @return
	 */
	public boolean back2Dao(int id, boolean due, Date inTime) {
		String sql = "update t_maborrow set inTime = ?, isdue = ?, status = ? where id = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setDate(1, new java.sql.Date(inTime.getTime()));
			fos.setBoolean(2, due);
			fos.setString(3, "已归还");
			fos.setInt(4, id);
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
