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
 * 删除收藏夹图书
 * @author hasee
 *
 */
@WebServlet("/DeleteFavouriteServlet")
public class DeleteFavouriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FavouriteService favouriteService = new FavouriteService();
	
    public DeleteFavouriteServlet() {
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
		boolean bool = favouriteService.deleteFavouriteDao(favourite);
		if(bool) {
			response.sendRedirect("FavouriteServlet");
		}
	}

}
