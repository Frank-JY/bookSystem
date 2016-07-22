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
 * 修改密码
 * @author hasee
 *
 */
@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService = new UserService();
	
    public PasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("userName")) {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			String oldPassword = user.getPassword();
			if(request.getParameter("oldPassword").equals(oldPassword)) {
				String newPassword = request.getParameter("newPassword");
				user.setPassword(newPassword);
				session.setAttribute("user", user);
				boolean bool = userService.updatePassword(user);
				if(bool) {
					response.sendRedirect("updateSuccess.jsp");
				}
			}else {
				request.setAttribute("error", "密码错误,请重新输入");
				request.getRequestDispatcher("password.jsp").forward(request, response);
			}
		}else {
			response.sendRedirect("ban.jsp");
		}
		
	}

}
