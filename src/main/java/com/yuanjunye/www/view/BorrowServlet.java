package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.BorrowService;

/**
 * 借阅预处理
 * @author hasee
 *
 */
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService();
	private BorrowService borrowService = new BorrowService(); 
	
    public BorrowServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("bookId")) {
			String bookId = request.getParameter("bookId");
			String status = bookService.findStatus(bookId);
			if(status.equals("可借阅")) {
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");
				String identity = user.getIdentity();
				String userName = user.getUserName();
				String bookName = borrowService.findBookName(bookId);
				if(identity.equals("学生")) {
					Student student = borrowService.findStudent(userName);
					String studentName = student.getStudentName();
					long studentId = student.getStudentId();
					request.setAttribute("name", studentName);
					request.setAttribute("readerId", studentId);
				}else {
					Manager manager = borrowService.findManager(userName);
					String managerName = manager.getManagerName();
					int managerId = manager.getManagerId();
					request.setAttribute("name", managerName);
					request.setAttribute("readerId", managerId);
				}
				request.setAttribute("identity", identity);
				request.setAttribute("bookId", bookId);
				request.setAttribute("userName", userName);
				request.setAttribute("bookName", bookName);
				request.getRequestDispatcher("borrow.jsp").forward(request, response);
				return;
			}else if(status.equals("无库存")) {
				request.setAttribute("error", "这本图已经被借光了，先收藏起来吧");
				request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
				return;
			}else if(status.equals("已下架")) {
				request.setAttribute("error", "这本图已经下架，先收藏起来吧");
				request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
				return;
			}else {
				response.sendRedirect("main.jsp");  
				return;
			}
		}else {  
			response.sendRedirect("ban.jsp");
			return;
		}
	}
      
}
