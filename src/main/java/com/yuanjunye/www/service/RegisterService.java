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
		Boolean bool = true;
		int t = registerDao.addUserDao(user);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		boolean bool = true;
		int t = registerDao.addStudentDao(student);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 添加管理员
	 * @param manager
	 * @return
	 */
	public boolean addManager(Manager manager) {
		boolean bool = true;
		int t = registerDao.addManagerDao(manager);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 检查用户名
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName) {
		boolean bool = true;
		int t = registerDao.checkUserNameDao(userName);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 检查学号
	 * @param studentId
	 * @return
	 */
	public boolean checkStudentId(long studentId) {
		boolean bool = true;
		int t = registerDao.checkStudentIdDao(studentId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 检查职工号
	 * @param managerId
	 * @return
	 */
	public boolean checkManagerId(int managerId) {
		boolean bool = true;
		int t = registerDao. checkManagerIdDao(managerId);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 检查邮箱
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {
		boolean bool = true;
		int t1 = registerDao.checkStudentEmailDao(email);
		int t2 = registerDao.checkManagerEmailDao(email);
		if(t1 == 0 && t2 == 0) {
			bool = false;
		}
		return bool;
	}
	
	
	
	
	
	
	
}
