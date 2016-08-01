package com.yuanjunye.www.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuanjunye.www.Exception.ServiceException;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.IBookService;
import com.yuanjunye.www.service.IBookTypeService;
import com.yuanjunye.www.service.ILoginService;
import com.yuanjunye.www.service.IRegisterService;
import com.yuanjunye.www.service.IUserService;

@Controller
@RequestMapping("/log")
public class LogController {

	@Resource
	private ILoginService loginService;
	@Resource
	private IBookService bookService;
	@Resource
	private IBookTypeService bookTypeService;  
	@Resource
	private IUserService userService;
	@Resource
	private IRegisterService registerService;
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/login2")
	public ModelAndView login2(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String identity = (String)session.getAttribute("identity");
		String photo = "";
		if(identity.equals("学生")) {
			photo = userService.findStudentPhoto(userName);
		}else {
			photo = userService.findManagerPhoto(userName);
		}
		Set<Books> booksSet = bookService.showAllBooks1();
		Set<String> typeNameSet = bookTypeService.allTypeName();
		session.setAttribute("typeNameSet", typeNameSet);
		session.setAttribute("booksSet", booksSet);
		session.setAttribute("photo", photo);
		mav.setViewName("log/main");
		return mav;
	}
	
	/**
	 * 登录
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user")User user,HttpServletRequest request, HttpServletResponse response,String code) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if (!(code.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写
			mav.setViewName("log/login");
		      mav.addObject("error1","验证码不正确");
		      return mav;
		}
		if(!loginService.verifyUser(user)) {
			mav.addObject("userName", user.getUserName());
			mav.addObject("password", user.getPassword());
			mav.addObject("identity", user.getIdentity());
			mav.addObject("error1", "登录失败，请核对登录信息");
			mav.setViewName("log/login");
		}else {
			cook(request,response);
			session.setAttribute("identity", user.getIdentity());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("user",user);
			mav.setViewName("redirect:/log/login2.do");
		} 
		return mav;
	}
	
	@RequestMapping("/cancel") 
	public String cancel(HttpSession session) {
		session.invalidate();
		return "log/login";
	}
	
	@RequestMapping("/toLogin") 
	public String toLogin() {
		return "log/login";
	}
	
	@RequestMapping("/toRegister") 
	public String toRegister() {
		return "log/register";
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request,User user, Student student, Manager manager,@RequestParam(value="ids",required=false)String ids) {
		ModelAndView mav = new ModelAndView();
		boolean bool = false;
		String name = request.getParameter("name");
		String identity = request.getParameter("identity");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		request.setAttribute("userName", userName);
		request.setAttribute("identity", identity);
		request.setAttribute("name", name);
		request.setAttribute("id", ids);
		request.setAttribute("email", email);
		Map<String,String> errorMap = registerService.checkAll(userName, ids, email,identity);
		mav.addObject("errorMap", errorMap);	
		if(identity.equals("管理员"))  {
			manager.setManagerId(Integer.valueOf(ids));
			manager.setManagerName(name);
			manager.setPhoto("dog.jpg");
			if(errorMap.isEmpty()){
				bool = registerService.maRegister(user, manager,email);
			}
		}else {
			student.setStudentId(Long.valueOf(ids));
			student.setStudentName(name);
			student.setPhoto("dog.jpg");
			if(errorMap.isEmpty()){
				bool = registerService.stRegister(user, student,email);			
			}
		}
		if(bool) {
			mav.setViewName("log/registerSuccess");
		}else {
			mav.setViewName("log/register");
		}
		return mav;
	}
	
	@RequestMapping("/validate")
	public ModelAndView validate(HttpServletRequest request) throws ServiceException {
		ModelAndView mav = new ModelAndView();
		String email = request.getParameter("email");//获取email
        String validateCode = request.getParameter("validateCode");//激活码
        registerService.processActivate(email , validateCode);//调用激活方法
        mav.setViewName("log/validateSuccess");
		return mav;
	}
	
	 
	
	/**
	 * cookie记住密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void cook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String identity = request.getParameter("identity");
		String[] isCookies = request.getParameterValues("checkBox");
		if(isCookies!=null && isCookies.length>0) {
			Cookie userNameCookie = new Cookie("userName", userName);				//创建cookie
			Cookie passwordCookie = new Cookie("password", password);
			Cookie identityCookie = new Cookie("identity", URLEncoder.encode(identity, "utf-8"));	//中文
			identityCookie.setMaxAge(86400);							//cookie生存周期为一天
			userNameCookie.setMaxAge(86400);
			passwordCookie.setMaxAge(86400);
			response.addCookie(userNameCookie);							//添加cookie
			response.addCookie(passwordCookie);
			response.addCookie(identityCookie);
		}else {
			Cookie[] cookies = request.getCookies();	
			if(cookies != null && cookies.length > 0) {
				for(Cookie c : cookies) {								//遍历cookie
					if(c.getValue().equals(userName) || c.getValue().equals(password) 
							||c.getValue().equals(URLEncoder.encode(identity, "utf-8")))  {
						c.setMaxAge(0);									//使cookie失效
						response.addCookie(c);							//添加失效cookie
					}
				}
			}
		}
	}
	
}
