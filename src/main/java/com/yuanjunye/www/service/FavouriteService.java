package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.dao.FavouriteDao;
import com.yuanjunye.www.po.Favourite;

public class FavouriteService {

	private FavouriteDao favouriteDao = new FavouriteDao();
	
	
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
	public List<String> findCollectBookId(String userName) {
		return favouriteDao.findCollectBookIdDao(userName);
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
