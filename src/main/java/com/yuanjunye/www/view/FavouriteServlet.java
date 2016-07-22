package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.FavouriteService;

/**
 * 显示个人收藏夹图书
 * @author hasee
 *
 */
@WebServlet("/FavouriteServlet")
public class FavouriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FavouriteService favouriteService = new FavouriteService();
	private BookService bookService = new BookService(); 
	
    public FavouriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		List<Books> booksList = new ArrayList<Books>();
		List<String> bookIdList = favouriteService.findCollectBookId(userName);
		for(String bookId : bookIdList) {
			Books book = bookService.findBook(bookId);
			booksList.add(book);
		}
		request.setAttribute("booksList", booksList);
		request.getRequestDispatcher("favourite.jsp").forward(request, response);
	}

}
