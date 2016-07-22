package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.yuanjunye.www.po.BookType;
import com.yuanjunye.www.util.DbUtil;

public class BookTypeDao {
	
	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 显示所有的图书类型
	 * @return
	 */
	public List<BookType> showBookTypeDao() {
		List<BookType> bookTypeList = new LinkedList<BookType>();
		String sql = "select * from t_booktype";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				BookType bookType = new BookType();
				bookType.setTypeName(rs.getString("typeName"));
				bookType.setTypeId(rs.getInt("typeId"));
				bookTypeList.add(bookType);
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
		return bookTypeList;
	}
	
	/**
	 * 添加图书类型
	 * @param bookType
	 * @return
	 */
	public boolean addBookTypeDao(String typeName) {
		String sql = "insert into t_booktype values(null,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, typeName);
			int t = fos.executeUpdate();
			if(t == 0 ) {
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
	 * 删除图书类型
	 * @param bookType
	 * @return
	 */
	public boolean deleteBookTypeDao(int typeId) {
		String sql = "delete from t_booktype where typeId = ? ";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, typeId);
			int t = fos.executeUpdate();
			if(t == 0 ) {
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
	 *修改图书类型 
	 * @param bookType
	 * @return
	 */
	public boolean updateBookTypeDao(BookType bookType) {
		String sql = "update t_booktype set typeName = ? where typeId = ? ";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookType.getTypeName());
			fos.setInt(2, bookType.getTypeId());
			int t = fos.executeUpdate();
			if(t == 0 ) {
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
	 * 判断类型名称是否存在
	 * @param typeName
	 * @return
	 */
	public boolean judgeTypeNameDao(String typeName) {
		String sql = "select * from t_booktype where typeName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, typeName);
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
	 * 查询图书类型名称
	 * @param typeName
	 * @return
	 */
	public String findTypeNameDao(int typeId) {
		String typeName = "";
		String sql = "select * from t_booktype where typeId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, typeId);
			rs = fos.executeQuery();
			if(rs.next()) {
				typeName = rs.getString("typeName");
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
		return typeName;
	}
	
	/**
	 * 查询所有图书类型名称
	 * @return
	 */
	public Set<String> allTypeNameDao() {
		Set<String> typeNameSet = new HashSet<String>();
		String sql = "select typeName from t_booktype ";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				String typeName = rs.getString("typeName");
				typeNameSet.add(typeName);
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
		return typeNameSet;
	}
	
	
	
}
