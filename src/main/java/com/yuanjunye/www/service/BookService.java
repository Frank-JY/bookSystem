package com.yuanjunye.www.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yuanjunye.www.dao.IBookDao;
import com.yuanjunye.www.po.Books;
import com.yuanjunye.www.po.Param2;

@Service
public class BookService implements IBookService{
	
	@Resource
	private IBookDao bookDao; 

	/**
	 * 上架图书
	 * @param book
	 * @return
	 */
	public boolean addBook(Books book) {
		boolean bool = true;
		if(book.getTotal() > 0) {
			book.setStatus("可借阅");
		}else {
			book.setStatus("无库存");
		}
		int t = bookDao.addBookDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 判断图书编号是否存在
	 * @param bookId
	 * @return
	 */
	public boolean judegBookId(String bookId) {
		boolean bool = true;
		int t = bookDao.judgeBookIdDao(bookId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 显示所有图书信息
	 * @return
	 */
	public List<Books> showAllBooks() {
		return bookDao.showAllBooksDao();
	}
	
	/**
	 * 显示所有图书信息（无顺序）
	 * @return
	 */
	public Set<Books> showAllBooks1() {
		return bookDao.showAllBooks1Dao();
	}
	
	/**
	 * 删除图书
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(String bookId) {
		boolean bool = true;
		Books book = bookDao.checkAmountTotalDao(bookId);
		int amount = book.getAmount();
		int total = book.getTotal();
		if(total != amount) {
			bool = false;
		}else {
			int t = bookDao.deleteBookDao(bookId);
			if(t == 0) {
				bool = false;
			}
		}
		return bool;
	}
	
	/**
	 * 改变图书状态（上架）
	 * @param bookId
	 * @return
	 */
	public boolean inBook(String bookId) {
		boolean bool = true;
		String status = "";
		int amount = bookDao.findAmountDao(bookId);
		if(amount == 0) {
			status = "无库存";
		}else {
			status = "可借阅";
		}
		Books book = new Books();
		book.setBookId(bookId);
		book.setStatus(status);
		int t = bookDao.inBookDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 改变图书状态（下架）
	 * @param bookId
	 * @return
	 */
	public boolean outBook(String bookId) {
		boolean bool = true;
		int t = bookDao.outBookDao(bookId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books showBook(String bookId) {
		return bookDao.showBookDao(bookId);
	}
	
	
	/**
	 * 显示一本图书信息
	 * @return
	 */
	public Books findBook(String bookId) {
		return bookDao.findBookDao(bookId);
	}
	
	/**
	 * 修改图书信息
	 * @param book
	 * @return
	 */
	public boolean updateBook(Books book) {
		boolean bool = true;
		int t = bookDao.updateBookDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 更换图书封面
	 * @param book
	 * @return
	 */
	public boolean updatePhoto(Books book) {
		boolean bool = true;
		int t = bookDao.updatePhotoDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询图书状态
	 * @param bookId
	 * @return
	 */
	public String findStatus(String bookId) {
		return bookDao.findStatusDao(bookId);
	}
	
	/**
	 * 查询图书库存
	 * @param bookId
	 * @return
	 */
	public int findAmount(String bookId) {
		return bookDao.findAmountDao(bookId);
	}
	
	
	/**
	 * 申请借阅图书，改变库存
	 * @param bookId
	 * @param amount
	 * @return
	 */
	public boolean updateAmount(Books book) {
		boolean bool = true;
		int t = bookDao.updateAmountDao(book);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	
	/**
	 * 模糊查询图书信息
	 * @return
	 */
	public List<Books> searchBooks(String keyword) {
		return bookDao.searchBooksDao(keyword);
	}

	/**
	 * 多条件查询书籍
	 * @param sql
	 * @return
	 */
	public List<Books> searchBooks1(Param2 param) {
		return bookDao.searchBooks1Dao(param);
	}

	/**
	 * 根据图书类型模糊查询图书信息
	 * @param keyword
	 * @return
	 */
	public List<Books> searchBooks2(String typeName) {
		return bookDao.searchBooks2Dao(typeName);
	}
	
	/**
	 * 每日新书
	 * @param time
	 * @return
	 */
	public List<Books> newBook() {
		List<Books> newBookList = null;
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime  = new Date();
			Date time = new Date();
			String s = df.format(currentTime);
			time = df.parse(s);
			newBookList = bookDao.newBookDao(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newBookList;
	}
	
	/**
	 * 热门图书推介
	 * @return
	 */
	public List<Books> hotBook() {
		return bookDao.hotBookDao();
	}

	/**
	 * 修改图书逻辑
	 */
	@Override
	public Books judge(Books book ,int oldTotal) {
		int amount = book.getAmount();
		int total = book.getTotal();
		amount = (total - oldTotal) + amount;
		String status = book.getStatus();
		if(!status.equals("已下架")) {
			if(amount == 0) {
				status = "无库存";
			}else {
				status = "可借阅";
			}
		}
		book.setAmount(amount);
		book.setStatus(status);
		return book;
	}
}
