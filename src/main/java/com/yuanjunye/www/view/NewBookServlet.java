package com.yuanjunye.www.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 每日新书
 * @author hasee
 *
 */
@WebServlet("/NewBookServlet")
public class NewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService();

    public NewBookServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当天日期
		try {
			HttpSession session = request.getSession();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime  = new Date();
			Date time = new Date();
			String s = df.format(currentTime);
			time = df.parse(s);
			List<Books> newBookList = bookService.newBook(time);
			session.setAttribute("newBookList", newBookList);
			response.sendRedirect("searchNewBook.jsp");
			return;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
