package com.yuanjunye.www.service;

import java.util.List;
import java.util.Set;

import com.yuanjunye.www.dao.BookTypeDao;
import com.yuanjunye.www.po.BookType;

public class BookTypeService {

	private BookTypeDao bookTypeDao = new BookTypeDao();
	
	/**
	 * 显示所有图书类型
	 * @return
	 */
	public List<BookType> showBookType() {
		return bookTypeDao.showBookTypeDao();
	}
	
	/**
	 * 添加图书类型
	 * @param bookType
	 * @return
	 */
	public boolean addBookType(String typeName) {
		boolean bool = true;
		int t = bookTypeDao.addBookTypeDao(typeName);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 修改图书类型
	 * @param typeName
	 * @return
	 */
	public boolean updateBookType(BookType bookType) {
		boolean bool = true;
		int t = bookTypeDao.updateBookTypeDao(bookType);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 删除图书类型
	 * @param typeName
	 * @return
	 */
	public boolean deleteBookType(int typeId) {
		boolean bool = true;
		int t = bookTypeDao.deleteBookTypeDao(typeId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 判断图书类型
	 * @param typeName
	 * @return
	 */
	public boolean judgeTypeName(String typeName) {
		boolean bool = true;
		int t = bookTypeDao.judgeTypeNameDao(typeName);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询图书类型名称
	 * @param typeName
	 * @return
	 */
	public String findTypeName(int typeId) {
		return bookTypeDao.findTypeNameDao(typeId);
	}
	
	/**
	 * 查询所有图书类型名称
	 * @return
	 */
	public Set<String> allTypeName() {
		return bookTypeDao.allTypeNameDao();
	}
}
