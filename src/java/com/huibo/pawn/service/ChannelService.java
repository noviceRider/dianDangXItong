package com.huibo.pawn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ChannelBo;
import com.huibo.pawn.dao.ChannelDao;
import com.huibo.pawn.po.ChannelPo;

/**
* <p>Title: 共享典当系统 - ChannelService</p>
*
* <p>Description:渠道商管理的Service</p>
*
* <p>Copyright: Copyright HBRC(c) 2018</p>
*
* <p>Company: 汇博人才</p>
*
* @author 王杰  
* @version 1.0
*/
@Service
public class ChannelService {
	
	@Autowired
	public ChannelDao channelDao;
	
	/**
	 *    查询渠道商
	 * @param po
	 * 	继承类
	 * @return
	 * 	渠道商
	 */
	public Map<String, Object> queryChannelTable(ChannelBo bo) {
		//创建map对象
		Map<String,Object> map = new HashMap<String,Object>();
		//分页
		Page page = new Page(bo);
		//创建list
		List<ChannelBo> list = new ArrayList<ChannelBo>();
		//调用接口中同名方法
		list = channelDao.queryChannelTable(page);
		//存数据
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		
		return map;
	}

	/**
	 * 	验证
	 * @param bo
	 * @return
	 */
	public Map<String, Object> validateChannel(ChannelBo bo) {
		
		int resultCode = 0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Integer count = channelDao.validateChannel(bo);
		if (count == 0) {
			resultCode = 1;
		}
		map.put("resultCode", resultCode);
		
		return map;
	}

	/**
	 *  	新增
	 * @param bo
	 * @return
	 */
	public Map<String, Object> addChannel(ChannelBo bo) {

		int resultCode = 0;
		Map<String, Object> map = new HashMap<String, Object>();

		//获取当前登录用户的ID
		bo.setCreateBy(CommonFunction.getUserFromSession().getUserId());
		bo.setModifyBy(CommonFunction.getUserFromSession().getUserId());
		channelDao.addChannel(bo);
		resultCode = 1;
		map.put("resultCode", resultCode);

		return map;
	}

	/**
	 *  删除
	 * @param channelCodes
	 * @return
	 */
	public Map<String, Object> romoveChannelById(String channelCodes) {
		
		int resultCode = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		channelDao.romoveChannelById(channelCodes);

		resultCode = 1;
		map.put("resultCode", resultCode);
		
		return map;
	}

	/**
	 *  单条查询
	 * @param channelCode
	 * @return
	 */
	public Map<String, Object> queryChannelById(String channelCode) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		ChannelPo po = channelDao.queryChannelById(channelCode);
		map.put("row", po);
		
		return map;
	}

	/**
	 *  修改
	 * @param bo
	 * @return
	 */
	public Map<String, Object> modifyChannel(ChannelBo bo) {
		
		int resultCode = 0;

		Map<String, Object> map = new HashMap<String, Object>();

		bo.setModifyBy(CommonFunction.getUserFromSession().getUserId());
		channelDao.modifyChannel(bo);

		resultCode = 1;
		map.put("resultCode", resultCode);
		
		return map;
	}

	/**
	 * 	禁用、启用转换
	 * @param bo
	 * @return
	 */
	public Map<String, Object> switchM(ChannelBo bo) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();	
		int row = 0;
		channelDao.switchM(bo);
		row = 1;
		map.put("result", row);
		return map;
	}

}
