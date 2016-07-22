package com.yuanjunye.www.dao;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;

/**
 * @author hasee
 * 注册操作
 */
public interface IRegisterDao {

	/**
	 * 添加用户学信息
	 * @param user
	 * @return
	 */
	public int addUserDao(User user);
	
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	public int addStudentDao(Student student);
	
	/**
	 * 添加管理员信息
	 * @param manager
	 * @return
	 */
	public int addManagerDao(Manager manager);
	
	/**
	 * 判断用户名是否已被注册
	 * @param userName
	 * @return
	 */
	public int checkUserNameDao(String userName);
	
	/**
	 * 判断学生学会是否被使用
	 * @param studentId
	 * @return
	 */
	public int checkStudentIdDao(long studentId);
	
	/**
	 * 判断管理员职工号是否被使用
	 * @param managerId
	 * @return
	 */
	public int checkManagerIdDao(int managerId);
	
	/**
	 * 判断邮箱是否被使用
	 * @param email
	 * @return
	 */
	public int checkStudentEmailDao(String email);
	
	/**
	 * 判断邮箱是否被使用
	 * @param email
	 * @return
	 */
	public int checkManagerEmailDao(String email);
}
