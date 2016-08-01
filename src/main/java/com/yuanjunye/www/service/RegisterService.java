package com.yuanjunye.www.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElementRefs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuanjunye.www.Exception.ServiceException;
import com.yuanjunye.www.dao.IRegisterDao;
import com.yuanjunye.www.po.Manager;
import com.yuanjunye.www.po.Student;
import com.yuanjunye.www.po.User;
import com.yuanjunye.www.util.MD5Util;
import com.yuanjunye.www.util.SendEmail;
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
	public boolean stRegister(User user, Student student,String email) {
		boolean bool = true;
		user.setStatus("等待激活");
		int t1 = registerDao.addUserDao(user);
		int t2 = registerDao.addStudentDao(student);
		if(t1 == 0 || t2 == 0) {
			bool = false;
		}
		StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/bookSystem-ssm/log/validate.do?email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("\">http://localhost:8080/bookSystem-ssm/log/validate.do?email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("</a>");

        //发送邮件
        SendEmail.send(email, sb.toString());
        System.out.println("发送邮件");
		return bool;
	}

	@Override
	@Transactional
	public boolean maRegister(User user, Manager manager,String email) {
		boolean bool = true;
		user.setStatus("等待激活");
		user.setRegisterTime(new Date());
		user.setValidateCode(MD5Util.encode2hex(email));
		int t1 = registerDao.addUserDao(user);
		int t2 = registerDao.addManagerDao(manager);	
		if(t1 == 0 || t2 == 0) {
			bool = false;
		}
		StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/bookSystem-ssm/log/validate.do?email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("\">http://localhost:8080/bookSystem-ssm/log/validate.do?email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("</a>");

        //发送邮件
        SendEmail.send(email, sb.toString());
        System.out.println("发送邮件");
		return bool;
	}
	
	 /**
     * 处理激活
     * @throws ParseException
     */
      ///传递激活码和email过来
    public void processActivate(String email , String validateCode)throws ServiceException{ 
         //数据访问层，通过email获取用户信息
       User user = null;
       User user1 = registerDao.findUserByEmail1(email);
       User user2 = registerDao.findUserByEmail2(email);
       if(null != user1) {
    	   user = user1;
       }
       if(null != user2) {
    	   user = user2;
       }
       System.out.println("asdasdasd"+user.getStatus());
        //验证用户是否存在
        if(user!=null) { 
            //验证用户激活状态 
            if(user.getStatus().equals("等待激活")) {
                ///没激活
                Date currentTime = new Date();//获取当前时间 
                //验证链接是否过期
                currentTime.before(user.getRegisterTime());
                Date registerTime = user.getRegisterTime();
                Calendar cl = Calendar.getInstance();
                cl.setTime(registerTime);
                cl.add(Calendar.DATE , 2);
                if(currentTime.before(cl.getTime())) { 
                    //验证激活码是否正确 
                    if(validateCode.equals(user.getValidateCode())) { 
                        //激活成功， //并更新用户的激活状态，为已激活
                        System.out.println("==sq==="+user.getStatus());
                        user.setStatus("等待申请");//把状态改为激活
                        System.out.println("==sh==="+user.getStatus());
                        registerDao.updateUserStatusDao(user);
                    } else { 
                       throw new ServiceException("激活码不正确"); 
                    } 
                } else { throw new ServiceException("激活码已过期！"); 
                } 
            } else {
               throw new ServiceException("邮箱已激活，请登录！"); 
            } 
        } else {
            throw new ServiceException("该邮箱未注册（邮箱地址不存在）！"); 
        } 

    } 
	
	
	
	
	
}
