package com.yuanjunye.www.dao;

import java.util.List;
import java.util.Set;

import com.yuanjunye.www.po.BookType;

public interface IBookTypeDao {

	/**
	 * 显示所有的图书类型
	 * @return
	 */
	public List<BookType> showBookTypeDao();
	
	/**
	 * 添加图书类型
	 * @param bookType
	 * @return
	 */
	public int addBookTypeDao(String typeName);
	
	/**
	 * 删除图书类型
	 * @param bookType
	 * @return
	 */
	public int deleteBookTypeDao(int typeId);
	
	/**
	 *修改图书类型 
	 * @param bookType
	 * @return
	 */
	public int updateBookTypeDao(BookType bookType);
	
	/**
	 * 判断类型名称是否存在
	 * @param typeName
	 * @return
	 */
	public int judgeTypeNameDao(String typeName);
	
	/**
	 * 查询图书类型名称
	 * @param typeName
	 * @return
	 */
	public String findTypeNameDao(int typeId);
	
	/**
	 * 查询所有图书类型名称
	 * @return
	 */
	public Set<String> allTypeNameDao();
}
