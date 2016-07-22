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
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.BorrowService;

/**
 * 查看个人借阅记录
 * @author hasee
 *
 */
@WebServlet("/MyBorrowServlet")
public class MyBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BorrowService borrowService = new BorrowService();
       
    public MyBorrowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String identity = user.getIdentity();
		String userName = user.getUserName();
		if(identity.equals("学生")) {
			Map<StudentBorrow,String> myBorrowMap = borrowService.myStudentBorrow(userName);
			Student student = borrowService.findStudent(userName);
			String name = student.getStudentName();
			session.setAttribute("myborrowName", name);
			session.setAttribute("myBorrowMap", myBorrowMap);
			response.sendRedirect("myStudentBorrow.jsp");
			return;
		}else {
			Map<ManagerBorrow,String> myBorrowMap = borrowService.myManagerBorrow(userName);
			Manager manager = borrowService.findManager(userName);
			String name = manager.getManagerName();
			session.setAttribute("myborrowName", name);
			session.setAttribute("myBorrowMap", myBorrowMap);
			response.sendRedirect("myManagerBorrow.jsp");
			return;
		}
	}

}
