package com.yuanjunye.www.po;

/**
 * 好友模型
 * @author hasee
 *
 */
public class Friend {

	private int id;
	private String myUserName;
	private String friendUserName;
	private String identity;
	private String status;
	private String photo;
	
	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Friend() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMyUserName() {
		return myUserName;
	}


	public void setMyUserName(String myUserName) {
		this.myUserName = myUserName;
	}


	public String getFriendUserName() {
		return friendUserName;
	}


	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}


	public String getIdentity() {
		return identity;
	}


	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
