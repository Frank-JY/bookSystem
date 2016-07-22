package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.service.AdviceService;

/**
 * 删除建议
 * @author hasee
 *
 */
@WebServlet("/DeleteAdviceServlet")
public class DeleteAdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdviceService adviceService = new AdviceService();

    public DeleteAdviceServlet() {
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
		if(null != request.getParameter("id")) {
			int id = Integer.valueOf(request.getParameter("id"));
			boolean bool = adviceService.deleteAdvice(id);
			if(bool) {
				response.sendRedirect("Advice1Servlet");
			}else {
				response.sendRedirect("updatefailure.jsp");
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
