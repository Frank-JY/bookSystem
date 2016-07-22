package com.yuanjunye.www.dao;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Param1;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.MybatisUtil;

public class UserDao {
	
	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 显示等待申请的学生
	 * @return
	 */
	public List<User> showApplyStudentDao() {
		List<User> studentList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			studentList = session.getMapper(IUserDao.class).showApplyStudentDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return studentList;
	}
	
	/**
	 * 显示等待申请的管理员
	 * @return
	 */
	public List<User> showApplyManagerDao() {
		List<User> managerList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			managerList = session.getMapper(IUserDao.class).showApplyManagerDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return managerList;
	}
	
	/**
	 * 查询学生头像路径
	 * @param userName
	 * @return
	 */
	public String findStudentPhotoDao(String userName) {
		String photo = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			photo = session.getMapper(IUserDao.class).findStudentPhotoDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return photo;
	}
	
	/**
	 * 查询管理员头像路径
	 * @param userName
	 * @return
	 */
	public String findManagerPhotoDao(String userName) {
		String photo = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			photo = session.getMapper(IUserDao.class).findManagerPhotoDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return photo;
	}
	
	/**
	 * 同意用户注册申请
	 * @param userName
	 * @return
	 */
	public int passUserDao(String userName) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).passUserDao(userName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 不同意用户注册申请
	 * @param userName
	 * @return
	 */
	public int rejectUserDao(String userName) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).rejectUserDao(userName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 学生用户个人信息
	 * @param userName
	 * @return
	 */
	public User myStudentDao(String userName) {
		User user = new User();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			user = session.getMapper(IUserDao.class).myStudentDao(userName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return user;	
	}
	
	/**
	 * 管理员用户个人信息
	 * @param userName
	 * @return
	 */
	public User myManagerDao(String userName) {
		User user = new User();
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			user = session.getMapper(IUserDao.class).myManagerDao(userName);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return user;	
	}
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public int updateStudentDao(Student student) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).updateStudentDao(student);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 更新管理员信息
	 * @param student
	 * @return
	 */
	public int updateManagerDao(Manager manager) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).updateManagerDao(manager);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}

	/**
	 * 更改学生用户头像
	 * @param student
	 * @return
	 */
	public int updateStudentPhotoDao(Student student) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).updateStudentPhotoDao(student);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 更改管理员用户头像
	 * @param student
	 * @return
	 */
	public int updateManagerPhotoDao(Manager manager) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).updateManagerPhotoDao(manager);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 *修改用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordDao(User user) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			t = session.getMapper(IUserDao.class).updatePasswordDao(user);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return t;
	}
	
	/**
	 * 显示通过申请的学生
	 * @return
	 */
	public List<User> showAllStudentDao() {
		List<User> studentList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			studentList = session.getMapper(IUserDao.class).showAllStudentDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return studentList;
	}
	
	/**
	 * 显示通过申请的管理员
	 * @return
	 */
	public List<User> showAllManagerDao() {
		List<User> managerList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			managerList = session.getMapper(IUserDao.class).showAllManagerDao();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return managerList;
	}
	
	/**
	 * 查询用户身份
	 * @param userName
	 * @return
	 */
	public String findIdentiyDao(String userName) {
		String identiy = "";
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			identiy = session.getMapper(IUserDao.class).findIdentiyDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return identiy;
	}
	
	/**
	 * 模糊查询学生
	 * @param keyword
	 * @return
	 */
	public List<User> searchStudentDao(String keyword) {
		List<User> studentList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			studentList = session.getMapper(IUserDao.class).searchStudentDao(keyword);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return studentList;
	}
	
	/**
	 * 模糊查询管理员
	 * @param keyword
	 * @return
	 */
	public List<User> searchManagerDao(String keyword) {
		List<User> managerList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			managerList = session.getMapper(IUserDao.class).searchManagerDao(keyword);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return managerList;
	}
	
	/**
	 * 多条件查询学生
	 * @param userName
	 * @return
	 */
	public List<User> conditionStudentDao(Param1 param) {
		List<User> studentList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			studentList = session.getMapper(IUserDao.class).conditionStudentDao(param);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return studentList;
	}
	
	/**
	 * 多条件查询管理员
	 * @param keyword
	 * @return
	 */
	public List<User> conditionManagerDao(Param1 param) {
		List<User> managerList = new LinkedList<User>(); 
		try {
			factory = MybatisUtil.getFactory();
			SqlSession session = factory.openSession();
			managerList = session.getMapper(IUserDao.class).conditionManagerDao(param);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			MybatisUtil.close(session);
		}	
		return managerList;
	}
	
	
	
	
}
