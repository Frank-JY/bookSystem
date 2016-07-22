package com.yuanjunye.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.service.BookService;
import com.yuanjunye.www.service.CommentService;

/**
 * 图书细节
 * @author hasee
 *
 */
@WebServlet("/BookDetailsServleet")
public class BookDetailsServleet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService();
	private CommentService commentService = new CommentService();
	
    public BookDetailsServleet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(null != request.getParameter("bookId")) {
			String bookId = request.getParameter("bookId");
			Books aBook = bookService.showBook(bookId);
			List<Comment> commentList = commentService.bookComment(bookId);
			String tag = request.getParameter("tag");
			session.setAttribute("commentList", commentList);
			session.setAttribute("aBook", aBook);
			session.setAttribute("tag", tag);
			response.sendRedirect("bookDetails.jsp");
		}else {
			response.sendRedirect("ban.jsp");
		}
	}

}
