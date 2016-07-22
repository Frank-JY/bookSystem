package com.yuanjunye.www.service;

import com.yuanjunye.www.dao.RegisterDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;

public class RegisterService {
	
	private RegisterDao registerDao = new RegisterDao();
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		boolean bool = registerDao.addUserDao(user);
		return bool;
	}
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		boolean bool = registerDao.addStudentDao(student);
		return bool;
	}
	
	/**
	 * 添加管理员
	 * @param manager
	 * @return
	 */
	public boolean addManager(Manager manager) {
		boolean bool = registerDao.addManagerDao(manager);
		return bool;
	}
	
	/**
	 * 检查用户名
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName) {
		boolean bool = registerDao.checkUserNameDao(userName);
		return bool;
	}
	
	/**
	 * 检查学号
	 * @param studentId
	 * @return
	 */
	public boolean checkStudentId(long studentId) {
		boolean bool = registerDao.checkStudentIdDao(studentId);
		return bool;
	}
	
	/**
	 * 检查职工号
	 * @param managerId
	 * @return
	 */
	public boolean checkManagerId(int managerId) {
		boolean bool = registerDao.checkManagerIdDao(managerId);
		return bool;
	}
	
	/**
	 * 检查邮箱
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {
		boolean bool = registerDao.checkEmailDao(email);
		return bool;
	}
	
	
	
	
	
	
	
}
