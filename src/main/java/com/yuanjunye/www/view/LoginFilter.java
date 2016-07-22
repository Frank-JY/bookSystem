package com.yuanjunye.www.view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器
 * @author hasee
 *
 */
public class LoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest) arg0;						//类型强制转换
		HttpServletResponse response =(HttpServletResponse) arg1;
		request.setCharacterEncoding("UTF-8");										//中文编码
		String uri = request.getRequestURI();										//获取URI
		if(uri.endsWith("login.jsp") || uri.endsWith("LoginServlet") 
				|| uri.endsWith("register.jsp") || uri.endsWith("RegisterServlet") 
				|| uri.endsWith("loginHead.jsp") || uri.endsWith("loginHead.css")
				|| uri.endsWith("foot.jsp") || uri.endsWith("foot.css")
				|| uri.endsWith("loginback.jpeg") || uri.endsWith("uni.jpg")
				|| uri.endsWith("cancel.jpg")){
			chain.doFilter(request, response);										
			return; 
		}
		HttpSession session = request.getSession();
		if(null == session.getAttribute("user")) {
			response.sendRedirect("login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
		

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
