package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Favourite;

public interface IFavouriteService {

	/**
	 * 检查用户是否可以收藏(数量少于等于10本)
	 * @param userName
	 * @return
	 */
	public boolean isCollectDao(String userName);
	
	/**
	 * 检查用户是否重复收藏图书
	 * @param userName
	 * @param bookId
	 * @return
	 */
	public boolean isRepeatDao(Favourite favourite);
	
	/**
	 * 插入用户收藏图书信息
	 * @param favourite
	 * @return
	 */
	public boolean collectDao(Favourite favourite);
	
	/**
	 * 获取用户收藏夹图书信息
	 * @param userName
	 * @return
	 */
	public List<Books> findCollectBookId(String userName);
	
	/**
	 * 删除收藏夹图书信息
	 * @param favourite
	 * @return
	 */
	public boolean deleteFavouriteDao(Favourite favourite);
}
