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

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.BorrowService;

/**
 * 申请借阅
 * @author hasee
 *
 */
@WebServlet("/Borrow1Servlet")
public class Borrow1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BorrowService borrowService = new BorrowService(); 
	private BookService bookService = new BookService();
	
    public Borrow1Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("bookId")) {
			boolean bool1 = false;
			boolean bool2 = false;
			String bookId = request.getParameter("bookId");
			String identity = request.getParameter("identity");
			String userName = request.getParameter("userName");
			int day = Integer.valueOf(request.getParameter("day"));
			//申请时间
			Date applyTime = new Date();
			try {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				Date currentTime  = new Date();
				String s = df.format(currentTime);
				applyTime = df.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(identity.equals("学生")) {
				long studentId = Long.valueOf(request.getParameter("readerId"));
				StudentBorrow borrow = new StudentBorrow();
				borrow.setBookId(bookId);
				borrow.setUserName(userName);
				borrow.setReaderId(studentId);
				borrow.setDay(day);
				borrow.setApplyTime(applyTime);
				String status = borrowService.findStatus1(borrow);
				if(status.equals("等待申请")){
					request.setAttribute("error", "申请已经发送,请等待管理员确认");
					request.getRequestDispatcher("BorrowServlet?bookId="+bookId).forward(request, response);
					return;
				}else if(status.equals("借阅中")) {
					request.setAttribute("error", "你已经成功借阅此图书，请勿重复借阅");
					request.getRequestDispatcher("BorrowServlet?bookId="+bookId).forward(request, response);
					return;
				}else {
					bool1 = borrowService.addStudentBorrow(borrow);
				}	
			}else {
				int managerId = Integer.valueOf(request.getParameter("readerId"));
				ManagerBorrow borrow = new ManagerBorrow();
				borrow.setBookId(bookId);
				borrow.setUserName(userName);
				borrow.setReaderId(managerId);
				borrow.setDay(day);
				borrow.setApplyTime(applyTime);
				String status = borrowService.findStatus2(borrow);
				if(status.equals("等待申请")){
					request.setAttribute("error", "申请已经发送,请等待管理员确认");
					request.getRequestDispatcher("BorrowServlet?bookId="+bookId).forward(request, response);
					return;
				}else if(status.equals("借阅中")) {
					request.setAttribute("error", "你已经成功借阅此图书，请勿重复借阅");
					request.getRequestDispatcher("BorrowServlet?bookId="+bookId).forward(request, response);
					return;
				}else {
					bool1 = borrowService.addManagerBorrow(borrow);	
				}
			}
			if(bool1) {
				int amount = bookService.findAmount(bookId);
				amount--;
				String newStatus = "";
				if(amount == 0) {
					newStatus = "无库存";
				}else {
					newStatus = "可借阅";
				}
				Books book = new Books();
				book.setBookId(bookId);
				book.setAmount(amount);
				book.setStatus(newStatus);
				bool2 = bookService.updateAmount(book);
				if(bool2) {
					response.sendRedirect("updateSuccess.jsp");
					return;
				}
			}else {
				response.sendRedirect("updateFailure.jsp");
				return;
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
