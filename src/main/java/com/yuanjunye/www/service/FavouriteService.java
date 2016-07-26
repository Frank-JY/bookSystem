package com.yuanjunye.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuanjunye.www.dao.IBookDao;
import com.yuanjunye.www.dao.IFavouriteDao;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Favourite;

@Service
public class FavouriteService implements IFavouriteService{

	@Resource
	private IFavouriteDao favouriteDao;
	@Resource
	private IBookDao bookDao;
	
	
	/**
	 * 检查用户是否可以收藏(数量少于等于10本)
	 * @param userName
	 * @return
	 */
	public boolean isCollectDao(String userName) {
		boolean bool = true;
		int t = favouriteDao.isCollectDao(userName);
		if(t >= 10) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 检查用户是否重复收藏图书
	 * @param userName
	 * @param bookId
	 * @return
	 */
	public boolean isRepeatDao(Favourite favourite) {
		boolean bool = true;
		int t = favouriteDao.isRepeatDao(favourite);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 插入用户收藏图书信息
	 * @param favourite
	 * @return
	 */
	public boolean collectDao(Favourite favourite) {
		boolean bool = true;
		int t = favouriteDao.collectDao(favourite);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 获取用户收藏夹图书信息
	 * @param userName
	 * @return
	 */
	public List<Books> findCollectBookId(String userName) {
		List<Books> booksList = new ArrayList<Books>();
		List<String> bookIdList = favouriteDao.findCollectBookIdDao(userName);
		for(String bookId : bookIdList) {
			Books book = bookDao.findBookDao(bookId);
			booksList.add(book);
		}
		return booksList;
	}
	
	/**
	 * 删除收藏夹图书信息
	 * @param favourite
	 * @return
	 */
	public boolean deleteFavouriteDao(Favourite favourite) {
		boolean bool = true;
		int t = favouriteDao.deleteFavouriteDao(favourite);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
}
