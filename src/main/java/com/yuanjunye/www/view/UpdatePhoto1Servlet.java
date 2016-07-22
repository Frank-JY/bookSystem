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

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.service.UserService;

/**
 * 更新头像
 * @author hasee
 *
 */
@WebServlet("/UpdatePhoto1Servlet")
public class UpdatePhoto1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService = new UserService();
	
    public UpdatePhoto1Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String identity = (String)session.getAttribute("identity");
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
		Student student = new Student();
		Manager manager = new Manager();
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
					if(identity.equals("学生")) {
						if(name.equals("userId")) {
							student.setStudentId(Long.valueOf(value));
						}
					}else {
						if(name.equals("userId")) {
							manager.setManagerId(Integer.valueOf(value));
						}
					}
				}else{
					String filename = item.getName();
					if(filename==null || filename.trim().equals("")){
						if(identity.equals("学生")) {
							student.setPhoto("dog.jpg");
						}else {
							manager.setPhoto("dog.jpg");
						}	
						continue;
					}
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
					//后缀格式
					if(!(fileExtName.equalsIgnoreCase("jpg") || fileExtName.equalsIgnoreCase("png")
							|| fileExtName.equalsIgnoreCase("jpeg") || fileExtName.equalsIgnoreCase("gif"))) {
						request.setAttribute("error2", fileExtName+"格式不符合要求");
						request.getRequestDispatcher("updatePhoto1.jsp").forward(request, response);
						return;
					}
					InputStream in = item.getInputStream();
					String saveFilename = makeFileName(filename);
					FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFilename);
					if(identity.equals("学生")) {
						student.setPhoto(saveFilename);
					}else {
						manager.setPhoto(saveFilename);
					}	
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
			request.getRequestDispatcher("updatePhoto1.jsp").forward(request, response);
			return;
		}catch (FileUploadException e1) {
			e1.printStackTrace();
			request.setAttribute("error2", "上传失败");
			request.getRequestDispatcher("updatePhoto1.jsp").forward(request, response);
			return;
		}
		boolean bool = false;
		if(identity.equals("学生")) {
			bool = userService.updateStudentPhoto(student);
		}else {
			bool = userService.updateManagerPhoto(manager);
		}
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
