package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.dao.FriendDao;
import com.yuanjunye.www.po.Friend;

public class FriendService {

	private FriendDao friendDao = new FriendDao();
	
	/**
	 * 好友申请
	 * @param friend
	 * @return
	 */
	public boolean addFriendDao(Friend friend) {
		return friendDao.addFriendDao(friend);
	}
	
	/**
	 * 查询状态
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public String findStatus(String myUserName,String friendUserName){
		return friendDao.findStatusDao(myUserName, friendUserName);
	}
	
	/**
	 * 查询个人好友
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showFriend(String myUserName) {
		return friendDao.showFriendDao(myUserName);
	}
	
	/**
	 * 查看好友申请
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showApplyFriend(String myUserName) {
		return friendDao.showApplyFriendDao(myUserName);
	}
	
	/**
	 * 不同意好友申请/删除
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean rejectFriend(String myUserName,String friendUserName) {
		return friendDao.rejectFriendDao(myUserName, friendUserName);
	}
	
	/**
	 * 通过申请
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean updateStatus(String myUserName,String friendUserName) {
		return friendDao.updateStatusDao(myUserName, friendUserName);
	}
}
