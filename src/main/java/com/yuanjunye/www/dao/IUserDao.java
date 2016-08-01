package com.yuanjunye.www.dao;

import java.util.List;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Param1;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;


/**
 * @author hasee
 * 对用户进行操作
 */
public interface IUserDao {

	/**
	 * 显示等待申请的学生
	 * @return
	 */
	public List<User> showApplyStudentDao();
	
	/**
	 * 显示等待申请的管理员
	 * @return
	 */
	public List<User> showApplyManagerDao();
	
	/**
	 * 同意用户注册申请
	 * @param userName
	 * @return
	 */
	public int passUserDao(String userName);
	
	/**
	 * 不同意用户注册申请
	 * @param userName
	 * @return
	 */
	public int rejectUserDao(String userName);
	
	/**
	 * 显示通过申请的学生
	 * @return
	 */
	public List<User> showAllStudentDao();
	
	/**
	 * 显示通过申请的管理员
	 * @return
	 */
	public List<User> showAllManagerDao();
	
	/**
	 * 学生用户个人信息
	 * @param userName
	 * @return
	 */
	public User myStudentDao(String userName);
	
	/**
	 * 管理员用户个人信息
	 * @param userName
	 * @return
	 */
	public User myManagerDao(String userName);
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public int updateStudentDao(Student student);
	
	/**
	 * 更新管理员信息
	 * @param student
	 * @return
	 */
	public int updateManagerDao(Manager manager);
	
	/**
	 *修改用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordDao(User user);
	
	/**
	 * 查询用户身份
	 * @param userName
	 * @return
	 */
	public String findIdentityDao(String userName);
	
	/**
	 * 模糊查询管理员
	 * @param keyword
	 * @return
	 */
	public List<User> searchManagerDao(String keyword);
	
	/**
	 * 模糊查询学生
	 * @param userName
	 * @return
	 */
	public List<User> searchStudentDao(String keyword);
	
	/**
	 * 更改学生用户头像
	 * @param student
	 * @return
	 */
	public int updateStudentPhotoDao(Student student);
	
	/**
	 * 更改管理员用户头像
	 * @param student
	 * @return
	 */
	public int updateManagerPhotoDao(Manager manager);
	
	/**
	 * 查询学生头像路径
	 * @param userName
	 * @return
	 */
	public String findStudentPhotoDao(String userName);
	
	/**
	 * 查询管理员头像路径
	 * @param userName
	 * @return
	 */
	public String findManagerPhotoDao(String userName);
	
	/**
	 * 多条件查询学生
	 * @param userName
	 * @return
	 */
	public List<User> conditionStudentDao(Param1 param);
	
	/**
	 * 多条件查询管理员
	 * @param keyword
	 * @return
	 */
	public List<User> conditionManagerDao(Param1 param);
}
