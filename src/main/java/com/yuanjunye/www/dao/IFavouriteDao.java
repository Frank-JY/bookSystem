package com.yuanjunye.www.dao;

import java.util.List;

import com.yuanjunye.www.po.Favourite;

public interface IFavouriteDao {

	/**
	 * 检查用户是否可以收藏(数量少于等于10本)
	 * @param userName
	 * @return
	 */
	public int isCollectDao(String userName);
	
	/**
	 * 检查用户是否重复收藏图书
	 * @param userName
	 * @param bookId
	 * @return
	 */
	public int isRepeatDao(Favourite favourite);
	
	/**
	 * 插入用户收藏图书信息
	 * @param favourite
	 * @return
	 */
	public int collectDao(Favourite favourite);
	
	/**
	 * 获取用户收藏夹图书信息
	 * @param userName
	 * @return
	 */
	public List<String> findCollectBookIdDao(String userName);
	
	/**
	 * 删除收藏夹图书信息
	 * @param favourite
	 * @return
	 */
	public int deleteFavouriteDao(Favourite favourite);
}
