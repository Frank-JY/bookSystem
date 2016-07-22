package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
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
			String sql = "select * from t_books a,t_booktype b where a.typeId=b.typeId ";
			if(!"".equals(bookId)) {
				sql += "and bookId = '" + bookId + "'";
			}
			if(!"".equals(bookName)) {
				sql += "and bookName = '" + bookName + "'";
			}
			if(!"".equals(author)) {
				sql += "and author = '" + author + "'";
			}
			if(!"".equals(publishers)) {
				sql += "and publishers = '" + publishers + "'";
			}
			if(!"".equals(typeName)) {
				sql += "and typeName = '" + typeName + "'";
			}
			Map<Books, String> searchBooksMap = bookService.searchBooks1(sql);
			if(value.equals("1")) {
				session.setAttribute("searchBooksMap", searchBooksMap);
				response.sendRedirect("search.jsp");
				return;
			}else if(value.equals("2")) {
				session.setAttribute("booksMap", searchBooksMap);
				response.sendRedirect("allBooks.jsp");
				return;
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
