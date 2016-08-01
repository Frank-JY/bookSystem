package com.yuanjunye.www.service;

import java.util.List;

import com.yuanjunye.www.po.Advice;
import com.yuanjunye.www.util.PageUtil;

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
	public List<Advice> ShowAdvice(PageUtil pUtil);
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public boolean deleteAdvice(int id);
	
	/**
	 * 查询所有意见反馈数量
	 * @return
	 */
	public PageUtil findAdviceNumber(int currentPage);
}
