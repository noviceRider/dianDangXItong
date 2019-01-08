package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.pawn.bo.AttrGroupBo;
import com.huibo.pawn.po.AttrGroupPo;
import com.huibo.pawn.service.GroupService;

/**
 * <p>title:广沣共享典当管理系统-GroupController</p>
 * 
 * <p>Description:商品属性组的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Controller
public class GroupController {

	@Autowired
	private GroupService groupService ;
	
	/**
	 * 	初始化加载数据
	 * @return
	 */
	@RequestMapping("/groupTable")
	public Map<String,Object> groupTable(AttrGroupBo bo){
		Map<String, Object> map = null ;
		
		try {
			map = groupService.groupTable(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	禁用、启用
	 */
	@RequestMapping("/changeGroupState")
	public Map<String,Object> changeGroupState(AttrGroupPo po) {
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.changeGroupState(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	表单验证
	 * @param groupCode
	 * @return
	 */
	@RequestMapping("/yanzhengByGroupCode")
	public  Map<String, Object> validatePriorityCode(String groupCode){
		int code = 0 ;
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = groupService.validatePriorityCode(groupCode);
		if(count == 0) {
			code = 1;
		}
		map.put("resultCode", code);
		return map;
	}
	
	/**
	 * 新增属性组的方法
	 * @return
	 */
	@RequestMapping("/addGroup")
	public Map<String,Object> addGroup(AttrGroupPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.addGroup(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增属性组关联大类的方法
	 * @param catCode
	 * @param groupCode
	 * @return
	 */
	@RequestMapping("/addAttrAndProd")
	public Map<String,Object> addAttrAndProd(String catCode ,String groupCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.addAttrAndProd(catCode,groupCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	根据编号加载信息
	 * @return
	 */
	@RequestMapping("/getAttrByGroupCode")
	public Map<String,Object> getAttrByGroupCode(String groupCode){
		Map<String,Object> map = null ;
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.getAttrByGroupCode(groupCode));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return map ;
	}
	
	/**
	 * 	根据编号查询已选商品大类
	 */
	@RequestMapping("/getCommodityByGroupCode")
	public Map<String,Object> getCommodityByGroupCode(String groupCode){
		Map<String, Object> map = null ;
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.getCommodityByGroupCode(groupCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 修改属性组的方法
	 * @return
	 */
	@RequestMapping("/updGroup")
	public Map<String,Object> updGroup(AttrGroupPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.updGroup(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改关联商品大类的方法
	 */
	@RequestMapping("/removeGroupAndProd")
	public Map<String,Object> removeGroupAndProd(String groupCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.removeGroupAndProd(groupCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除属性组的方法
	 */
	@RequestMapping("/delGroup")
	public Map<String,Object> delGroup(String groupCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", groupService.delGroup(groupCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
