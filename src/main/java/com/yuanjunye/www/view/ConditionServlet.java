package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Param1;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.UserService;

/**
 * 多条件查询用户
 * @author hasee
 *
 */
@WebServlet("/ConditionServlet")
public class ConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService = new UserService();

    public ConditionServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			response.sendRedirect("ban.jsp");
			return;
		}
		if(null != request.getParameter("value")) {
			String value = request.getParameter("value");
			if(value.equals("1")) {
				String userName = request.getParameter("userName");
				String studentId = request.getParameter("studentId");
				String studentName = request.getParameter("studentName");
				String sex = request.getParameter("sex");
				String studentMajor= request.getParameter("studentMajor");
				String studentClass= request.getParameter("studentClass");
				Param1 param = new Param1();
				param.setUserName(userName);
				param.setStudentId(studentId);
				param.setStudentName(studentName);
				param.setSex(sex);
				param.setStudentMajor(studentMajor);
				param.setStudentClass(studentClass);
				List<User> studentList = userService.conditionStudent(param);
				request.setAttribute("studentList", studentList);
				request.getRequestDispatcher("allStudent.jsp").forward(request, response);
			}else if(value.equals("2")) {
				String userName = request.getParameter("userName");
				String managerId = request.getParameter("managerId");
				String managerName = request.getParameter("managerName");
				String sex = request.getParameter("sex");
				String age= request.getParameter("age");
				Param1 param = new Param1();
				param.setUserName(userName);
				param.setManagerId(managerId);
				param.setManagerName(managerName);
				param.setSex(sex);
				param.setAge(age);
				List<User> managerList = userService.conditionManager(param);
				request.setAttribute("managerList", managerList);
				request.getRequestDispatcher("allManager.jsp").forward(request, response);
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
