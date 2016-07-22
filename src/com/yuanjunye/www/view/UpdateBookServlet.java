package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.service.BookService;

/**
 * 修改图书
 * @author hasee
 *
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private BookService bookService = new BookService();
	
    public UpdateBookServlet() {
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
		if(null != request.getParameter("bookId")) {
			String bookId = request.getParameter("bookId");
			String bookName = request.getParameter("bookName");
			String author = request.getParameter("author");
			String publishers = request.getParameter("publishers");
			int typeId = Integer.valueOf(request.getParameter("typeId"));
			int amount = Integer.valueOf(request.getParameter("amount"));
			int total = Integer.valueOf(request.getParameter("total"));
			int oldTotal = Integer.valueOf(request.getParameter("oldTotal"));
			String remarks = request.getParameter("remarks");
			amount = (total - oldTotal) + amount;
			if(amount < 0) {
				request.setAttribute("error", "图书总数量不应少于已借出图书的数量");
				request.getRequestDispatcher("updateBook.jsp").forward(request, response);
				return;
			}
			String status = request.getParameter("status");
			if(!status.equals("已下架")) {
				if(amount == 0) {
					status = "无库存";
				}else {
					status = "可借阅";
				}
			}
			Books book = new Books();
			book.setBookId(bookId);
			book.setBookName(bookName);
			book.setAuthor(author);
			book.setPublishers(publishers);
			book.setTypeId(typeId);
			book.setAmount(amount);
			book.setTotal(total);
			book.setRemarks(remarks);
			book.setStatus(status);
			boolean bool = bookService.updateBook(book);
			if(bool) {
				response.sendRedirect("updateSuccess.jsp");
			}else {
				response.sendRedirect("updateFailure.jsp");
			}
		}else {
			response.sendRedirect("ban.jsp");
		}
	}

}
