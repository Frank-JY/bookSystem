package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.po.Advice;

public interface IAdviceService {

	
	/**
	 * 添加意见反馈
	 * @param advice
	 * @return
	 */
	public boolean addAdvice(String userName,String advice);

	/**
	 * 查询所有意见反馈
	 * @return
	 */
	public List<Advice> ShowAdvice();
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public boolean deleteAdvice(int id);
	
	
}
