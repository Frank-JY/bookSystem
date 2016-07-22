package com.yuanjunye.www.dao;

import java.util.List;

import com.yuanjunye.www.po.Advice;


/**
 * @author hasee
 *	对建议表的操作
 */
public interface IAdviceDao {
	
	/**
	 * 添加意见反馈
	 * @param advice
	 * @return
	 */
	public int addAdviceDao(Advice advice);

	/**
	 * 查询所有意见反馈
	 * @return
	 */
	public List<Advice> ShowAdviceDao();
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public int deleteAdviceDao(int id);
}
