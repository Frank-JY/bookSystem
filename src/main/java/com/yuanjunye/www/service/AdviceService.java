package com.yuanjunye.www.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yuanjunye.www.dao.IAdviceDao;
import com.yuanjunye.www.dao.IUserDao;
import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.util.PageUtil;

@Service
public class AdviceService implements IAdviceService{

	@Resource
	private IAdviceDao adviceDao;
	@Resource
	private IUserDao userDao;
	
	/**
	 * 添加意见反馈
	 * @param advice
	 * @return
	 */
	public boolean addAdvice(String userName, String advice) {
		boolean bool = true;
		//反馈时间
		Date time = new Date();
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentTime  = new Date();
			String s = df.format(currentTime);
			time = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		Advice advices = new Advice();
		advices.setUserName(userName);
		advices.setAdvice(advice);
		advices.setTime(time);
		int t = adviceDao.addAdviceDao(advices);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询所有意见反馈
	 * @return
	 */
	public List<Advice> ShowAdvice(PageUtil pUtil) {
		int fromIndex = pUtil.getFromIndex();
		int pageSize = pUtil.getPageSize();
		List<Advice> adviceList = adviceDao.ShowAdviceDao(fromIndex,pageSize);
		for(Advice advice : adviceList) {
			String advicephoto = "";
			if(advice.getIdentity().equals("学生")) {
				advicephoto = userDao.findStudentPhotoDao(advice.getUserName());
			}else {
				advicephoto = userDao.findManagerPhotoDao(advice.getUserName());
			}
			advice.setPhoto(advicephoto);
		}
		return adviceList;
	}
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public boolean deleteAdvice(int id) {
		boolean bool = true;
		int t = adviceDao.deleteAdviceDao(id);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}

	@Override
	public PageUtil findAdviceNumber(int currentPage) {
		int number = adviceDao.findAdviceNumberDao();
		PageUtil pUtil = new PageUtil(5, number, currentPage);
		return pUtil;
	}
}

