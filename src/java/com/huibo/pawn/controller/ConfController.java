package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.pawn.bo.ConfBo;
import com.huibo.pawn.po.ConfPo;
import com.huibo.pawn.service.ConfService;
/**
 * <p>title:广沣共享典当管理系统-ConfController</p>
 * 
 * <p>Description:商品属性的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Controller
public class ConfController {

	@Autowired
	private ConfService confService ;
	
	/**
	 * 	初始化加载数据
	 * @return
	 */
	@RequestMapping("/confTable")
	public Map<String,Object> confTable(ConfBo bo){
	Map<String, Object> map = null ;
		
		try {
			map = confService.confTable(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增属性的方法
	 * @return
	 */
	@RequestMapping("/addConf")
	public Map<String,Object> addConf(ConfPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", confService.addConf(po));
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
	@RequestMapping("/yanzhengConfByAttrCode")
	public  Map<String, Object> validatePriorityCode(ConfPo po){
		po.setAttrCode(po.getGroupCode()+po.getAttrCode());
		int code = 0 ;
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = confService.validatePriorityCode(po);
		if(count == 0) {
			code = 1;
		}
		map.put("resultCode", code);
		return map;
	}
	
	/**
	 * 	根据编号加载信息
	 * @return
	 */
	@RequestMapping("/getConfByAttrCode")
	public Map<String,Object> getConfByAttrCode(String attrCode){
		Map<String,Object> map = null ;
		try {
			map = new HashMap<String,Object>();
			map.put("rows", confService.getConfByAttrCode(attrCode));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return map ;
	}
	
	/**
	 * 修改属性的方法
	 * @return
	 */
	@RequestMapping("/updConf")
	public Map<String,Object> updConf(ConfPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", confService.updConf(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除属性的方法
	 */
	@RequestMapping("/delConf")
	public Map<String,Object> delConf(String attrCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", confService.delConf(attrCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
