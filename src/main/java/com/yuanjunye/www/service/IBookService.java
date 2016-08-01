package com.yuanjunye.www.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Param2;

public interface IBookService {

	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public boolean addBook(Books book);
	
	/**
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public boolean judegBookId(String bookId);
	
	/**
	 * 显示所有图书信息
	 * @return
	 */
	public List<Books> showAllBooks();
	
	/**
	 * 显示所有图书信息（无顺序）
	 * @return
	 */
	public Set<Books> showAllBooks1();
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(String bookId);
	
	/**
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public boolean inBook(String bookId);
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public boolean outBook(String bookId);
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books showBook(String bookId);
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books findBook(String bookId);
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public boolean updateBook(Books book);
	
	/**
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public boolean updatePhoto(Books book);
	
	/**
	 * 查询图书状态
	 * @param bookId
	 * @return
	 */
	public String findStatus(String bookId);
	
	/**
	 * 查询图书库存
	 * @param bookId
	 * @return
	 */
	public int findAmount(String bookId);
	
	
	
	/**
	 * 申请借阅图书，改变库存
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateAmount(Books book);
	
	
	/**
	 * 模糊查询图书信息
	 * @return
	 */
	public List<Books> searchBooks(String keyword);
	
	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public List<Books> searchBooks1(Param2 param);
	
	/**
	 * 根据图书类型模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooks2(String typeName);
	
	/**
	 * 每日新书
	 * @param time
	 * @return
	 */
	public List<Books> newBook();
	
	/**
	 * 热门图书推介
	 * @return
	 */
	public List<Books> hotBook();
	
	/**
	 * 修改图书逻辑
	 */
	public Books judge(Books book, int oldTotal);
}
