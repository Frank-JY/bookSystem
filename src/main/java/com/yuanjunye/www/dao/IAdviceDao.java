package com.yuanjunye.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public List<Advice> ShowAdviceDao(@Param("fromIndex")int fromIndex,@Param("pageSize")int pageSize);
	
	/**
	 * 删除意见
	 * @param id
	 * @return
	 */
	public int deleteAdviceDao(int id);
	
	/**
	 * 查询所有意见数量
	 */
	public int findAdviceNumberDao();
}
