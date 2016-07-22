package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.RegisterService;

/**
 * 注册
 * @author hasee
 *
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RegisterService register;  //成员变量
    
    public RegisterServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null == request.getParameter("userName") || "".equals(request.getParameter("userName"))) {
			response.sendRedirect("login.jsp");
			return;
		}
		boolean bool1 = false;
		boolean bool2 = false;
		boolean bool3 = false;
		register = new RegisterService();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String identity = request.getParameter("identity");
		request.setAttribute("userName", userName);
		request.setAttribute("identity", identity);
		request.setAttribute("name", name);
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setIdentity(identity);
		user.setStatus("等待申请");
		request.setAttribute("id", id);
		request.setAttribute("email", email);
		boolean bb = register.checkUserName(userName);
		if(bb) {	
			request.setAttribute("error1", "用户名已存在");
		}
		if(identity.equals("管理员")) {
			Manager manager = new Manager();
			manager.setManagerId(Integer.valueOf(id));
			manager.setManagerName(name);
			manager.setUserName(userName);
			manager.setEmail(email);
			manager.setPhoto("dog.jpg");
			boolean bo1 = register.checkManagerId(Integer.valueOf(id));
			boolean bo2 = register.checkEmail(email);
			if(bo1) {
				request.setAttribute("error2", "职工号已存在");
			}
			if(bo2) {		
				request.setAttribute("error3", "邮箱已存在");
			}
			if(!bo1 && !bo2 && !bb){
				bool1 = register.addUser(user);
				bool2 = register.addManager(manager);
			}
		}else {
			Student student = new Student();
			student.setStudentId(Long.valueOf(id));
			student.setStudentName(name);
			student.setUserName(userName);
			student.setEmail(email);
			student.setPhoto("dog.jpg");
			boolean bo1 = register.checkStudentId(Long.valueOf(id));
			boolean bo2 = register.checkEmail(email);
			if(bo1) {
				request.setAttribute("error2", "学号已存在");
			}
			if(bo2){
				request.setAttribute("error3", "邮箱已存在");
			}
			if(!bo1 && !bo2 && !bb){
				bool1 = register.addUser(user);
				bool3 = register.addStudent(student);
			}
		}
		if(bool1 && (bool2 || bool3)) {
			request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}

}
