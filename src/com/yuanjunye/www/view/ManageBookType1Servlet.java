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
 * 管理图书类型操作
 * @author hasee
 *
 */
@WebServlet("/ManageBookType1Servlet")
public class ManageBookType1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	BookTypeService bookTypeService = new BookTypeService();
	
    public ManageBookType1Servlet() {
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
		boolean bool = false;
		if("update".equals(request.getParameter("action"))){
			int typeId = Integer.valueOf(request.getParameter("typeId"));
			String typeName = bookTypeService.findTypeName(typeId);
			BookType bookType = new BookType();
			bookType.setTypeId(typeId);
			bookType.setTypeName(typeName);
			request.setAttribute("bookType", bookType);
			request.getRequestDispatcher("updateBookType.jsp").forward(request, response);
			return;
		}
		if("update1".equals(request.getParameter("action"))){
			int typeId = Integer.valueOf(request.getParameter("typeId"));
			String typeName = request.getParameter("typeName");
			BookType bookType = new BookType();
			bookType.setTypeId(typeId);
			bookType.setTypeName(typeName);
			request.setAttribute("bookType", bookType);
			bool = bookTypeService.updateBookType(bookType);
			if(bool) {
				response.sendRedirect("ManageBookTypeServlet");
				return;
			}
		}
		if("delete".equals(request.getParameter("action"))){
			int typeId = Integer.valueOf(request.getParameter("typeId"));
			bool = bookTypeService.deleteBookType(typeId);
		}
		if("add".equals(request.getParameter("action"))){
			String typeName = request.getParameter("typeName");
			boolean bool1 = bookTypeService.judgeTypeName(typeName);
			if(!bool1) {
				bool = bookTypeService.addBookType(typeName);
			}else {
				request.setAttribute("error", "图书类型已存在");
				request.getRequestDispatcher("manageBookType.jsp").forward(request, response);
				return;
			}
		}
		if(bool) {
			List<BookType> bookTypeList = bookTypeService.showBookType();
			session.setAttribute("bookTypeList", bookTypeList);
			response.sendRedirect("manageBookType.jsp");
			return;
		}else{
			response.sendRedirect("updatefailure.jsp");
			return;
		}
	}

}
