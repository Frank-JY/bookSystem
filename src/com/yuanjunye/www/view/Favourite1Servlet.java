package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Favourite;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.FavouriteService;

/**
 * 收藏预处理
 * @author hasee
 *
 */
@WebServlet("/Favourite1Servlet")
public class Favourite1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FavouriteService favouriteService = new FavouriteService();
    
    public Favourite1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		String bookId = request.getParameter("bookId");
		Favourite favourite = new Favourite();
		favourite.setUerName(userName);
		favourite.setBookId(bookId);
		if(!favouriteService.isRepeatDao(favourite)) {
			request.setAttribute("error", "你已经收藏了该图书！");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
			return;
		}
		if(!favouriteService.isCollectDao(userName)) {
			request.setAttribute("error", "你的收藏夹已经有10本图书了，去清理一下吧！");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
			return;
		}
		if(favouriteService.collectDao(favourite)) {
			request.setAttribute("error", "收藏成功！");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
			return;
		}
	}

}
