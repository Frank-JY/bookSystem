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
 * 归还图书
 * @author hasee
 *
 */
@WebServlet("/BackBookServlet")
public class BackBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BorrowService borrowService = new BorrowService(); 
	private BookService bookService = new BookService();
	
    public BackBookServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//归还时间
		Date inTime = new Date();
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime  = new Date();
			String s = df.format(currentTime);
			inTime = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int id = Integer.valueOf(request.getParameter("id"));		
		String identity = request.getParameter("identity");
		if(identity.equals("student")) {
			Date endTime = borrowService.findEndTime1(id);
			boolean due = inTime.after(endTime);
			StudentBorrow borrow = new StudentBorrow();
			borrow.setId(id);
			borrow.setDue(due);
			borrow.setInTime(inTime);
			boolean bool1 = borrowService.back1(borrow);
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
			if(bool1 || bool2) {
				response.sendRedirect("MyBorrowServlet");
			}
		}else if(identity.equals("manager")) {
			Date endTime = borrowService.findEndTime2(id);
			boolean due = inTime.after(endTime);
			ManagerBorrow borrow = new ManagerBorrow();
			borrow.setId(id);
			borrow.setDue(due);
			borrow.setInTime(inTime);
			boolean bool1 = borrowService.back2(borrow);
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
			if(bool1 || bool2) {
				response.sendRedirect("MyBorrowServlet");
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
