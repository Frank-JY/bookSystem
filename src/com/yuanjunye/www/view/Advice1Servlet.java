package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.service.AdviceService;

/**
 * 显示意见
 * @author hasee
 *
 */
@WebServlet("/Advice1Servlet")
public class Advice1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdviceService adviceService = new AdviceService();
	
    public Advice1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			response.sendRedirect("power.jsp");
			return;
		}
		List<Advice> adviceList = adviceService.ShowAdvice();
		Collections.reverse(adviceList);
		session.setAttribute("adviceList", adviceList);
		response.sendRedirect("showAdvice.jsp");
		return;
	}

}
