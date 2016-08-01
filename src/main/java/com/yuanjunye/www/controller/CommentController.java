package com.yuanjunye.www.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.service.IBookService;
import com.yuanjunye.www.service.ICommentService;
import com.yuanjunye.www.service.IUserService;
import com.yuanjunye.www.util.PageUtil;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private ICommentService commentService;
	@Resource
	private IBookService bookService;
	@Resource
	private IUserService userService;
	
	/**
	 * 发表评论
	 * @param request
	 * @param tag
	 * @param comment
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value="/comment")
	public ModelAndView comment(HttpServletRequest request, String tag,Comment comment, String bookId) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		if(null != comment)  {
			
			boolean bool = commentService.publish(comment,userName);
			if(bool) {
				mav.setViewName("redirect:/book/bookDetails.do?bookId="+bookId+"&tag="+tag);
			}else {
				mav.setViewName("user/updatefailure");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 管理员删除评论
	 * @param request
	 * @param id
	 * @param bookId
	 * @param tag
	 * @return
	 */
	@RequestMapping(value="/deleteComment")
	public ModelAndView deleteComment(HttpServletRequest request, Integer id,String bookId, String tag) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(null != id) {
			boolean bool = commentService.deleteComment(id);
			if(bool) {
				mav.setViewName("redirect:/book/bookDetails.do?bookId="+bookId+"&tag="+tag);
			}else {
				mav.setViewName("user/updatefailure");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 查看个人书评
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/comment1")
	public ModelAndView comment1(HttpServletRequest request, String page) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();	
		String userName = (String)session.getAttribute("userName");
		int currentPage = 1;
		if(null != page) {
			currentPage = Integer.valueOf(page);
		}
		PageUtil pUtil = commentService.findPage(userName, currentPage);
		List<Comment> myCommentList = commentService.myComment(userName,pUtil);
		session.setAttribute("pUtil", pUtil);
		session.setAttribute("myCommentList", myCommentList);
		mav.setViewName("comment/myComment");
		return mav;
	}
	
	/**
	 * 跳转到个人书评页面
	 * @return
	 */
	@RequestMapping("/toMyComment") 
	public String toMyComment() {
		return "comment/myComment";
	}
	
	/**
	 * 个人删除个人评论
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteComment1")
	public ModelAndView deleteComment1(HttpServletRequest request,Integer id) {
		ModelAndView mav = new ModelAndView();
		if(null != request.getParameter("id")) {
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			Comment comment = new Comment();
			comment.setId(id);
			comment.setUserName(userName);
			boolean bool = commentService.deleteComment1(comment);
			if(bool) {
				mav.setViewName("redirect:/comment/comment1.do");
			}else {
				mav.setViewName("user/updatefailure");
			}
		}else {
			mav.setViewName("ban");
		}	
		return mav;
	}
	
}
