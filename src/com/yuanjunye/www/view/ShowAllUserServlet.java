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
 * 显示所有用户
 * @author hasee
 *
 */
@WebServlet("/ShowAllUserServlet")
public class ShowAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserService userService = new UserService();
	
    public ShowAllUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			response.sendRedirect("power.jsp");
			return;
		}
		Map<Student,User> studentMap = userService.showAllStudent();
		Map<Manager,User> managerMap = userService.showAllManager();
		request.setAttribute("studentMap", studentMap);
		request.setAttribute("managerMap", managerMap);
		if(request.getParameter("action").equals("1")) {
			request.getRequestDispatcher("allStudent.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("allManager.jsp").forward(request, response);
		}
	}

}
