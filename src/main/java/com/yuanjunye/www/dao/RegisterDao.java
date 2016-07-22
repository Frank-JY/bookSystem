package com.yuanjunye.www.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.MybatisUtil;

public class RegisterDao {
	
	private SqlSessionFactory factory;
	private SqlSession session;
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUserDao(User user) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.addUserDao(user);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			MybatisUtil.close(session);
		}
		return t;
	}
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	public int addStudentDao(Student student) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.addStudentDao(student);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return t;
	}
	
	/**
	 * 添加管理员信息
	 * @param manager
	 * @return
	 */
	public int addManagerDao(Manager manager) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.addManagerDao(manager);
			session.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return t;
	}
	
	/**
	 * 判断用户名是否已被注册
	 * @param userName
	 * @return
	 */
	public int checkUserNameDao(String userName) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.checkUserNameDao(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 判断学生学会是否被使用
	 * @param studentId
	 * @return
	 */
	public int checkStudentIdDao(long studentId){
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.checkStudentIdDao(studentId);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return t;
	}
	
	/**
	 * 判断管理员职工号是否被使用
	 * @param managerId
	 * @return
	 */
	public int checkManagerIdDao(int managerId){
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.checkManagerIdDao(managerId);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return t;
	}
	
	/**
	 * 判断邮箱是否被使用
	 * @param email
	 * @return
	 */
	public int checkStudentEmailDao(String email) {
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.checkStudentEmailDao(email);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return t;
	}
	/**
	 * 判断邮箱是否被使用
	 * @param email
	 * @return
	 */
	public int checkManagerEmailDao(String email)
	{
		int t = -1;
		try {
			factory = MybatisUtil.getFactory();
			session = factory.openSession();
			IRegisterDao iregisterDao = session.getMapper(IRegisterDao.class);
			t = iregisterDao.checkManagerEmailDao(email);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return t;
	}
}
