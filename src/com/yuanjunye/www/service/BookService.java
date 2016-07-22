package com.yuanjunye.www.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yuanjunye.www.dao.BookDao;
import com.yuanjunye.www.po.Books;

public class BookService {
	
	private BookDao bookDao = new BookDao(); 

	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public boolean addBook(Books book) {
		return bookDao.addBookDao(book);
	}
	
	/**
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public boolean judegBookId(String bookId) {
		return bookDao.judgeBookIdDao(bookId);
	}
	
	/**
	 * 显示所有图书信息
	 * @return
	 */
	public Map<Books, String> showAllBooks() {
		return bookDao.showAllBooksDao();
	}
	
	/**
	 * 显示所有图书信息（无顺序）
	 * @return
	 */
	public Map<Books, String> showAll1Books() {
		return bookDao.showAllBooks1Dao();
	}
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(String bookId) {
		return bookDao.deleteBookDao(bookId);
	}
	
	/**
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public boolean inBook(String bookId) {
		return bookDao.inBookDao(bookId);
	}
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public boolean outBook(String bookId) {
		return bookDao.outBookDao(bookId);
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Map<Books, String> showBook(String bookId) {
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
		return bookDao.updateBookDao(book);
	}
	
	/**
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public boolean updatePhoto(Books book) {
		return bookDao.updatePhotoDao(book);
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
	public boolean updateAmount(String bookId, int amount, String status) {
		return bookDao.updateAmountDao(bookId, amount, status);
	}
	
	/**
	 * 通过借阅申请，借阅次数加一
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateNumberDao(String bookId, int number) {
		return bookDao.updateNumberDao(bookId, number);
	}
	
	/**
	 * 模糊查询图书信息
	 * @return
	 */
	public Map<Books, String> searchBooks(String keyword) {
		return bookDao.searchBooksDao(keyword);
	}

	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public Map<Books, String> searchBooks1(String sql) {
		return bookDao.searchBooks1Dao(sql);
	}

	/**
	 * 根据图书类型模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public Map<Books, String> searchBooks2(String typeName) {
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
