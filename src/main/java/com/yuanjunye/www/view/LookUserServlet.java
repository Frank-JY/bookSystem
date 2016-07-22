package com.yuanjunye.www.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.UserService;

/**
 * 查看用户资料
 * @author hasee
 *
 */
@WebServlet("/LookUserServlet")
public class LookUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService = new UserService();
	
    public LookUserServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("userName")) {
			HttpSession session = request.getSession();
			String messageTag = request.getParameter("messageTag");
			session.setAttribute("messageTag", messageTag);
			String userName = request.getParameter("userName");
			String identity = userService.findIdentiy(userName);
			if(identity.equals("学生")){
				User user = userService.myStudent(userName);
				session.setAttribute("lookUser", user);
				response.sendRedirect("lookStudent.jsp");	
				return;
			}else {
				User user = userService.myManager(userName);
				session.setAttribute("lookUser", user);
				response.sendRedirect("lookManager.jsp");
				return;
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
