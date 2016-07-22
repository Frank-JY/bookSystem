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
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.service.BorrowService;
import com.yuanjunye.www.service.UserService;

/**
 * 用户注册申请操作
 * @author hasee
 *
 */
@WebServlet("/PassUserActionServlet")
public class PassUserActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserService userService = new UserService();
	BorrowService borrowService  = new BorrowService(); 
    
    public PassUserActionServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(("pass".equals(request.getParameter("action")))) {
			userService.passUser(request.getParameter("userName"));
			response.sendRedirect("PassUserServlet");
			return;
		}
		if(("reject").equals(request.getParameter("action"))) {
			userService.rejectUser(request.getParameter("userName"));
			response.sendRedirect("PassUserServlet");
			return;
		}
		if(("delete").equals(request.getParameter("action"))) {
			userService.rejectUser(request.getParameter("userName"));
			response.sendRedirect("ShowAllUserServlet?action=1");
			return;
		}
		if(("borrow").equals(request.getParameter("action"))) {
			HttpSession session = request.getSession();
			String userName = request.getParameter("userName");
			String identity = userService.findIdentiy(userName);
			if(identity.equals("学生")) { 
				Map<StudentBorrow,String> borrowMap = borrowService.myStudentBorrow(userName);
				Student student = borrowService.findStudent(userName);
				String name = student.getStudentName();
				session.setAttribute("borrowName", name);
				session.setAttribute("borrowMap", borrowMap);
				response.sendRedirect("findStudentBorrow.jsp");
				return;
			}else {
				Map<ManagerBorrow,String> borrowMap = borrowService.myManagerBorrow(userName);
				Manager manager = borrowService.findManager(userName);
				String name = manager.getManagerName();
				session.setAttribute("borrowName", name);
				session.setAttribute("borrowMap", borrowMap);
				response.sendRedirect("findManagerBorrow.jsp");
				return;
			}
		}
		response.sendRedirect("ban.jsp");
	}

}
