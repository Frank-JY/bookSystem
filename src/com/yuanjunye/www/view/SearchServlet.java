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
 * 搜索图书
 * @author hasee
 *
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService(); 
	
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("keyword")) {
			HttpSession session = request.getSession();
			String keyword = request.getParameter("keyword");
			Map<Books, String> searchBooksMap = bookService.searchBooks(keyword);
			session.setAttribute("searchBooksMap", searchBooksMap);
			response.sendRedirect("search.jsp");
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
