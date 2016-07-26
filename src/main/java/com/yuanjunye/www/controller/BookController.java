package com.yuanjunye.www.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yuanjunye.www.po.BookType;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Comment;
import com.yuanjunye.www.po.Param2;
import com.yuanjunye.www.service.IBookService;
import com.yuanjunye.www.service.IBookTypeService;
import com.yuanjunye.www.service.ICommentService;
import com.yuanjunye.www.service.IUserService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Resource
	private IBookTypeService bookTypeService;
	@Resource
	private IBookService bookService;
	@Resource
	private ICommentService commentService;
	@Resource
	private IUserService userService;
	
	/**
	 * 管理图书类型
	 * @param request
	 * @param tag1
	 * @return
	 */
	@RequestMapping(value="/manageBookType")
	public ModelAndView manageBookType(HttpServletRequest request, String tag1) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(null != tag1) {
			session.setAttribute("tag1", tag1);
		}
		List<BookType> bookTypeList = bookTypeService.showBookType();
		session.setAttribute("bookTypeList", bookTypeList);
		mav.setViewName("book/manageBookType");
		return mav;
	}
	
	/**
	 * 管理图书类型（操作）
	 * @param request
	 * @param action
	 * @param typeName
	 * @param typeId
	 * @return
	 */
	@RequestMapping(value="/manageBookType1")
	public ModelAndView manageBookType1(HttpServletRequest request, String action, String typeName, Integer typeId) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		boolean bool = false;
		if("add".equals(action)){
			boolean bool1 = bookTypeService.judgeTypeName(typeName);
			if(!bool1) {
				bool = bookTypeService.addBookType(typeName);
			}else {
				request.setAttribute("error", "图书类型已存在");
				mav.setViewName("book/manageBookType");
				return mav;
			}
		}
		if("update".equals(action)){
			String typeName1 = bookTypeService.findTypeName(typeId);
			BookType bookType = new BookType();
			bookType.setTypeId(typeId);
			bookType.setTypeName(typeName1);
			mav.addObject("bookType", bookType);
			mav.setViewName("book/updateBookType");
			return mav;
		}
		if("update1".equals(action)){
			BookType bookType = new BookType();
			bookType.setTypeId(typeId);
			bookType.setTypeName(typeName);
			request.setAttribute("bookType", bookType);
			bool = bookTypeService.updateBookType(bookType);
		}
		if("delete".equals(action)){
			bool = bookTypeService.deleteBookType(typeId);
		}
		if(bool) { 
			mav.setViewName("redirect:/book/manageBookType.do");
		}else {
			mav.setViewName("user/updatefailure");
		}
		
		return mav;
	}
	
	/**
	 * 跳转上架图书页面
	 * @param request
	 * @param tag1
	 * @return
	 */
	@RequestMapping(value="/addBook1")
	public ModelAndView addBook1(HttpServletRequest request, String tag1) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		List<BookType> bookTypeList = bookTypeService.showBookType();
		session.setAttribute("bookTypeList", bookTypeList);
		mav.setViewName("book/addBook");
		return mav;
	}
	
	/**
	 * 上架图书（含图片上传）
	 * @param request
	 * @param file
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/addBook2")
	public ModelAndView addBook2(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file
			, Books book) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		boolean judge = false;
		String savePath = request.getSession().getServletContext().getRealPath("/image/upload");
		String tempPath = request.getSession().getServletContext().getRealPath("/image/temp");
		File tmpFile = new File(tempPath); 
		File saFile = new File(savePath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		if (!saFile.exists()) {
			saFile.mkdir();
		}
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime  = new Date();
			Date time = new Date();
			String s = df.format(currentTime);
			time = df.parse(s);
			book.setTime(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}								//判断并设值
		boolean bool = bookService.judegBookId(book.getBookId());
		if(bool) {
			mav.addObject("error1", "图书编号已存在");
			judge = false;
		}else {
			judge = true;
		
		}
		
		String fileName = file.getOriginalFilename();
		if(!"".equals(fileName)) {
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			String saveFilename = makeFileName(fileName);
			
			String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
			//后缀格式
			if(!(fileExtName.equalsIgnoreCase("jpg") || fileExtName.equalsIgnoreCase("png")
					|| fileExtName.equalsIgnoreCase("jpeg") || fileExtName.equalsIgnoreCase("gif"))) {
				mav.addObject("error2", fileExtName+"格式不符合要求");
				judge = false;
			}
			try {  
	            file.transferTo(new File(savePath + "\\" + saveFilename));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
			book.setPhoto(saveFilename);
		}else {
			book.setPhoto("math.jpg");
		}
		boolean bool1 = false;
		if(judge) {
			bool1 = bookService.addBook(book);
		}else {
			mav.addObject("book", book);
			mav.setViewName("book/addBook");
			return mav;
		}
		if(bool1) {
			mav.setViewName("user/updateSuccess");
		}else {
			mav.setViewName("user/updatefailure");
		}
		return mav;
	}
	
	/**
	 * 显示所有图书
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showAllBooks")
	public ModelAndView showAllBooks(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		List<Books> booksList = bookService.showAllBooks();
		session.setAttribute("booksList", booksList);
		mav.setViewName("book/allBooks");
		return mav;
	}
	
	/**
	 * 管理图书（操作）
	 * @param request
	 * @param bookId
	 * @param action
	 * @return
	 */
	@RequestMapping(value="/manageBook")
	public ModelAndView manageBook(HttpServletRequest request, String bookId, String action) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(action.equals("delete")) {
			boolean bool = bookService.deleteBook(bookId);
			if(bool) {
				mav.setViewName("user/updateSuccess");
			}else { 
				request.setAttribute("error", "操作失败：该图书还没有全部归还，不可删除。可以先下架图书等待全部归还后删除该图书信息");
				mav.setViewName("forward:/showAllBooks.do");
				return mav;
			}
		}
		if(action.equals("in")) {
			boolean bool = bookService.inBook(bookId);
			if(bool) {
				mav.setViewName("forward:/book/showAllBooks.do");
			}
		}
		if(action.equals("out")) {
			boolean bool = bookService.outBook(bookId);
			if(bool) {
				mav.setViewName("forward:/book/showAllBooks.do");
			}
		}
		if(action.equals("update")) {
			Books book = bookService.showBook(bookId);
			session.setAttribute("book", book);
			List<BookType> bookTypeList = bookTypeService.showBookType();
			session.setAttribute("bookTypeList", bookTypeList);
			mav.setViewName("book/updateBook");
		}
		return mav;
	}
	
	/**
	 * 跳转到更新图书封面页面
	 * @return
	 */
	@RequestMapping("/toUpdatePhoto") 
	public String toUpdatePhoto() {
		return "book/updatePhoto";
	}
	
	/**
	 * 修改图书
	 * @param request
	 * @param book
	 * @param oldTotal
	 * @return
	 */
	@RequestMapping(value="/updateBook")
	public ModelAndView updateBook(HttpServletRequest request, Books book, int oldTotal) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}			
		if(null != book) {
			Books newBook = bookService.judge(book, oldTotal); 
			int amount = book.getAmount();
			if(amount < 0) {
				request.setAttribute("error", "图书总数量不应少于已借出图书的数量");
				mav.setViewName("book/updateBook");
				return mav;
			}
			boolean bool = bookService.updateBook(newBook);
			if(bool) {
				mav.setViewName("user/updateSuccess");
			}else {
				mav.setViewName("user/updatefailure");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 更新图书封面
	 * @param request
	 * @param file
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/updatePhoto")
	public ModelAndView updatePhoto(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file
			, Books book) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		String savePath = request.getSession().getServletContext().getRealPath("/image/upload");
		String tempPath = request.getSession().getServletContext().getRealPath("/image/temp");
		File tmpFile = new File(tempPath); 
		File saFile = new File(savePath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		if (!saFile.exists()) {
			saFile.mkdir();
		}	
		String fileName = file.getOriginalFilename();
		if(!"".equals(fileName)) {
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			String saveFilename = makeFileName(fileName);
			
			String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
			//后缀格式
			if(!(fileExtName.equalsIgnoreCase("jpg") || fileExtName.equalsIgnoreCase("png")
					|| fileExtName.equalsIgnoreCase("jpeg") || fileExtName.equalsIgnoreCase("gif"))) {
				mav.addObject("error2", fileExtName+"格式不符合要求");
				mav.setViewName("book/updatePhoto");
				return mav;
			}
			try {  
	            file.transferTo(new File(savePath + "\\" + saveFilename));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
			book.setPhoto(saveFilename);
		}else {
			book.setPhoto("math.jpg");
		}
		boolean bool = bookService.updatePhoto(book);
		if(bool) {
			mav.setViewName("user/updateSuccess");
		}else {
			mav.setViewName("user/updatefailure");
		}
		return mav;
	}
	
	/**
	 * 模糊查询图书信息
	 * @param request
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/showAllBooks1")
	public ModelAndView showAllBooks1(HttpServletRequest request, String keyword) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		List<Books> booksList = bookService.searchBooks(keyword);
		mav.addObject("keyword1", keyword);
		session.setAttribute("booksList", booksList);
		mav.setViewName("book/allBooks");
		return mav;
	}
	
	/**
	 * 跳转到多条件查询页面
	 * @return
	 */
	@RequestMapping(value="/toCondition1")
	public ModelAndView toCondition1() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("book/condition1");
		return mav;
	}
	
	/**
	 * 多条件查询书籍
	 * @param request
	 * @param value
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/condition1")
	public ModelAndView condition1(HttpServletRequest request, String value, Param2 param) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("identity").equals("学生")) {
			mav.setViewName("power");
			return mav;
		}	
		if(null != value) {
			List<Books> booksList = bookService.searchBooks1(param);
			if(value.equals("1")) {
				session.setAttribute("searchBooksList", booksList);
				mav.setViewName("book/search");
			}else if(value.equals("2")) {
				session.setAttribute("booksList", booksList);
				mav.setViewName("book/allBooks");
			}else {
				mav.setViewName("ban");
			}
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 模糊查询图书信息
	 * @param request
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/search")
	public ModelAndView search(HttpServletRequest request, String keyword) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();	
		if(null != keyword) {
			List<Books> searchBooksList = bookService.searchBooks(keyword);
			session.setAttribute("searchBooksList", searchBooksList);
			mav.addObject("keyword1", keyword);
			session.setAttribute("searchBooksList", searchBooksList);
			mav.setViewName("book/search");
		}else {
			mav.setViewName("book/search");
		}
		return mav;
	}
	
	/**
	 * 根据图书类型查询图书信息
	 * @param request
	 * @param typeName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/typeSearch")
	public ModelAndView typeSearch(HttpServletRequest request, String typeName) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();	
		if(null != typeName) {
			typeName = new String(typeName.getBytes("ISO-8859-1"), "UTF-8");  
			List<Books> searchBooksList = bookService.searchBooks2(typeName);
			session.setAttribute("searchBooksList", searchBooksList);
			mav.setViewName("book/search");
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	/**
	 * 图书细节
	 * @param request
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value="/bookDetails")
	public ModelAndView bookDetails(HttpServletRequest request, String bookId,String tag){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();	
		if(null != bookId) {
			Books aBook = bookService.showBook(bookId);
			List<Comment> commentList = commentService.bookComment(bookId);
			session.setAttribute("commentList", commentList);
			session.setAttribute("aBook", aBook);
			session.setAttribute("tag", tag);
			mav.setViewName("book/bookDetails");
		}else {
			mav.setViewName("ban");
		}
		return mav;
	}
	
	
	/**
	 * 每日新书
	 * @param request
	 * @param typeName
	 * @return
	 */
	@RequestMapping(value="/newBook")
	public ModelAndView newBook(HttpServletRequest request, String typeName) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		List<Books> newBookList = bookService.newBook();
		session.setAttribute("newBookList", newBookList);
		mav.setViewName("book/searchNewBook");
		return mav;
	}
	
	/**
	 * 热门图书
	 * @param request
	 * @param typeName
	 * @return
	 */
	@RequestMapping(value="/hotBook")
	public ModelAndView hotBook(HttpServletRequest request, String typeName) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		List<Books> hotBookList = bookService.hotBook();
		session.setAttribute("hotBookList", hotBookList);
		mav.setViewName("book/searchHotBook");	
		return mav;
	}
	
	
	/**
	 * 跳转到查询学生借阅信息页面
	 * @return
	 */
	@RequestMapping("/toFindStudentBorrow") 
	public String toFindStudentBorrow() {
		return "borrow/findStudentBorrow";
	}
	
	/**
	 * 跳转到查询管理员借阅信息页面
	 * @return
	 */
	@RequestMapping("/toFindManagerBorrow") 
	public String toFindManagerBorrow() {
		return "borrow/findManagerBorrow";
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
