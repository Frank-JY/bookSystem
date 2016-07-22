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
import com.yuanjunye.www.service.BookService;

/**
 * 热门图书
 * @author hasee
 *
 */
@WebServlet("/HotBookServlet")
public class HotBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	
    public HotBookServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Books> hotBookList = bookService.hotBook();
		session.setAttribute("hotBookList", hotBookList);
		response.sendRedirect("searchHotBook.jsp");
		return;
	}

}
