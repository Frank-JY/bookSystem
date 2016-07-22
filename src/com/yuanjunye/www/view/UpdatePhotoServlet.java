package com.yuanjunye.www.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.service.BookService;

/**
 * 更新图书
 * @author hasee
 *
 */
@WebServlet("/UpdatePhotoServlet")
public class UpdatePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService();
	
    public UpdatePhotoServlet() {
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
		String savePath = this.getServletContext().getRealPath("/image/upload");
		String tempPath = this.getServletContext().getRealPath("/image/temp");
		File tmpFile = new File(tempPath);
		File saFile = new File(savePath);
		if (!tmpFile.exists()) {
		tmpFile.mkdir();
		}
		if (!saFile.exists()) {
			saFile.mkdir();
		}
		Books book = new Books();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			factory.setSizeThreshold(1024*1024);
			factory.setRepository(tmpFile);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			upload.setFileSizeMax(1024*1024);
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					if(name.equals("bookId")) {
						book.setBookId(value);
					}
				}else{
					String filename = item.getName();
					if(filename==null || filename.trim().equals("")){
						book.setPhoto("math.jpg");
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
					//后缀格式
					if(!(fileExtName.equalsIgnoreCase("jpg") || fileExtName.equalsIgnoreCase("png")
							|| fileExtName.equalsIgnoreCase("jpeg") || fileExtName.equalsIgnoreCase("gif"))) {
						request.setAttribute("error2", fileExtName+"格式不符合要求");
						request.getRequestDispatcher("updatePhoto.jsp").forward(request, response);
						return;
					}
					InputStream in = item.getInputStream();
					String saveFilename = makeFileName(filename);
					FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFilename);
					book.setPhoto(saveFilename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while((len=in.read(buffer))>0){
					out.write(buffer, 0, len);
					}
					in.close();
					out.close();
				}
			}
		}catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("error2", "单个文件超出最大值！！！");
			request.getRequestDispatcher("updatePhoto.jsp").forward(request, response);
			return;
		}catch (FileUploadException e1) {
			e1.printStackTrace();
			request.setAttribute("error2", "上传失败");
			request.getRequestDispatcher("updatePhoto.jsp").forward(request, response);
			return;
		}
		boolean bool = bookService.updatePhoto(book);
		if(bool) {
			response.sendRedirect("updateSuccess.jsp");
		}else {
			response.sendRedirect("updateFailure.jsp");
		}
	}
	
	private String makeFileName(String filename){  
		return UUID.randomUUID().toString() + "0000" + filename;
	}
	
}
