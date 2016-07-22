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

import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.service.CommentService;

/**
 * 个人评论
 * @author hasee
 *
 */
@WebServlet("/Comment1Servlet")
public class Comment1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentService commentService = new CommentService();
	
    public Comment1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		List<Comment> myCommentList = commentService.myComment(userName);
		Collections.reverse(myCommentList);
		session.setAttribute("myCommentList", myCommentList);
		response.sendRedirect("myComment.jsp");
	}

}
