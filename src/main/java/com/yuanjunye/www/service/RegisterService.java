package com.yuanjunye.www.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElementRefs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanjunye.www.dao.IRegisterDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
@Service
public class RegisterService implements IRegisterService{
	@Resource
	private IRegisterDao registerDao;
	
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

	@Override
	public Map<String, String> checkAll(String userName, String ids, String email,String identity) {
		Map<String, String> errorMap = new HashMap<String,String>();
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int t4 = 0;
		if(identity.equals("学生")) {
			t2 = registerDao.checkStudentIdDao(Long.valueOf(ids));
			t3 = registerDao.checkManagerEmailDao(email);
			t4 = registerDao.checkStudentEmailDao(email);
		}else {
			t2 = registerDao.checkManagerIdDao(Integer.valueOf(ids));
			t3 = registerDao.checkManagerEmailDao(email);
			t4 = registerDao.checkStudentEmailDao(email);
		}
		t1 = registerDao.checkUserNameDao(userName);
		if(t1 == 1) {
			errorMap.put("error1", "用户名已存在");
		}
		if(t2 == 1) {
			errorMap.put("error2", "学号（职工号）已存在");
		}
		if(t3 == 1 || t4 == 1) {
			errorMap.put("error3", "邮箱已存在");
		}
		return errorMap;
	}

	
	@Override
	@Transactional
	public boolean stRegister(User user, Student student) {
		boolean bool = true;
		user.setStatus("等待申请");
		int t1 = registerDao.addUserDao(user);
		int t2 = registerDao.addStudentDao(student);
		if(t1 == 0 || t2 == 0) {
			bool = false;
		}
		return bool;
	}

	@Override
	@Transactional
	public boolean maRegister(User user, Manager manager) {
		boolean bool = true;
		user.setStatus("等待申请");
		int t1 = registerDao.addUserDao(user);
		int t2 = registerDao.addManagerDao(manager);	
		if(t1 == 0 || t2 == 0) {
			bool = false;
		}
		return bool;
	}
	
	
	
	
	
	
	
}
