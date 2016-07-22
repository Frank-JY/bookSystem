package com.yuanjunye.www.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.service.AdviceService;

/**
 * 发表意见
 * @author hasee
 *
 */
@WebServlet("/AdviceServlet")
public class AdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdviceService adviceService = new AdviceService();
       
    public AdviceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("advice")) {
			String advices = request.getParameter("advice");
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			//反馈时间
			Date time = new Date();
			try {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date currentTime  = new Date();
				String s = df.format(currentTime);
				time = df.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			Advice advice = new Advice();
			advice.setUserName(userName);
			advice.setAdvice(advices);
			advice.setTime(time);
			boolean bool = adviceService.addAdvice(advice);
			if(bool) {
				response.sendRedirect("updateSuccess.jsp");
			}else {
				response.sendRedirect("updatefailure.jsp");
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
