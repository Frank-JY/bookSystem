package com.yuanjunye.www.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Param2;

public interface IBookDao {

	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public int addBookDao(Books book);
	
	/**
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public int judgeBookIdDao(String bookId);
	
	/**
	 * 显示所有图书信息（有顺序）
	 * @return
	 */
	public List<Books> showAllBooksDao();
	
	/**
	 * 显示所有图书信息(无顺序)
	 * @return
	 */
	public Set<Books> showAllBooks1Dao();
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public int deleteBookDao(String bookId);
	
	/**
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public int inBookDao(Books book);
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public int outBookDao(String bookId);
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books showBookDao(String bookId);
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books findBookDao(String bookId);
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public int updateBookDao(Books book);
	
	/**
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public int updatePhotoDao(Books book);
	
	/**
	 * 查询图书状态
	 * @param bookId
	 * @return
	 */
	public String findStatusDao(String bookId);
	
	/**
	 * 查询图书库存
	 * @param bookId
	 * @return
	 */
	public int findAmountDao(String bookId);
	
	/**
	 * 查询图书借阅次数
	 * @param bookId
	 * @return
	 */
	public int findNumberDao(String bookId);
	
	/**
	 * 图书操作，改变库存
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public int updateAmountDao(Books book);
	
	/**
	 * 通过借阅申请，借阅次数加一
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public int updateNumberDao(String bookId);
	
	/**
	 * 模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooksDao(String keyword);
	
	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public List<Books> searchBooks1Dao(Param2 param);
	
	/**
	 * 根据图书类型查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooks2Dao(String typeName);
	
	/**
	 * 每日新书
	 * @param time
	 * @return
	 */
	public List<Books> newBookDao(Date time);
	
	/**
	 * 热门图书推介
	 * @return
	 */
	public List<Books> hotBookDao();
	
	/**
	 * 查询图书总数和库存
	 * @param bookId
	 * @return
	 */
	public Books checkAmountTotalDao(String bookId);
	
}
