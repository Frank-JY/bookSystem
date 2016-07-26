package com.yuanjunye.www.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.ManagerBorrow;
import com.yuanjunye.www.po.Param1;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.StudentBorrow;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.IBorrowService;
import com.yuanjunye.www.service.IUserService;

@Controller
@RequestMapping("/user")
@SessionAttributes({"user","identity"})
public class UserController {

	@Resource
	private IUserService userService;
	@Resource
	private IBorrowService borrowService;
	
	/**
	 * 个人信息
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/myUser")
	public ModelAndView myUser(User user,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String userName = user.getUserName();
		String identity = user.getIdentity();
		if(identity.equals("学生")) {
			User user1 = userService.myStudent(userName);
			request.setAttribute("user1", user1);
			mav.setViewName("user/myStudent");
		}else {
			User user1 = userService.myManager(userName);
			request.setAttribute("user1", user1);
			mav.setViewName("user/myManager");
		}
		return mav;
	}
	
	/**
	 * 更新用户信息
	 * @param identity
	 * @param student
	 * @param manager
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateUser",method= RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("identity")String identity, Student student, Manager manager
			,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();	
		boolean bool = false;
		if(identity.equals("管理员")) {
			bool = userService.updateManager(manager);
		}else {
			bool = userService.updateStudent(student);
		}
		if(bool) {
			mav.setViewName("user/updateSuccess");
		}else {
			mav.setViewName("user/updatefailure");
		}
		return mav;
	}
	
	/**
	 * 跳转到图形修改页面
	 * @param identity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toUpdatePhoto1")
	public ModelAndView toUpdatePhoto1(@ModelAttribute("identity")String identity,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		if(identity.equals("管理员")) {
			mav.setViewName("user/updatePhoto1");
		}else {
			mav.setViewName("user/updatePhoto1");
		}
		return mav;
	}
	
	/**
	 * 修改头像
	 * @param identity
	 * @param photo
	 * @param request
	 * @param userId
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/updatePhoto1",method= RequestMethod.POST)
	public ModelAndView updatePhoto1(@ModelAttribute("identity")String identity,@RequestParam(value = "photo", required = false) MultipartFile photo,HttpServletRequest request,String userId)
			throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();

		String savePath = request.getSession().getServletContext().getRealPath("/image/upload");
		String tempPath = request.getSession().getServletContext().getRealPath("/image/temp");
		File tmpFile = new File(tempPath); 
		File saFile = new File(savePath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		if (!saFile.exists()) {
			saFile.mkdir();
		}
		Student student = new Student();
		Manager manager = new Manager();
		
		if(identity.equals("学生")) {
			student.setStudentId(Long.valueOf(userId));
			student.setPhoto("dog.jpg");
		}else {
			manager.setManagerId(Integer.valueOf(userId));
			manager.setPhoto("dog.jpg");
		}
		String fileName = photo.getOriginalFilename();
		if(!"".equals(fileName)) {
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			String saveFilename = makeFileName(fileName);
			
			String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
			//后缀格式
			if(!(fileExtName.equalsIgnoreCase("jpg") || fileExtName.equalsIgnoreCase("png")
					|| fileExtName.equalsIgnoreCase("jpeg") || fileExtName.equalsIgnoreCase("gif"))) {
				request.setAttribute("error2", fileExtName+"格式不符合要求");
				mav.setViewName("user/updatePhoto1");
				return mav;
			}
			try {  
	            photo.transferTo(new File(savePath + "\\" + saveFilename));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
			if(identity.equals("学生")) {
				student.setPhoto(saveFilename);
			}else {
				manager.setPhoto(saveFilename);
			}
		}
		boolean bool = false;
		if(identity.equals("学生")) {
			bool = userService.updateStudentPhoto(student);
		}else {
			bool = userService.updateManagerPhoto(manager);
		}	
		
		if(bool) {
			mav.setViewName("user/updateSuccess");
		}else {
			mav.setViewName("user/updatefailure");
		}
		return mav;
		
	}
	
	/**
	 * 显示等待申请的用户
	 * @return
	 */
	@RequestMapping(value="/passUser")
	public ModelAndView passUser() {
		ModelAndView mav = new ModelAndView();
		
		List<User> studentList = userService.showApplyStudent();
		List<User> managerList = userService.showApplyManager();
		
		mav.addObject("studentList", studentList);
		mav.addObject("managerList", managerList);
		mav.setViewName("user/passUser");
		return mav;
	}
	
