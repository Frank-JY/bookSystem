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
 * 根据图书类型查询图书
 * @author hasee
 *
 */
@WebServlet("/TypeSearchServlet")
public class TypeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService();
	
    public TypeSearchServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("typeName")) {
			HttpSession session = request.getSession();
			String typeName = request.getParameter("typeName");
			typeName = new String(typeName.getBytes("ISO-8859-1"), "UTF-8");  
			Map<Books, String> searchBooksMap = bookService.searchBooks2(typeName);
			session.setAttribute("searchBooksMap", searchBooksMap);
			response.sendRedirect("search.jsp");
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
