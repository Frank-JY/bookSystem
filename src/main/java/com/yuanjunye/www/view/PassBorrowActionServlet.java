package com.yuanjunye.www.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.BorrowService;

/**
 * 借阅操作
 * @author hasee
 *
 */
@WebServlet("/PassBorrowActionServlet")
public class PassBorrowActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BorrowService borrowService  = new BorrowService(); 
	BookService bookService = new BookService();
	
    public PassBorrowActionServlet() {
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
		
		if(request.getParameter("action").equals("pass")) {
			boolean bool = false;
			int id = Integer.valueOf(request.getParameter("id"));
			int day = 0;
			if(request.getParameter("identity").equals("student")) {
				day = borrowService.findDay1(id);
			}else if(request.getParameter("identity").equals("manager")){
				day = borrowService.findDay2(id);
			}else {
				response.sendRedirect("ban.jsp");
				return;
			}
			//当前日期加上借阅天数
			Calendar c=Calendar.getInstance();   
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			Date endTime = new Date();
			Date outTime = new Date();
			try {
			c.setTime(now);  
			c.add(Calendar.DATE,day);   
			Date end=c.getTime();   
			String ends = df.format(end); 
			String outs = df.format(now);
			endTime = df.parse(ends);
			outTime = df.parse(outs);
			} catch (ParseException e) {
				e.printStackTrace();
			}
					
			if(request.getParameter("identity").equals("student")) {	
				StudentBorrow borrow = new StudentBorrow();
				borrow.setId(id);
				borrow.setEndTime(endTime);
				borrow.setOutTime(outTime);
				String bookId = borrowService.findBookId1(id);
				int number = bookService.findNumber(bookId);
				number++;
				Books book = new Books();
				book.setBookId(bookId);
				book.setNumber(number);
				bookService.updateNumberDao(book);
				bool = borrowService.passStudentBorrow(borrow);
				if(bool) {
					response.sendRedirect("PassBorrowServlet?action=1");
					return;
				}
			}else if(request.getParameter("identity").equals("manager")){
				ManagerBorrow borrow = new ManagerBorrow();
				borrow.setId(id);
				borrow.setEndTime(endTime);
				borrow.setOutTime(outTime);
				String bookId = borrowService.findBookId2(id);
				int number = bookService.findNumber(bookId);
				number++;
				Books book = new Books();
				book.setBookId(bookId);
				book.setNumber(number);
				bookService.updateNumberDao(book);
				bool = borrowService.passManagerBorrow(borrow);
				if(bool) {
					response.sendRedirect("PassBorrowServlet?action=2");
					return;
				}
			}
		}else if(request.getParameter("action").equals("reject")) {
			int id = Integer.valueOf(request.getParameter("id"));
			if(request.getParameter("identity").equals("student")) {
				boolean bool1 = borrowService.rejectStudentBorrow(id);
				String bookId = borrowService.findBookId1(id);
				int amount = bookService.findAmount(bookId);
				amount++;                                                //库存加1
				String status = bookService.findStatus(bookId);
				if(status.equals("已下架")) {
					status = "已下架";
				}else if(status.equals("无库存")){
					status = "可借阅";
				}else if(status.equals("可借阅")) {
					status = "可借阅";
				}
				Books book = new Books();
				book.setBookId(bookId);
				book.setAmount(amount);
				book.setStatus(status);
				boolean bool2 = bookService.updateAmount(book);
				if(bool1 && bool2) {
					response.sendRedirect("PassBorrowServlet?action=1");
					return;
				}
			}else if(request.getParameter("identity").equals("manager")){
				boolean bool1 = borrowService.rejectManagerBorrow(id);
				String bookId = borrowService.findBookId2(id);
				int amount = bookService.findAmount(bookId);
				amount++;                                                //库存加1
				String status = bookService.findStatus(bookId);
				if(status.equals("已下架")) {
					status = "已下架";
				}else if(status.equals("无库存")){
					status = "可借阅";
				}else if(status.equals("可借阅")) {
					status = "可借阅";
				}
				Books book = new Books();
				book.setBookId(bookId);
				book.setAmount(amount);
				book.setStatus(status);
				boolean bool2 = bookService.updateAmount(book);
				if(bool1 && bool2) {
					response.sendRedirect("PassBorrowServlet?action=1");
					return;
				}
			}else {
				response.sendRedirect("ban.jsp");
				return;
			}
		}
		
	}

}
