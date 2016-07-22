package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Friend;
import com.yuanjunye.www.service.FriendService;

/**
 * 显示个人好友
 * @author hasee
 *
 */
@WebServlet("/Friend1Servlet")
public class Friend1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FriendService friendService = new FriendService();

    public Friend1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		List<Friend> applyFriendList = friendService.showApplyFriend(userName);
		List<Friend> friendList = friendService.showFriend(userName);
		session.setAttribute("applyFriendList", applyFriendList);
		session.setAttribute("friendList", friendList);
		response.sendRedirect("friend.jsp");
	}

}
