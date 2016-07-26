package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Param1;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;

public interface IUserService {

	/**
	 * 显示等待申请的学生
	 * @return
	 */
	public List<User> showApplyStudent();
	
	/**
	 * 显示等待申请的管理员
	 * @return
	 */
	public List<User> showApplyManager();
	
	/**
	 * 同意申请
	 * @param userName
	 * @return
	 */
	public boolean passUser(String userName);
	
	/**
	 * 不同意申请
	 * @param userName
	 * @return
	 */
	public boolean rejectUser(String userName);
	
	/**
	 * 显示通过申请的学生
	 * @return
	 */
	public List<User> showAllStudent();
	
	/**
	 * 显示通过申请的管理员
	 * @return
	 */
	public List<User> showAllManager();
	
	/**
	 * 学生用户个人信息
	 * @return
	 */
	public User myStudent(String userName);
	
	/**
	 * 管理员用户个人信息
	 * @return
	 */
	public User myManager(String userName);

	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public boolean updateStudent(Student student);
	
	/**
	 * 更新管理员信息
	 * @param student
	 * @return
	 */
	public boolean updateManager(Manager manager);
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public boolean updatePassword(User user,String newPassword, String oldPassword);
	
	/**
	 * 查询用户身份
	 * @param userName
	 * @return
	 */
	public String findIdentity(String userName);
	
	/**
	 * 模糊查询学生
	 * @param userName
	 * @return
	 */
	public List<User> searchStudent(String keyword);
	
	/**
	 * 模糊查询管理员
	 * @return
	 */
	public List<User> searchManager(String keyword);
	
	/**
	 * 更改学生用户头像
	 * @param student
	 * @return
	 */
	public boolean updateStudentPhoto(Student student);
	
	/**
	 * 更改管理员用户头像
	 * @param student
	 * @return
	 */
	public boolean updateManagerPhoto(Manager manager);
	
	/**
	 * 查询学生头像路径
	 * @param userName
	 * @return
	 */
	public String findStudentPhoto(String userName);
	
	/**
	 * 查询管理员头像路径
	 * @param userName
	 * @return
	 */
	public String findManagerPhoto(String userName);
	
	/**
	 * 多条件查询学生
	 * @param userName
	 * @return
	 */
	public List<User> conditionStudent(Param1 param);
	
	/**
	 * 多条件查询管理员
	 * @param keyword
	 * @return
	 */
	public List<User> conditionManager(Param1 param);
	
}
