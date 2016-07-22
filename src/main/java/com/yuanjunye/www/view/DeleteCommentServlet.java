package com.yuanjunye.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuanjunye.www.service.CommentService;

/**
 * 管理员删除评论
 * @author hasee
 *
 */
@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommentService commentService = new CommentService();

    public DeleteCommentServlet() {
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
			String tag = request.getParameter("tag");
			String bookId = request.getParameter("bookId");
			int id = Integer.valueOf(request.getParameter("id"));
			boolean bool = commentService.deleteComment(id);
			if(bool) {
				response.sendRedirect("BookDetailsServleet?bookId="+bookId+"&tag="+tag);
			}else {
				response.sendRedirect("updatefailure.jsp");
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	}

}
