package com.yuanjunye.www.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuanjunye.www.po.Friend;
import com.yuanjunye.www.service.IFriendService;
import com.yuanjunye.www.service.IUserService;

@Controller
@RequestMapping("/friend")
public class FriendController {

	@Resource
	private IFriendService friendService ;
	@Resource
	private IUserService userService;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/friend1")
	public ModelAndView friend1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		List<Friend> applyFriendList = friendService.showApplyFriend(userName);
		List<Friend> friendList = friendService.showFriend(userName);
		session.setAttribute("applyFriendList", applyFriendList);
		session.setAttribute("friendList", friendList);
		mav.setViewName("friend/friend");
		return mav;
	}
	
	@RequestMapping(value="/friend")
	public ModelAndView friend(HttpServletRequest request, String userName) {
		ModelAndView mav = new ModelAndView();
		if(null != userName) {
			HttpSession session = request.getSession();
			Friend friend = new Friend();
			String friendUserName = userName;
			String myUserName = (String)session.getAttribute("userName");
			friend.setMyUserName(myUserName);
			friend.setFriendUserName(friendUserName);
			String identity = userService.findIdentity(friendUserName);
			String status0 = friendService.findStatus(friend);
			if(("等待申请").equals(status0)) {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "申请已发送！");
					mav.setViewName("user/lookStudent");
					return mav;
				}else {
					request.setAttribute("tips", "申请已发送！");
					mav.setViewName("user/lookManager");
					return mav;
				}
			}else if(("已通过申请").equals(status0)) {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "你已添加此好友!");
					mav.setViewName("user/lookStudent");
					return mav;
				}else {
					request.setAttribute("tips", "你已添加此好友!");
					mav.setViewName("user/lookManager");
					return mav;
				}
			}			
			boolean bool = friendService.addFriendDao(friend);
			if(bool) {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "申请已发送");
					mav.setViewName("user/lookStudent");
					return mav;
				}else {
					request.setAttribute("tips", "申请已发送！");
					mav.setViewName("user/lookManager");
					return mav;
				}
			}else {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "添加好友失败!");
					mav.setViewName("user/lookStudent");
					return mav;
				}else {
					request.setAttribute("tips", "添加好友失败!");
					mav.setViewName("user/lookManager");
					return mav;
				}
			}
		}else {
			mav.setViewName("ban");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/friendAction")
	public ModelAndView friendAction(HttpServletRequest request, String action, String friendUserName) {
		ModelAndView mav = new ModelAndView();
		if(null != friendUserName) {
			HttpSession session = request.getSession();
			String myUserName = (String)session.getAttribute("userName");
			if(action.equals("pass")){
				Friend friend = new Friend();
				String status = "已通过申请";
				friend.setMyUserName(myUserName);
				friend.setFriendUserName(friendUserName);
				friend.setStatus(status);
				boolean bool1 = friendService.addFriendDao(friend);
				boolean bool2 = friendService.updateStatus(friend);
				if(bool1 && bool2) {
					mav.setViewName("redirect:/friend/friend1.do");
				}else {
					mav.setViewName("user/updatefailure");
				}
			}else if(action.equals("reject")){
				Friend friend1 = new Friend();
				friend1.setMyUserName(friendUserName);
				friend1.setFriendUserName(myUserName);
				boolean bool = friendService.rejectFriend(friend1);
				if(bool) {
					mav.setViewName("redirect:/friend/friend1.do");
				}else {
					mav.setViewName("user/updatefailure");
				}
			}else if(action.equals("delete")){
				Friend friend1 = new Friend();
				friend1.setMyUserName(friendUserName);
				friend1.setFriendUserName(myUserName);
				boolean bool1 = friendService.rejectFriend(friend1);
				Friend friend2 = new Friend();
				friend2.setMyUserName(myUserName);
				friend2.setFriendUserName(friendUserName);
				boolean bool2 = friendService.rejectFriend(friend2);
				if(bool1 && bool2) {
					mav.setViewName("redirect:/friend/friend1.do");
				}else {
					mav.setViewName("user/updatefailure");
				}
			}
		}else {
			mav.setViewName("ban");
		}	
		return mav;
	}
}
