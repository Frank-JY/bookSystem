package com.yuanjunye.www.service;

import java.util.Map;

import com.yuanjunye.www.Exception.ServiceException;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;

public interface IRegisterService {

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student);
	
	/**
	 * 添加管理员
	 * @param manager
	 * @return
	 */
	public boolean addManager(Manager manager);
	
	/**
	 * 检查用户名
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName);
	
	/**
	 * 检查学号
	 * @param studentId
	 * @return
	 */
	public boolean checkStudentId(long studentId);
	
	/**
	 * 检查职工号
	 * @param managerId
	 * @return
	 */
	public boolean checkManagerId(int managerId);
	
	/**
	 * 检查邮箱
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email);
	
	/**
	 * 注册验证
	 */
	public Map<String,String> checkAll(String userName, String ids,String email,String identity);
	
	/**
	 * 学生完成注册
	 */
	public boolean stRegister(User user, Student student,String email);
	
	/**
	 * 管理员完成注册
	 */
	public boolean maRegister(User user, Manager manager,String email);

	public void processActivate(String email, String validateCode)throws ServiceException;
}
