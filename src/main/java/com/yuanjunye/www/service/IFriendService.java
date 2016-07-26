package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.po.Friend;

public interface IFriendService {

	/**
	 * 好友申请
	 * @param friend
	 * @return
	 */
	public boolean addFriendDao(Friend friend);
	
	/**
	 * 查询状态
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public String findStatus(Friend friend);
	
	/**
	 * 查询个人好友
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showFriend(String myUserName);
	
	/**
	 * 查看好友申请
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showApplyFriend(String myUserName);
	
	/**
	 * 不同意好友申请/删除
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean rejectFriend(Friend friend);
	
	/**
	 * 通过申请
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean updateStatus(Friend friend);
}
