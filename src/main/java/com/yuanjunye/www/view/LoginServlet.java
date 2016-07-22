package com.yuanjunye.www.view;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.User;
import com.yuanjunye.www.service.LoginService;

/**
 * 登录预处理
 * @author hasee
 *
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LoginService loginService = new LoginService();
    
    public LoginServlet() {
        super();    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null == request.getParameter("userName")) {
			response.sendRedirect("login.jsp");
			return;
		}
		User user = new User();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String identity = request.getParameter("identity");
		user.setUserName(userName);
		user.setPassword(password);
		user.setIdentity(identity);
		if(!loginService.verifyUser(user)) {
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("identity", identity);
			request.setAttribute("error1", "登录失败，请核对登录信息");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else {		
			cook(request,response);
			HttpSession session = request.getSession();
			session.setAttribute("identity", identity);
			session.setAttribute("userName", userName);
			session.setAttribute("user",user);
			response.sendRedirect("Login1Servlet");
		}

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
