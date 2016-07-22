package com.yuanjunye.www.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 上架图书处理数据，enctype="multipart/form-data"
 * @author hasee
 *
 */
@WebServlet("/AddBook2Servlet")
public class AddBook2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookService();
   
    public AddBook2Servlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean judge = false;
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			response.sendRedirect("power.jsp");
			return;
		}
		//获取上传文件的保存目录
		String savePath = this.getServletContext().getRealPath("/image/upload");
		//上传时生成的临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/image/temp");
		File tmpFile = new File(tempPath);
		File saFile = new File(savePath);
		//创建目录
		if (!tmpFile.exists()) {
		tmpFile.mkdir();
		}
		if (!saFile.exists()) {
			saFile.mkdir();
		}
		Books book = new Books();
		try {
			//创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置工厂的缓冲区的大小(生成临时文件）
			factory.setSizeThreshold(1024*1024);
			//设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			//创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			//单个文件大小最大值
			upload.setFileSizeMax(1024*1024);
			//上架时间
			try {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				Date currentTime  = new Date();
				Date time = new Date();
				String s = df.format(currentTime);
				time = df.parse(s);
				book.setTime(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			List<FileItem> list = upload.parseRequest(request);   //获取表单
			//遍历item
			for(FileItem item : list){							  //遍历
				if(item.isFormField()){							  //判断是否为普通项
					String name = item.getFieldName();			  //获得name
					String value = item.getString("UTF-8");		 //编码后的值
					if(name.equals("bookId")) {									//判断并设值
						boolean bool = bookService.judegBookId(value);
						if(bool) {
							book.setBookId(value);
							request.setAttribute("error1", "图书编号已存在");
							judge = false;
						}else {
							book.setBookId(value);
							judge = true;
						}
					}
					if(name.equals("bookName")) {
						book.setBookName(value);
					}
					if(name.equals("author")) {
						book.setAuthor(value);
					}
					if(name.equals("publishers")) {
						book.setPublishers(value);
					}
					if(name.equals("typeId")) {
						book.setTypeId(Integer.valueOf(value));
					}
					if(name.equals("total")) {
						book.setTotal(Integer.valueOf(value));
					}
					if(name.equals("remarks")) {
						book.setRemarks(value);
					}
				}else{
					String filename = item.getName();							//获得文件名
					if(filename==null || filename.trim().equals("")){			//文件名若不存在或为空，则设置默认图片
						book.setPhoto("math.jpg");
						continue;												//结束
					}
					filename = filename.substring(filename.lastIndexOf("\\")+1);				//取后缀文件名
					String fileExtName = filename.substring(filename.lastIndexOf(".")+1);		//去文件后缀
					//后缀格式
					if(!(fileExtName.equalsIgnoreCase("jpg") || fileExtName.equalsIgnoreCase("png")	//判断后缀类型
							|| fileExtName.equalsIgnoreCase("jpeg")|| fileExtName.equalsIgnoreCase("gif"))) {
						request.setAttribute("error2", fileExtName+"格式不符合要求");   //错误提示
						judge = false;
					}
					InputStream in = item.getInputStream();						//输入流
					String saveFilename = makeFileName(filename);				//随机赋予文件名
					FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFilename);		//二进制输出到文件
					book.setPhoto(saveFilename);								//Books类型设值
					byte buffer[] = new byte[1024];
					int len = 0;
					while((len=in.read(buffer))>0){								//输入的文件字节数
					out.write(buffer, 0, len);									//输出到文件
					}
					in.close();													//关闭管道
					out.close();
				}
			}
		}catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			request.setAttribute("error2", "单个文件超出最大值！！！"); 						//错误提示
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}catch (FileUploadException e1) {
			e1.printStackTrace();
			request.setAttribute("error2", "上传失败");									//错误提示
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}
		boolean bool = false;
		if(judge) {
			bool = bookService.addBook(book);
		}else {
			request.setAttribute("book", book);
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}
		if(bool) {
			response.sendRedirect("updateSuccess.jsp");
		}else {
			response.sendRedirect("updateFailure.jsp");
		}
		
	}
		
	/**
	 * 生成上传文件的文件名，文件名以：uuid+"0000"+文件的原始名称
	 * @param filename
	 * @return uuid+"0000"+文件的原始名称
	 */
	private String makeFileName(String filename){  
		//为防止文件覆盖的现象发生，为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "0000" + filename;
	}

}