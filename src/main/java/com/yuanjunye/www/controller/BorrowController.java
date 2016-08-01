package com.yuanjunye.www.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuanjunye.www.dto.BorrowUser;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.IBookService;
import com.yuanjunye.www.service.IBorrowService;
import com.yuanjunye.www.service.ICommentService;
import com.yuanjunye.www.util.PageUtil;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	
	@Resource
	private IBookService bookService;
	@Resource
	private IBorrowService borrowService; 
	@Resource
	private ICommentService commentService;
	
	/**
	 * 申请借阅（第一步）
	 * @param request
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value="/borrow")
	public ModelAndView borrow(HttpServletRequest request, String bookId) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String identity = user.getIdentity();
		String userName = user.getUserName();
		if(null != bookId) {
			String status = bookService.findStatus(bookId);
			if(status.equals("可借阅")) {	
				BorrowUser borrowUser = borrowService.showBorrowUserMessage(bookId,identity,userName);
				borrowUser.setBookId(bookId);
				mav.addObject("borrowUser", borrowUser);
				mav.setViewName("borrow/borrow");
			}else if(status.equals("无库存")) {
				request.setAttribute("error", "这本图已经被借光了，先收藏起来吧");
				mav.setViewName("book/bookDetails");
			}else if(status.equals("已下架")) {
				request.setAttribute("error", "这本图已经下架，先收藏起来吧");
				mav.setViewName("book/bookDetails");
			}else {
				mav.setViewName("redirect:/log/login2.do");
			}
		}else {  
			mav.setViewName("ban");
		}		
		return mav;
	}
	
	/**
	 * 跳转到到图书细节页面
	 * @return
	 */
	@RequestMapping("/toBookDetails") 
	public String condition(String page, HttpServletRequest request,String bookId) {
		HttpSession session = request.getSession();	
		int currentPage = 1;
		if(null != page) {
			currentPage = Integer.valueOf(page);
		}
		PageUtil pUtil = commentService.findBookPage(bookId, currentPage);
		List<Comment> commentList = commentService.bookComment(bookId,pUtil);
		session.setAttribute("commentList", commentList);
		session.setAttribute("pUtil", pUtil);
		return "book/bookDetails";
	}
	
	/**
	 * 借阅申请（第二步）
	 * @param request
	 * @param identity
	 * @param bookId
	 * @param maBorrow
	 * @param stBorrow
	 * @return
	 */
	@RequestMapping(value="/borrow1")
	public ModelAndView borrow1(HttpServletRequest request, String identity,String bookId
			, ManagerBorrow maBorrow,StudentBorrow stBorrow, String readerId) {
		ModelAndView mav = new ModelAndView();
		if(null != bookId) {
			String status = "";
			if(identity.equals("学生")) {
			    status = borrowService.findStatus1(stBorrow);
			}else {
				status = borrowService.findStatus2(maBorrow);
			}
			mav.addObject("bookId", bookId);
			if(status.equals("等待申请")){
				request.setAttribute("error", "申请已经发送,请等待管理员确认");
				mav.setViewName("forward:/borrow/borrow.do");		
				return mav;
			}else if(status.equals("借阅中")) {
				request.setAttribute("error", "你已经成功借阅此图书，请勿重复借阅");
				mav.setViewName("forward:/borrow/borrow.do");		
				return mav;
			}else {
				boolean bool = borrowService.updateAll(bookId, stBorrow, maBorrow,identity,readerId);
				if(bool) {
					mav.setViewName("user/updateSuccess");
				}else {
					mav.setViewName("user/updatefailure");
				}
			}		
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 查看借阅申请记录
	 * @param request
	 * @param action
	 * @return
	 */
	@RequestMapping(value="/passBorrow")
	public ModelAndView passBorrow(HttpServletRequest request, String action) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(request.getParameter("action").equals("1")) {
			List<StudentBorrow> stBorrowList = borrowService.applyStudentBorrowDao();
			mav.addObject("stBorrowList", stBorrowList);
			mav.setViewName("borrow/passStudentBorrow");
		}else {
			List<ManagerBorrow> maBorrowList = borrowService.applyManagerBorrowDao();
			mav.addObject("maBorrowList", maBorrowList);
			mav.setViewName("borrow/passManagerBorrow");
		}
		return mav;
	}
	
	/**
	 * 对借阅申请进行操作
	 * @param request
	 * @param action
	 * @return
	 */
	@RequestMapping(value="/passBorrowAction")
	public ModelAndView passBorrowAction(HttpServletRequest request, String action, String identity, RedirectAttributes attr,Integer id) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(action.equals("pass")) {
			boolean bool = borrowService.passBorrow(identity, id);
			if(bool) {
				if(identity.equals("student")) {			
					attr.addAttribute("action", "1");
				}else {	
					attr.addAttribute("action", "2");
			}
			mav.setViewName("forward:/borrow/passBorrow.do");
			return mav;
			}
		}else if(action.equals("reject")) {
			boolean bool = borrowService.rejectBorrow(identity, id);
			if(bool) {
				if(identity.equals("student")) {
					attr.addAttribute("action", "1");
					mav.setViewName("forward:/borrow/passBorrow.do");
				}else {
					attr.addAttribute("action", "2");
					mav.setViewName("forward:/borrow/passBorrow.do");
				}	
			}else {
				mav.setViewName("ban");
			}
		}	
		return mav;
	}
	
	/**
	 * 查看个人借阅信息
	 * @param request
	 * @param tag1
	 * @return
	 */
	@RequestMapping(value="/myBorrow")
	public ModelAndView myBorrow(HttpServletRequest request, String tag1) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String identity = user.getIdentity();
		String userName = user.getUserName();
		if(identity.equals("学生")) {
			List<StudentBorrow> myBorrowList = borrowService.myStudentBorrow(userName);
			Student student = borrowService.findStudent(userName);
			String name = student.getStudentName();
			session.setAttribute("myborrowName", name);
			session.setAttribute("myBorrowList", myBorrowList);
			mav.setViewName("borrow/myStudentBorrow");
		}else {
			List<ManagerBorrow> myBorrowList = borrowService.myManagerBorrow(userName);
			Manager manager = borrowService.findManager(userName);
			String name = manager.getManagerName();
			session.setAttribute("myborrowName", name);
			session.setAttribute("myBorrowList", myBorrowList);
			mav.setViewName("borrow/myManagerBorrow");
		}	
		return mav;
	}
	
	/**
	 * 跳转到个人申请页面
	 * @return
	 */
	@RequestMapping("/toMyStudentBorrow") 
	public String toUpdatePhoto() {
		return "borrow/myStudentBorrow";
	}
	
	/**
	 * 跳转到个人申请页面
	 * @return
	 */
	@RequestMapping("/toMyManagerBorrow") 
	public String toMyManagerBorrow() {
		return "borrow/myManagerBorrow";
	}
	
	/**
	 * 归还图书
	 * @param request
	 * @param id
	 * @param identity
	 * @return
	 */
	@RequestMapping(value="/backBook")
	public ModelAndView backBook(HttpServletRequest request, Integer id,String identity) {
		ModelAndView mav = new ModelAndView();
		//归还时间
		boolean bool = borrowService.backBook(identity, id);
		if(bool) {
			mav.setViewName("redirect:/borrow/myBorrow.do");
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	
	
	
}
