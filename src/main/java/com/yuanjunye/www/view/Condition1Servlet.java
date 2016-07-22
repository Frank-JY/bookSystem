package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Param2;
import com.yuanjunye.www.service.BookService;

/**
 * 多条件查询图书
 * @author hasee
 *
 */
@WebServlet("/Condition1Servlet")
public class Condition1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService(); 
	
    public Condition1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("value")) {
			String value = request.getParameter("value");
			HttpSession session = request.getSession();
			String bookId = request.getParameter("bookId");
			String bookName = request.getParameter("bookName");
			String author = request.getParameter("author");
			String publishers = request.getParameter("publishers");
			String typeName= request.getParameter("typeName");
			Param2 param = new Param2();
			param.setBookId(bookId);
			param.setBookName(bookName);
			param.setAuthor(author);
			param.setPublishers(publishers);
			param.setTypeName(typeName);
			List<Books> booksList = bookService.searchBooks1(param);
			if(value.equals("1")) {
				session.setAttribute("searchBooksList", booksList);
				response.sendRedirect("search.jsp");
				return;
			}else if(value.equals("2")) {
				session.setAttribute("booksList", booksList);
				response.sendRedirect("allBooks.jsp");
				return;
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
