package com.yuanjunye.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.util.DbUtil;

public class BookDao {

	private Connection conn;
	private PreparedStatement fos;
	private ResultSet rs; 
	
	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public boolean addBookDao(Books book) {
		String sql = "insert into t_books values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, book.getBookId());
			fos.setString(2, book.getBookName());
			fos.setString(3, book.getAuthor());
			fos.setString(4, book.getPublishers());
			fos.setDate(5, new java.sql.Date (book.getTime().getTime()));
			fos.setInt(6, book.getTypeId());
			fos.setInt(7, book.getTotal());
			fos.setInt(8, book.getTotal());
			fos.setInt(9, 0);
			fos.setString(10, book.getRemarks());
			if(book.getTotal() > 0) {
				fos.setString(11, "可借阅");
			}else {
				fos.setString(11, "无库存");
			}
			fos.setString(12, book.getPhoto());
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
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public boolean judgeBookIdDao(String bookId) {
		String sql = "select * from t_books where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
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
	 * 显示所有图书信息（有顺序）
	 * @return
	 */
	public Map<Books, String> showAllBooksDao() {
		Map<Books, String> booksMap = new LinkedHashMap<Books, String>();
		String sql = "select * from t_books a, t_booktype b where a.typeId = b.typeId order by bookId";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				String typeName = rs.getString("typeName");
				booksMap.put(book, typeName);
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
		return booksMap;
	}
	
	/**
	 * 显示所有图书信息(无顺序)
	 * @return
	 */
	public Map<Books, String> showAllBooks1Dao() {
		Map<Books, String> booksMap = new HashMap<Books, String>();
		String sql = "select * from t_books a, t_booktype b where a.typeId = b.typeId";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				String typeName = rs.getString("typeName");
				booksMap.put(book, typeName);
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
		return booksMap;
	}
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public boolean deleteBookDao(String bookId) {
		String sql1 = "select * from t_books where bookId = ? ";
		String sql2 = "delete from t_books where bookId = ? ";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql1);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			rs.next();
			int total = rs.getInt("total");
			int amount = rs.getInt("amount");
			if(total != amount) {
				return false;
			}
			fos = conn.prepareStatement(sql2);
			fos.setString(1, bookId);
			fos.executeUpdate();
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
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public boolean inBookDao(String bookId) {
		String sql1 = "select * from t_books where bookId = ?";
		String sql2 = "update t_books set status = ? where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql1);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			rs.next();
			String status = "";
			int amount = rs.getInt("amount");
			if(amount == 0) {
				status = "无库存";
			}else {
				status = "可借阅";
			}
			fos = conn.prepareStatement(sql2);
			fos.setString(1, status);
			fos.setString(2, bookId);
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
				DbUtil.close(rs, fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public boolean outBookDao(String bookId) {
		String sql2 = "update t_books set status = ? where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql2);
			fos.setString(1, "已下架");
			fos.setString(2, bookId);
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
				DbUtil.close(rs, fos, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Map<Books, String> showBookDao(String bookId) {
		Map<Books, String> bookMap = new LinkedHashMap<Books, String>();
		String sql = "select * from t_books a, t_booktype b where a.typeId = b.typeId and bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			if(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				String typeName = rs.getString("typeName");
				bookMap.put(book, typeName);
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
		return bookMap;
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books findBookDao(String bookId) {
		Books book = new Books();
		String sql = "select * from t_books where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			if(rs.next()) {
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
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
		return book;
	}
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public boolean updateBookDao(Books book) {
		String sql = "update t_books set bookName=?, author=?, publishers=?, typeId=?, amount=?, "
				+ "total=?, remarks=?, status=? where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, book.getBookName());
			fos.setString(2, book.getAuthor());
			fos.setString(3, book.getPublishers());
			fos.setInt(4, book.getTypeId());
			fos.setInt(5, book.getAmount());
			fos.setInt(6, book.getTotal());
			fos.setString(7, book.getRemarks());
			fos.setString(8, book.getStatus());
			fos.setString(9, book.getBookId());
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
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public boolean updatePhotoDao(Books book) {
		String sql = "update t_books set photo = ? where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, book.getPhoto());
			fos.setString(2, book.getBookId());
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
	 * 查询图书状态
	 * @param bookId
	 * @return
	 */
	public String findStatusDao(String bookId) {
		String status = "";
		String sql = "select * from t_books where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			rs.next();
			status = rs.getString("status");
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
		return status;
	}
	
	
	/**
	 * 查询图书库存
	 * @param bookId
	 * @return
	 */
	public int findAmountDao(String bookId) {
		int amount = 0;
		String sql = "select amount from t_books where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			rs.next();
			amount = rs.getInt("amount");
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
		return amount;
	}
	
	/**
	 * 查询图书借阅次数
	 * @param bookId
	 * @return
	 */
	public int findNumberDao(String bookId) {
		int number = 0;
		String sql = "select number from t_books where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, bookId);
			rs = fos.executeQuery();
			rs.next();
			number = rs.getInt("number");
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
		return number;
	}
	
	
	/**
	 * 图书操作，改变库存
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateAmountDao(String bookId, int amount, String status) {
		String sql = "update t_books set amount = ?,status = ? where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, amount);
			fos.setString(2, status);
			fos.setString(3, bookId);
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
	 * 通过借阅申请，借阅次数加一
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateNumberDao(String bookId, int number) {
		String sql = "update t_books set number = ? where bookId = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setInt(1, number);
			fos.setString(2, bookId);
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
	 * 模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public Map<Books, String> searchBooksDao(String keyword) {
		Map<Books, String> searchBooksMap = new LinkedHashMap<Books, String>();
		String sql = "SELECT * FROM t_books a, t_booktype b WHERE a.typeId = b.typeId  AND (bookName LIKE '%' ? '%'"
				+ "or b.typeName like '%' ? '%' or a.author like '%' ? '%' or a.bookId = ?)";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, keyword);
			fos.setString(2, keyword);
			fos.setString(3, keyword);
			fos.setString(4, keyword);
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				String typeName = rs.getString("typeName");
				searchBooksMap.put(book, typeName);
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
		return searchBooksMap;
	}
	
	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public Map<Books, String> searchBooks1Dao(String sql) {
		Map<Books, String> searchBooksMap = new LinkedHashMap<Books, String>();
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				String typeName = rs.getString("typeName");
				searchBooksMap.put(book, typeName);
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
		return searchBooksMap;
	}
	
	/**
	 * 根据图书类型查询图书信息
	 * @param keyword
	 * @return
	 */
	public Map<Books, String> searchBooks2Dao(String typeName) {
		Map<Books, String> searchBooksMap = new LinkedHashMap<Books, String>();
		String sql = "SELECT * FROM t_books a, t_booktype b WHERE a.typeId = b.typeId and typeName = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setString(1, typeName);
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setPublishers(rs.getString("publishers"));
				book.setTime(rs.getDate("time"));
				book.setTypeId(rs.getInt("typeId"));
				book.setTotal(rs.getInt("total"));
				book.setAmount(rs.getInt("amount"));
				book.setNumber(rs.getInt("number"));
				book.setRemarks(rs.getString("remarks"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				String typeName1 = rs.getString("typeName");
				searchBooksMap.put(book, typeName1);
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
		return searchBooksMap;
	}
	
	/**
	 * 每日新书
	 * @param time
	 * @return
	 */
	public List<Books> newBookDao(Date time) {
		List<Books> newBookList = new LinkedList<Books>();
		String sql = "select * from t_books where time = ?";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			fos.setDate(1, new java.sql.Date(time.getTime()));
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				newBookList.add(book);
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
		return newBookList;
	}
	
	/**
	 * 热门图书推介
	 * @return
	 */
	public List<Books> hotBookDao() {
		List<Books> hotBookList = new LinkedList<Books>();
		String sql = "select * from t_books order by number";
		try {
			conn = DbUtil.getConn();
			fos = conn.prepareStatement(sql);
			rs = fos.executeQuery();
			while(rs.next()) {
				Books book = new Books();
				book.setBookId(rs.getString("bookId"));
				book.setBookName(rs.getString("bookName"));
				book.setAuthor(rs.getString("author"));
				book.setStatus(rs.getString("status"));
				book.setPhoto(rs.getString("photo"));
				hotBookList.add(book);
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
		return hotBookList;
	} 
	
	
	
	
}
