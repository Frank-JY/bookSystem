package com.yuanjunye.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Favourite;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.IBookService;
import com.yuanjunye.www.service.IFavouriteService;

@Controller
@RequestMapping("/favourite")
public class FavouriteController {

	@Resource
	private IFavouriteService favouriteService;
	@Resource
	private IBookService bookService;

	/**
	 * 收藏图书操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/favourite1")
	public ModelAndView favourite1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		String bookId = request.getParameter("bookId");
		Favourite favourite = new Favourite();
		favourite.setUerName(userName);
		favourite.setBookId(bookId);
		if(favouriteService.isRepeatDao(favourite)) {
			request.setAttribute("error", "你已经收藏了该图书！");
			mav.setViewName("book/bookDetails");
			return mav;
		}
		if(!favouriteService.isCollectDao(userName)) {
			request.setAttribute("error", "你的收藏夹已经有10本图书了，去清理一下吧！");
			mav.setViewName("book/bookDetails");
			return mav;
		}
		if(favouriteService.collectDao(favourite)) {
			request.setAttribute("error", "收藏成功！");
			mav.setViewName("book/bookDetails");
			return mav;
		}	
		return mav;
	}
	
	/**
	 * 显示收藏夹
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/favourite")
	public ModelAndView favourite(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		List<Books> booksList = favouriteService.findCollectBookId(userName);
		request.setAttribute("booksList", booksList);
		mav.setViewName("favourite/favourite");
		return mav;
	}
	
	/**
	 * 删除收藏夹图书
	 * @param request
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value="/deleteFavourite")
	public ModelAndView deleteFavourite(HttpServletRequest request, String bookId) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		Favourite favourite = new Favourite();
		favourite.setUerName(userName);
		favourite.setBookId(bookId);
		boolean bool = favouriteService.deleteFavouriteDao(favourite);
		if(bool) {
			mav.setViewName("redirect:/favourite/favourite.do");
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	
	
	
}
