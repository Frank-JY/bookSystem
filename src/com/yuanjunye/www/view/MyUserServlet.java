package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.UserService;

/**
 * 查看/修改个人信息
 * @author hasee
 *
 */
@WebServlet("/MyUserServlet")
public class MyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserService userService = new UserService();
	
    public MyUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		String identity = user.getIdentity();
		if(identity.equals("学生")) {
			Map<Student,User> studentMap = userService.myStudent(userName);
			request.setAttribute("studentMap", studentMap);
			request.getRequestDispatcher("myStudent.jsp").forward(request, response);	
			return;
		}else {
			Map<Manager,User> managerMap = userService.myManager(userName);
			request.setAttribute("managerMap", managerMap);
			request.getRequestDispatcher("myManager.jsp").forward(request, response);
			return;
		}
	}

}
