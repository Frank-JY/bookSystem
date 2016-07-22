package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Friend;
import com.yuanjunye.www.service.FriendService;

/**
 * 好友的操作
 * @author hasee
 *
 */
@WebServlet("/FriendActionServlet")
public class FriendActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FriendService friendService = new FriendService();

    public FriendActionServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("friendUserName")) {
			HttpSession session = request.getSession();
			String myUserName = (String)session.getAttribute("userName");
			String friendUserName = request.getParameter("friendUserName");
			String action = request.getParameter("action");
			if(action.equals("pass")){
				Friend friend = new Friend();
				String status = "已通过申请";
				friend.setMyUserName(myUserName);
				friend.setFriendUserName(friendUserName);
				friend.setStatus(status);
				boolean bool1 = friendService.addFriendDao(friend);
				boolean bool2 = friendService.updateStatus(friendUserName, myUserName);
				if(bool1 && bool2) {
					response.sendRedirect("Friend1Servlet");
					return;
				}else {
					response.sendRedirect("updatefailure.jsp");
					return;
				}
			}else if(action.equals("reject")){
				boolean bool = friendService.rejectFriend(friendUserName, myUserName);
				if(bool) {
					response.sendRedirect("Friend1Servlet");
					return;
				}else {
					response.sendRedirect("updatefailure.jsp");
					return;
				}
			}else if(action.equals("delete")){
				boolean bool1 = friendService.rejectFriend(friendUserName, myUserName);
				boolean bool2 = friendService.rejectFriend(myUserName, friendUserName);
				if(bool1 && bool2) {
					response.sendRedirect("Friend1Servlet");
					return;
				}else {
					response.sendRedirect("updatefailure.jsp");
					return;
				}
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
