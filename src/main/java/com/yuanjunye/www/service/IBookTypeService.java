package com.yuanjunye.www.service;

import java.util.List;
import java.util.Set;

import com.yuanjunye.www.po.BookType;

public interface IBookTypeService {

	
	/**
	 * 显示所有图书类型
	 * @return
	 */
	public List<BookType> showBookType();
	
	/**
	 * 添加图书类型
	 * @param bookType
	 * @return
	 */
	public boolean addBookType(String typeName);
	
	/**
	 * 修改图书类型
	 * @param typeName
	 * @return
	 */
	public boolean updateBookType(BookType bookType);
	
	/**
	 * 删除图书类型
	 * @param typeName
	 * @return
	 */
	public boolean deleteBookType(int typeId);
	
	/**
	 * 判断图书类型
	 * @param typeName
	 * @return
	 */
	public boolean judgeTypeName(String typeName);
	
	/**
	 * 查询图书类型名称
	 * @param typeName
	 * @return
	 */
	public String findTypeName(int typeId);
	
	/**
	 * 查询所有图书类型名称
	 * @return
	 */
	public Set<String> allTypeName();
}
