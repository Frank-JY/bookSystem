package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.BookType;
import com.yuanjunye.www.service.BookTypeService;

/**
 * 管理图书预处理
 * @author hasee
 *
 */
@WebServlet("/ManageBookTypeServlet")
public class ManageBookTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BookTypeService bookTypeService = new BookTypeService();
	
    public ManageBookTypeServlet() {
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
		if(null != request.getParameter("tag1")) {
			String tag1 = request.getParameter("tag1");
			session.setAttribute("tag1", tag1);
		}
		List<BookType> bookTypeList = bookTypeService.showBookType();
		session.setAttribute("bookTypeList", bookTypeList);
		response.sendRedirect("manageBookType.jsp");
		
	}

}
