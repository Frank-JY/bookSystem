package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.BookTypeService;
import com.yuanjunye.www.service.UserService;

/**
 * 登录预处理
 * @author hasee
 *
 */
@WebServlet("/Login1Servlet")
public class Login1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService = new BookService(); 
	private BookTypeService bookTypeService = new BookTypeService();   
	private UserService userService = new UserService();
	
    public Login1Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<Books> booksSet = bookService.showAllBooks1();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String identity = (String)session.getAttribute("identity");
		String photo = "";
		if(identity.equals("学生")) {
			photo = userService.findStudentPhoto(userName);
		}else {
			photo = userService.findManagerPhoto(userName);
		}
		Set<String> typeNameSet = bookTypeService.allTypeName();
		session.setAttribute("typeNameSet", typeNameSet);
		session.setAttribute("booksSet", booksSet);
		session.setAttribute("photo", photo);
		response.sendRedirect("main.jsp");
	}

}
