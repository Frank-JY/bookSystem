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
 * 用户注册申请
 * @author hasee
 *
 */
@WebServlet("/PassUserServlet")
public class PassUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	UserService userService = new UserService();
    
    public PassUserServlet() {
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
		Map<Student,User> studentMap = userService.showApplyStudent();
		Map<Manager,User> managerMap = userService.showApplyManager();
		request.setAttribute("studentMap", studentMap);
		request.setAttribute("managerMap", managerMap);
		request.getRequestDispatcher("passUser.jsp").forward(request, response);
	}

}
