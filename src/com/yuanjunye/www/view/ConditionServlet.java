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
				String sql = "select * from t_user a,t_student b where a.userName = b.userName and status = '已通过申请' ";
				if(!"".equals(userName)) {
					sql += "and a.userName = '" + userName + "'";
				}
				if(!"".equals(studentId)) {
					sql += "and studentId = '" + studentId + "'";
				}
				if(!"".equals(studentName)) {
					sql += "and studentName = '" + studentName + "'";
				}
				if(!"".equals(sex)) {
					sql += "and sex = '" + sex + "'";
				}
				if(!"".equals(studentMajor)) {
					sql += "and studentMajor = '" + studentMajor + "'";
				}
				if(!"".equals(studentClass)) {
					sql += "and studentClass = '" + studentClass + "'";
				}
				Map<Student,User> studentMap = userService.conditionStudent(sql);
				request.setAttribute("studentMap", studentMap);
				request.getRequestDispatcher("allStudent.jsp").forward(request, response);
			}else if(value.equals("2")) {
				String userName = request.getParameter("userName");
				String managerId = request.getParameter("managerId");
				String managerName = request.getParameter("managerName");
				String sex = request.getParameter("sex");
				String age= request.getParameter("age");
				String sql = "select * from t_user a,t_manager b where a.userName = b.userName and status = '已通过申请' ";
				if(!"".equals(userName)) {
					sql += "and a.userName = '" + userName + "'";
				}
				if(!"".equals(managerId)) {
					sql += "and managerId = '" + managerId + "'";
				}
				if(!"".equals(managerName)) {
					sql += "and managerName = '" + managerName + "'";
				}
				if(!"".equals(sex)) {
					sql += "and sex = '" + sex + "'";
				}
				if(!"".equals(age)) {
					sql += "and age = '" + age + "'";
				}
				Map<Manager,User> managerMap = userService.conditionManager(sql);
				request.setAttribute("managerMap", managerMap);
				request.getRequestDispatcher("allManager.jsp").forward(request, response);
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
