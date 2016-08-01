package com.yuanjunye.www.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuanjunye.www.dao.IFriendDao;
import com.yuanjunye.www.dao.IUserDao;
import com.yuanjunye.www.po.Friend;

@Service
public class FriendService implements IFriendService{

	@Resource
	private IFriendDao friendDao;
	@Resource
	private IUserDao userDao;
	
	/**
	 * 好友申请
	 * @param friend
	 * @return
	 */
	public boolean addFriendDao(Friend friend) {
		boolean bool = true;
		friend.setStatus("等待申请");
		int t = friendDao.addFriendDao(friend);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询状态
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public String findStatus(Friend friend){
		return friendDao.findStatusDao(friend);
	}
	
	/**
	 * 查询个人好友
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showFriend(String myUserName) {
		List<Friend> friendList = friendDao.showFriendDao(myUserName);
		for(Friend friend : friendList) {
			String friendUserName = friend.getFriendUserName();
			String identity = friend.getIdentity();
			String headphoto = "";
				if(identity.equals("学生")) {
					headphoto = userDao.findStudentPhotoDao(friendUserName);
				}else {
					headphoto = userDao.findManagerPhotoDao(friendUserName);
				}	
			friend.setPhoto(headphoto);
		}
		return friendList;
	}
	
	/**
	 * 查看好友申请
	 * @param myUserName
	 * @return
	 */
	public List<Friend> showApplyFriend(String myUserName) {
		List<Friend> applyFriendList = friendDao.showApplyFriendDao(myUserName);
		for(Friend friend : applyFriendList) {
			String identity = friend.getIdentity();
			String myUserUserName = friend.getMyUserName();
			String headphoto = "";
				if(identity.equals("学生")) {
					headphoto = userDao.findStudentPhotoDao(myUserUserName);
				}else {
					headphoto = userDao.findManagerPhotoDao(myUserUserName);
				}	
			friend.setPhoto(headphoto);
		}
		return applyFriendList;
	}
	
	/**
	 * 不同意好友申请/删除
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean rejectFriend(Friend friend) {
		boolean bool = true;
		int t = friendDao.rejectFriendDao(friend);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 通过申请
	 * @param myUserName
	 * @param friendUserName
	 * @return
	 */
	public boolean updateStatus(Friend friend) {
		boolean bool = true;
		int t = friendDao.updateStatusDao(friend);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
}