	/**
	 * 对申请注册的用户进行操作
	 * @param action
	 * @param userName
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/passUserAction")
	public ModelAndView passUserAction(String action,String userName,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		if("pass".equals(action)) {
			userService.passUser(userName);
			mav.setViewName("redirect:/user/passUser.do");
			return mav;
		}
		if("reject".equals(action)) {
			userService.rejectUser(userName);
			mav.setViewName("redirect:/user/passUser.do");
			return mav;
		}
		if("delete".equals(action)) {
			userService.rejectUser(userName);
			mav.setViewName("redirect:/user/showAllUser.do");
			return mav;
		}
		if("borrow".equals(action)) {
			String identity = userService.findIdentity(userName);
			if(identity.equals("学生")) { 
				List<StudentBorrow> borrowList = borrowService.myStudentBorrow(userName);
				Student student = borrowService.findStudent(userName);
				String name = student.getStudentName();
				session.setAttribute("borrowName", name);
				session.setAttribute("borrowList", borrowList);
				mav.setViewName("borrow/findStudentBorrow");
				return mav;
			}else {
				List<ManagerBorrow> borrowList = borrowService.myManagerBorrow(userName);
				Manager manager = borrowService.findManager(userName);
				String name = manager.getManagerName();
				session.setAttribute("borrowName", name);
				session.setAttribute("borrowList", borrowList);
				mav.setViewName("borrow/findManagerBorrow");
				return mav;
			}
		}
		mav.setViewName("../ban");
		return mav;
	}
	
	/**
	 * 显示所有用户
	 * @param request
	 * @param action
	 * @return
	 */
	@RequestMapping(value="/showAllUser")
	public ModelAndView showAllUser(HttpServletRequest request, String action) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		List<User> studentList = userService.showAllStudent();
		List<User> managerList = userService.showAllManager();
		request.setAttribute("studentList", studentList);
		request.setAttribute("managerList", managerList);	
		mav.addObject("studentList", studentList);
		mav.addObject("managerList", managerList);	
		if(action.equals("1")) {
			mav.setViewName("user/allStudent");
		}else {
			mav.setViewName("user/allManager");
		}
		return mav;
	}
	
	/**
	 * 模糊查询用户
	 * @param request
	 * @param action
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/showAllUser1")
	public ModelAndView showAllUser1(HttpServletRequest request, String action, String keyword) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(null == keyword) {
			mav.setViewName("ban");
			return mav;
		}
		List<User> studentList = userService.searchStudent(keyword);
		List<User> managerList = userService.searchManager(keyword);
		request.setAttribute("keyword", keyword);
		request.setAttribute("studentList", studentList);
		request.setAttribute("managerList", managerList);
		if(action.equals("1")) {
			mav.setViewName("user/allStudent");
		}else {
			mav.setViewName("user/allManager");
		}
		return mav;
	}
	
	/**
	 * 跳转到多条件查询页面
	 * @return
	 */
	@RequestMapping("/toCondition") 
	public String condition() {
		return "user/condition";
	}
	
	/**
	 * 多条件查询
	 * @param request
	 * @param param
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/condition")
	public ModelAndView condition(HttpServletRequest request, Param1 param, String value) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(null != value) {	
			if(value.equals("1")) {
				List<User> studentList = userService.conditionStudent(param);
				request.setAttribute("studentList", studentList);
				mav.setViewName("user/allStudent");
			}else if(value.equals("2")) {
				List<User> managerList = userService.conditionManager(param);
				request.setAttribute("managerList", managerList);
				mav.setViewName("user/allManager");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequestMapping("/toPassword") 
	public String toPassword() {
		return "user/password";
	}
	
	/**
	 * 修改密码操作
	 * @param request
	 * @param userName
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	@RequestMapping(value="/password")
	public ModelAndView password(HttpServletRequest request, String userName, String newPassword, String oldPassword) {
		ModelAndView mav = new ModelAndView();
		if(null != userName) {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			boolean bool = userService.updatePassword(user,newPassword,oldPassword);
			if(bool) {
				user.setPassword(newPassword);
				session.setAttribute("user", user);
				mav.setViewName("user/updateSuccess");		
			}else {
				request.setAttribute("error", "密码错误,请重新输入");
				mav.setViewName("user/password");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 查询单个用户信息
	 * @param request
	 * @param userName
	 * @param messageTag
	 * @return
	 */
	@RequestMapping(value="/lookUser")
	public ModelAndView lookUser(HttpServletRequest request,String userName, String messageTag) {
		ModelAndView mav = new ModelAndView();
		if(null != userName) {
			HttpSession session = request.getSession();
			session.setAttribute("messageTag", messageTag);
			String identity = userService.findIdentity(userName);
			if(identity.equals("学生")){
				User user = userService.myStudent(userName);
				session.setAttribute("lookUser", user);
				mav.setViewName("user/lookStudent");
			}else {
				User user = userService.myManager(userName);
				session.setAttribute("lookUser", user);
				mav.setViewName("user/lookManager");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	
	
	
	
	
	
	private String makeFileName(String filename){  
		return UUID.randomUUID().toString() + "0000" + filename;
	}
	
	
}
