package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.dao.AdviceDao;
import com.yuanjunye.www.po.Advice;

public class AdviceService {

	private AdviceDao adviceDao = new AdviceDao();
	
	/**
	 * 添加意见反馈
	 * @param advice
	 * @return
	 */
	public boolean addAdvice(Advice advice) {
		boolean bool = true;
		int t = adviceDao.addAdviceDao(advice);
		if(t == 0) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 查询所有意见反馈
	 * @return
	 */
	public List<Advice> ShowAdvice() {
		return adviceDao.ShowAdviceDao();
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
}

