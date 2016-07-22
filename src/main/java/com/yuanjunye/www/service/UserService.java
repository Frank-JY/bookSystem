package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.dao.UserDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Param1;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	/**
	 * 显示等待申请的学生
	 * @return
	 */
	public List<User> showApplyStudent() {
		return userDao.showApplyStudentDao();
	}

	/**
	 * 显示等待申请的管理员
	 * @return
	 */
	public List<User> showApplyManager() {
		return userDao.showApplyManagerDao();
	}
	
	/**
	 * 同意申请
	 * @param userName
	 * @return
	 */
	public boolean passUser(String userName) {
		boolean bool = true;
		int t = userDao.passUserDao(userName);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 不同意申请
	 * @param userName
	 * @return
	 */
	public boolean rejectUser(String userName) {
		boolean bool = true;
		int t = userDao.rejectUserDao(userName);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 显示通过申请的学生
	 * @return
	 */
	public List<User> showAllStudent() {
		return userDao.showAllStudentDao();
	} 
	
	/**
	 * 显示通过申请的管理员
	 * @return
	 */
	public List<User> showAllManager() {
		return userDao.showAllManagerDao();
	}
	
	/**
	 * 学生用户个人信息
	 * @return
	 */
	public User myStudent(String userName) {
		return userDao.myStudentDao(userName);
	}
	
	/**
	 * 管理员用户个人信息
	 * @return
	 */
	public User myManager(String userName) {
		return userDao.myManagerDao(userName);
	}
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public boolean updateStudent(Student student) {
		boolean bool = true;
		int t = userDao.updateStudentDao(student);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 更新管理员信息
	 * @param student
	 * @return
	 */
	public boolean updateManager(Manager manager) {
		boolean bool = true;
		int t = userDao.updateManagerDao(manager);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public boolean updatePassword(User user) {
		boolean bool = true;
		int t = userDao.updatePasswordDao(user);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询用户身份
	 * @param userName
	 * @return
	 */
	public String findIdentiy(String userName) {
		return userDao.findIdentiyDao(userName);
	}
	
	/**
	 * 模糊查询学生
	 * @param userName
	 * @return
	 */
	public List<User> searchStudent(String keyword) {
		return userDao.searchStudentDao(keyword);
	}
	
	/**
	 * 模糊查询管理员
	 * @return
	 */
	public List<User> searchManager(String keyword) {
		return userDao.searchManagerDao(keyword);
	}
	
	/**
	 * 更改学生用户头像
	 * @param student
	 * @return
	 */
	public boolean updateStudentPhoto(Student student) {
		boolean bool = true;
		int t = userDao.updateStudentPhotoDao(student);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 更改管理员用户头像
	 * @param student
	 * @return
	 */
	public boolean updateManagerPhoto(Manager manager) {
		boolean bool = true;
		int t = userDao.updateManagerPhotoDao(manager);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询学生头像路径
	 * @param userName
	 * @return
	 */
	public String findStudentPhoto(String userName) {
		return userDao.findStudentPhotoDao(userName);
	}
	
	/**
	 * 查询管理员头像路径
	 * @param userName
	 * @return
	 */
	public String findManagerPhoto(String userName) {
		return userDao.findManagerPhotoDao(userName);
	}
	
	/**
	 * 多条件查询学生
	 * @param userName
	 * @return
	 */
	public List<User> conditionStudent(Param1 param) {
		return userDao.conditionStudentDao(param);
	}
	
	/**
	 * 多条件查询管理员
	 * @param keyword
	 * @return
	 */
	public List<User> conditionManager(Param1 param) {
		return userDao.conditionManagerDao(param);
	}
	
}
