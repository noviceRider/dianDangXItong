package com.huibo.pawn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ChannelBo;
import com.huibo.pawn.po.ChannelPo;

public interface ChannelDao {

	/**
	 *  查渠道商
	 * @param page
	 * @return
	 */
	public List<ChannelBo> queryChannelTable(Page page);

	/**
	 *  验证
	 * @param bo
	 * @return
	 */
	public Integer validateChannel(ChannelBo bo);

	/**
	 *  新增
	 * @param bo
	 */
	public void addChannel(ChannelBo bo);

	/**
	 *  删除
	 * @param channelCodes
	 */
	public void romoveChannelById( @Param("channelCodes") String channelCodes);

	/**
	 *  单条查询
	 * @param channelCode
	 * @return
	 */
	public ChannelPo queryChannelById(String channelCode);

	/**
	 *  修改
	 * @param bo
	 */
	public void modifyChannel(ChannelBo bo);

	/**
	 *  	禁用、启用转换
	 * @param bo
	 */
	public void switchM(ChannelBo bo);

}
