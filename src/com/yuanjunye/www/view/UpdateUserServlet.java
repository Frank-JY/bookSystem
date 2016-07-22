package com.yuanjunye.www.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.service.UserService;

/**
 * 修改个人信息
 * @author hasee
 *
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private UserService userService = new UserService();
	
    public UpdateUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("student")){
			String userName = request.getParameter("userName");
			String sex = request.getParameter("sex");
			String studentMajor = request.getParameter("studentMajor");
			String studentClass = request.getParameter("studentClass");
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = new Date();
			try {
				String bir = "1970-01-01";
				birthday = df.parse(bir);
				if(null != request.getParameter("birthday") && !request.getParameter("birthday").equals("")) {
					birthday = df.parse(request.getParameter("birthday"));
				}
			}catch (ParseException e) {
				e.printStackTrace();
			}
			long mobile = 0;
			if(null != request.getParameter("mobile") && !request.getParameter("mobile").equals("")) {
				mobile = Long.valueOf(request.getParameter("mobile"));
			}
			Student student = new Student();
			student.setUserName(userName);
			student.setSex(sex);
			student.setStudentMajor(studentMajor);
			student.setStudentClass(studentClass);
			student.setBirthday(birthday);
			student.setMobile(mobile);
			boolean bool = userService.updateStudent(student);
			if(bool) {
				response.sendRedirect("updateSuccess.jsp");
				return;
			}else {
				response.sendRedirect("updatefailure.jsp");
				return;
			}
		}
		else if(request.getParameter("action").equals("manager")) {
			String userName = request.getParameter("userName");
			String sex = request.getParameter("sex");
			int age = 0;
			long mobile = 0;
			if(null != request.getParameter("age") && !request.getParameter("age").equals("")) {
				age = Integer.valueOf(request.getParameter("age"));
			}
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = new Date();
			try {
				String bir = "1970-01-01";
				birthday = df.parse(bir);
				if(null != request.getParameter("birthday") && !request.getParameter("birthday").equals("")) {
					birthday = df.parse(request.getParameter("birthday"));
				}
			}catch (ParseException e) {
				e.printStackTrace();
			}
			if(null != request.getParameter("mobile") && !request.getParameter("mobile").equals("")) {
				mobile = Long.valueOf(request.getParameter("mobile"));
			}
			Manager manager = new Manager();
			manager.setUserName(userName);
			manager.setSex(sex);
			manager.setAge(age);
			manager.setBirthday(birthday);
			manager.setMobile(mobile);;
			boolean bool = userService.updateManager(manager);
			if(bool) {
				response.sendRedirect("updateSuccess.jsp");
				return;
			}else {
				response.sendRedirect("updatefailure.jsp");
				return;
			}
		}else {
			response.sendRedirect("updatefailure.jsp");
			return;
		}
		
		
		
		
	}

}
