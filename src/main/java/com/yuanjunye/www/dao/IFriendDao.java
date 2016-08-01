package com.yuanjunye.www.dao;

import java.util.List;

import com.yuanjunye.www.po.Friend;

public interface IFriendDao {

	/**
	 * 好友申请
	 * @param friend
	 * @return
	 */
	public int addFriendDao(Friend friend);
	
	/**
	 * 查询状态
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public String findStatusDao(Friend friend);
	
	/**
	 * 查询个人好友
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showFriendDao(String myUserName);
	
	/**
	 * 查看好友申请
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showApplyFriendDao(String myUserName);
	
	/**
	 * 不同意好友申请/删除
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public int rejectFriendDao(Friend friend);
	
	/**
	 * 通过申请
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public int updateStatusDao(Friend friend);
}
