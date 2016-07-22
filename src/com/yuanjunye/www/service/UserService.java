package com.yuanjunye.www.service;

import java.util.Map;

import com.yuanjunye.www.dao.UserDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	/**
	 * 显示等待申请的学生
	 * @return
	 */
	public Map<Student,User> showApplyStudent() {
		return userDao.showApplyStudentDao();
	}

	/**
	 * 显示等待申请的管理员
	 * @return
	 */
	public Map<Manager,User> showApplyManager() {
		return userDao.showApplyManagerDao();
	}
	
	/**
	 * 同意申请
	 * @param userName
	 * @return
	 */
	public boolean passUser(String userName) {
		return userDao.passUserDao(userName);
	}
	
	/**
	 * 不同意申请
	 * @param userName
	 * @return
	 */
	public boolean rejectUser(String userName) {
		return userDao.rejectUserDao(userName);
	}
	
	/**
	 * 显示通过申请的学生
	 * @return
	 */
	public Map<Student,User> showAllStudent() {
		return userDao.showAllStudentDao();
	} 
	
	/**
	 * 显示通过申请的管理员
	 * @return
	 */
	public Map<Manager,User> showAllManager() {
		return userDao.showAllManagerDao();
	}
	
	/**
	 * 学生用户个人信息
	 * @return
	 */
	public Map<Student,User> myStudent(String userName) {
		return userDao.myStudentDao(userName);
	}
	
	/**
	 * 管理员用户个人信息
	 * @return
	 */
	public Map<Manager,User> myManager(String userName) {
		return userDao.myManagerDao(userName);
	}
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public boolean updateStudent(Student student) {
		return userDao.updateStudentDao(student);
	}
	
	/**
	 * 更新管理员信息
	 * @param student
	 * @return
	 */
	public boolean updateManager(Manager manager) {
		return userDao.updateManagerDao(manager);
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public boolean updatePassword(User user) {
		return userDao.updatePasswordDao(user);
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
	public Map<Student,User> searchStudent(String keyword) {
		return userDao.searchStudentDao(keyword);
	}
	
	/**
	 * 模糊查询管理员
	 * @return
	 */
	public Map<Manager,User> searchManager(String keyword) {
		return userDao.searchManagerDao(keyword);
	}
	
	/**
	 * 更改学生用户头像
	 * @param student
	 * @return
	 */
	public boolean updateStudentPhoto(Student student) {
		return userDao.updateStudentPhotoDao(student);
	}
	
	/**
	 * 更改管理员用户头像
	 * @param student
	 * @return
	 */
	public boolean updateManagerPhoto(Manager manager) {
		return userDao.updateManagerPhotoDao(manager);
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
	public Map<Student,User> conditionStudent(String sql) {
		return userDao.conditionStudentDao(sql);
	}
	
	/**
	 * 多条件查询管理员
	 * @param keyword
	 * @return
	 */
	public Map<Manager,User> conditionManager(String sql) {
		return userDao.conditionManagerDao(sql);
	}
	
}
