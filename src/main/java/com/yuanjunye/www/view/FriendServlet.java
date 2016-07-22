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
import com.yuanjunye.www.service.UserService;

/**
 * 好友申请预处理
 * @author hasee
 *
 */
@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FriendService friendService = new FriendService();
	private UserService userService = new UserService();

    public FriendServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("userName")) {
			HttpSession session = request.getSession();
			Friend friend = new Friend();
			String friendUserName = request.getParameter("userName");
			String myUserName = (String)session.getAttribute("userName");
			String status = "等待申请";
			friend.setMyUserName(myUserName);
			friend.setFriendUserName(friendUserName);
			friend.setStatus(status);
			String identity = userService.findIdentiy(friendUserName);
			String status0 = friendService.findStatus(friend);
			if(("等待申请").equals(status0)) {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "申请已发送！");
					request.getRequestDispatcher("lookStudent.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("tips", "申请已发送！");
					request.getRequestDispatcher("lookManager.jsp").forward(request, response);
					return;
				}
			}else if(("已通过申请").equals(status0)) {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "你已添加此好友!");
					request.getRequestDispatcher("lookStudent.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("tips", "你已添加此好友!");
					request.getRequestDispatcher("lookManager.jsp").forward(request, response);
					return;
				}
			}			
			boolean bool = friendService.addFriendDao(friend);
			if(bool) {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "申请已发送");
					request.getRequestDispatcher("lookStudent.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("tips", "申请已发送！");
					request.getRequestDispatcher("lookManager.jsp").forward(request, response);
					return;
				}
			}else {
				if(identity.equals("学生")) {
					request.setAttribute("tips", "添加好友失败!");
					request.getRequestDispatcher("lookStudent.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("tips", "添加好友失败!");
					request.getRequestDispatcher("lookManager.jsp").forward(request, response);
					return;
				}
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
