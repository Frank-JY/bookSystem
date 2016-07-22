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
 * 模糊搜索图书
 * @author hasee
 *
 */
@WebServlet("/ShowAllBooks1Servlet")
public class ShowAllBooks1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookService();
   
    public ShowAllBooks1Servlet() {
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
		String keyword = request.getParameter("keyword");
		Map<Books, String> booksMap = bookService.searchBooks(keyword);
		session.setAttribute("booksMap", booksMap);
		response.sendRedirect("allBooks.jsp");
	}

}
