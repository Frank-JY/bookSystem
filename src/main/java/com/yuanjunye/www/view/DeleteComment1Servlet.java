package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.service.CommentService;

/**
 * 用户删除个人评论
 * @author hasee
 *
 */
@WebServlet("/DeleteComment1Servlet")
public class DeleteComment1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentService commentService = new CommentService();

    public DeleteComment1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(null != request.getParameter("id")) {
			int id = Integer.valueOf(request.getParameter("id"));
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			Comment comment = new Comment();
			comment.setId(id);
			comment.setUserName(userName);
			boolean bool = commentService.deleteComment1(comment);
			if(bool) {
				response.sendRedirect("Comment1Servlet");
				return;
			}else {
				response.sendRedirect("updatefailure.jsp");
				return;
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
