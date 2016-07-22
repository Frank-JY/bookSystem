package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.SomeName;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.service.BorrowService;

/**
 * 借阅预处理
 * @author hasee
 *
 */
@WebServlet("/PassBorrowServlet")
public class PassBorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private BorrowService borrowService = new BorrowService();
	
    public PassBorrowServlet() {
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
		Map<StudentBorrow,SomeName> stBorrowMap = borrowService.applyStudentBorrowDao();
		Map<ManagerBorrow,SomeName> maBorrowMap = borrowService.applyManagerBorrowDao();
		request.setAttribute("stBorrowMap", stBorrowMap);
		request.setAttribute("maBorrowMap", maBorrowMap);
		if(request.getParameter("action").equals("1")) {
			request.getRequestDispatcher("passStudentBorrow.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("passManagerBorrow.jsp").forward(request, response);
		}
	}

}
