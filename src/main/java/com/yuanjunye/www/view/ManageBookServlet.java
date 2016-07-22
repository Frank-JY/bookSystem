package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.BookType;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.BookTypeService;

/**
 * 管理图书操作
 * @author hasee
 *
 */
@WebServlet("/ManageBookServlet")
public class ManageBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService(); 
	private BookTypeService bookTypeService = new BookTypeService();
    
    public ManageBookServlet() {
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
		if(request.getParameter("action").equals("delete")) {
			boolean bool = bookService.deleteBook(request.getParameter("bookId"));
			if(bool) {
				response.sendRedirect("updateSuccess.jsp");
			}else { 
				request.setAttribute("error", "操作失败：该图书还没有全部归还，不可删除。可以先下架图书等待全部归还后删除该图书信息");
				request.getRequestDispatcher("ShowAllBooksServlet").forward(request, response);
				return;
			}
		}
		if(request.getParameter("action").equals("in")) {
			boolean bool = bookService.inBook(request.getParameter("bookId"));
			if(bool) {
				response.sendRedirect("ShowAllBooksServlet");
			}
		}
		if(request.getParameter("action").equals("out")) {
			boolean bool = bookService.outBook(request.getParameter("bookId"));
			if(bool) {
				response.sendRedirect("ShowAllBooksServlet");
			}
		}
		if(request.getParameter("action").equals("update")) {
			Books book = bookService.showBook(request.getParameter("bookId"));
			session.setAttribute("book", book);
			List<BookType> bookTypeList = bookTypeService.showBookType();
			session.setAttribute("bookTypeList", bookTypeList);
			response.sendRedirect("updateBook.jsp");
		}
	}

}
