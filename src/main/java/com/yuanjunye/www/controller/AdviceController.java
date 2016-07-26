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

import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.service.IAdviceService;
import com.yuanjunye.www.service.IUserService;

@Controller
@RequestMapping("/advice")
public class AdviceController {

	@Resource
	private IAdviceService adviceService;
	@Resource
	private IUserService userService;
	
	@RequestMapping("/toAdvice") 
	public String toAdvice() {
		return "advice/advice";
	}
	
	/**
	 * 提交意见
	 * @param request
	 * @param advice
	 * @return
	 */
	@RequestMapping(value="/advice")
	public ModelAndView deleteComment1(HttpServletRequest request,String advice) {
		ModelAndView mav = new ModelAndView();
		if(null != advice) {
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			boolean bool = adviceService.addAdvice(userName,advice);
			if(bool) {
				mav.setViewName("user/updateSuccess");
			}else {
				mav.setViewName("user/updatefailure");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 查看意见反馈
	 * @param request
	 * @param advice
	 * @return
	 */
	@RequestMapping(value="/advice1")
	public ModelAndView advice1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		List<Advice> adviceList = adviceService.ShowAdvice();
		session.setAttribute("adviceList", adviceList);
		mav.setViewName("advice/showAdvice");
		return mav;
	}
	
	/**
	 * 跳转到查询意见的页面
	 * @return
	 */
	@RequestMapping("/toShowAdvice") 
	public String toShowAdvice() {
		return "advice/showAdvice";
	}
	
	/**
	 * 删除意见
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteAdvice")
	public ModelAndView deleteAdvice(HttpServletRequest request, Integer id) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(null != id) {
			boolean bool = adviceService.deleteAdvice(id);
			if(bool) {
				mav.setViewName("redirect:/advice/advice1.do");
			}else {
				mav.setViewName("user/updatefailure");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
}
