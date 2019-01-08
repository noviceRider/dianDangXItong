package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.pawn.bo.ChannelBo;
import com.huibo.pawn.service.ChannelService;

/**
* <p>Title: 共享典当系统 - ChannelController</p>
*
* <p>Description:渠道商管理的Controller</p>
*
* <p>Copyright: Copyright HBRC(c) 2018</p>
*
* <p>Company: 汇博人才</p>
*
* @author 王杰  
* @version 1.0
*/
@Controller
public class ChannelController {
	
	@Autowired
	public ChannelService channelService;
	
	/**
	 * 	查询渠道商
	 * @param bo
	 * 	查询渠道商
	 * @return
	 * 	工时月报JSON数据
	 */
	@RequestMapping("/queryChannelTable")
	public Map<String, Object> queryChannelTable(ChannelBo bo) {
		
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			//调用服务层同名方法
			map = channelService.queryChannelTable(bo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//validateState
	/**
	 *  验证编号非空且不重复
	 * @param bo
	 * @return
	 */
	@RequestMapping("/validateChannel")
	public Map<String, Object> validateChannel(ChannelBo bo) {
		
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			//调用服务层同名方法
			map = channelService.validateChannel(bo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	} 

	//addChannel
	/**
	 *  新增
	 * @param bo
	 * @return
	 */
	@RequestMapping("/addChannel")
	public Map<String, Object> addChannel(ChannelBo bo) {

		Map<String, Object> map = null;
		try {
			map = channelService.addChannel(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *  删除
	 * @param stateCodes
	 * @return
	 */
	@RequestMapping("/romoveChannelById")
	public Map<String, Object> romoveChannelById(String channelCodes) {
		
		Map<String, Object> map = null;
		try {
			map = channelService.romoveChannelById(channelCodes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 单条查询
	 */
	@RequestMapping("/queryChannelById")
	public Map<String, Object> queryChannelById(String channelCode) {
		
		Map<String, Object> map = null;
		try {
			map = channelService.queryChannelById(channelCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	//modifyChannel
	/**
	 *  修改
	 * @param bo
	 * @return
	 */
	@RequestMapping("/modifyChannel")
	public Map<String, Object> modifyChannel(ChannelBo bo){
		Map<String, Object> map = null;
		try {
			map = channelService.modifyChannel(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *  禁用、启用
	 * @param bo
	 * @return
	 */
	@RequestMapping("/switchM")
	public Map<String, Object> switchM(ChannelBo bo){
		
		Map<String,Object> map = null;
		try {
			map = channelService.switchM(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
