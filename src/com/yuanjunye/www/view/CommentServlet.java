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

import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.service.CommentService;

/**
 * 添加评论
 * @author hasee
 *
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService commentService = new CommentService();
       
    public CommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		if(null != request.getParameter("comments")) {
			Comment comment = new Comment();
			String tag = request.getParameter("tag");
			String bookId = request.getParameter("bookId");
			String comments = request.getParameter("comments");
			//评论时间
			Date time = new Date();
			try {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date currentTime  = new Date();
				String s = df.format(currentTime);
				time = df.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			comment.setTime(time);
			comment.setBookId(bookId);
			comment.setUserName(userName);
			comment.setComments(comments);
			boolean bool = commentService.publish(comment);
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
