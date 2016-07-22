package com.yuanjunye.www.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yuanjunye.www.dao.BookDao;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Param2;

public class BookService {
	
	private BookDao bookDao = new BookDao(); 

	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public boolean addBook(Books book) {
		boolean bool = true;
		if(book.getTotal() > 0) {
			book.setStatus("可借阅");
		}else {
			book.setStatus("无库存");
		}
		int t = bookDao.addBookDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public boolean judegBookId(String bookId) {
		boolean bool = true;
		int t = bookDao.judgeBookIdDao(bookId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 显示所有图书信息
	 * @return
	 */
	public List<Books> showAllBooks() {
		return bookDao.showAllBooksDao();
	}
	
	/**
	 * 显示所有图书信息（无顺序）
	 * @return
	 */
	public Set<Books> showAllBooks1() {
		return bookDao.showAllBooks1Dao();
	}
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(String bookId) {
		boolean bool = true;
		Books book = bookDao.checkAmountTotalDao(bookId);
		int amount = book.getAmount();
		int total = book.getTotal();
		if(total != amount) {
			bool = false;
		}else {
			int t = bookDao.deleteBookDao(bookId);
			if(t == 0) {
				bool = false;
			}
		}
		return bool;
	}
	
	/**
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public boolean inBook(String bookId) {
		boolean bool = true;
		String status = "";
		int amount = bookDao.findAmountDao(bookId);
		if(amount == 0) {
			status = "无库存";
		}else {
			status = "可借阅";
		}
		Books book = new Books();
		book.setBookId(bookId);
		book.setStatus(status);
		int t = bookDao.inBookDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public boolean outBook(String bookId) {
		boolean bool = true;
		int t = bookDao.outBookDao(bookId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books showBook(String bookId) {
		return bookDao.showBookDao(bookId);
	}
	
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books findBook(String bookId) {
		return bookDao.findBookDao(bookId);
	}
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public boolean updateBook(Books book) {
		boolean bool = true;
		int t = bookDao.updateBookDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public boolean updatePhoto(Books book) {
		boolean bool = true;
		int t = bookDao.updatePhotoDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询图书状态
	 * @param bookId
	 * @return
	 */
	public String findStatus(String bookId) {
		return bookDao.findStatusDao(bookId);
	}
	
	/**
	 * 查询图书库存
	 * @param bookId
	 * @return
	 */
	public int findAmount(String bookId) {
		return bookDao.findAmountDao(bookId);
	}
	
	/**
	 * 查询图书借阅次数
	 * @param bookId
	 * @return
	 */
	public int findNumber(String bookId) {
		return bookDao.findNumberDao(bookId);
	}
	
	/**
	 * 申请借阅图书，改变库存
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateAmount(Books book) {
		boolean bool = true;
		int t = bookDao.updateAmountDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 通过借阅申请，借阅次数加一
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateNumberDao(Books book) {
		boolean bool = true;
		int t = bookDao.updateNumberDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 模糊查询图书信息
	 * @return
	 */
	public List<Books> searchBooks(String keyword) {
		return bookDao.searchBooksDao(keyword);
	}

	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public List<Books> searchBooks1(Param2 param) {
		return bookDao.searchBooks1Dao(param);
	}

	/**
	 * 根据图书类型模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooks2(String typeName) {
		return bookDao.searchBooks2Dao(typeName);
	}
	
	/**
	 * 每日新书
	 * @param time
	 * @return
	 */
	public List<Books> newBook(Date time) {
		return bookDao.newBookDao(time);
	}
	
	/**
	 * 热门图书推介
	 * @return
	 */
	public List<Books> hotBook() {
		return bookDao.hotBookDao();
	}
}
