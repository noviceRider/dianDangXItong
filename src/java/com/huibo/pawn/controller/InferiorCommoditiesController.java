package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.po.ProductCatPo;
import com.huibo.pawn.service.InferiorCommoditiesService;
/**
 * <p>title:广沣共享典当管理系统-InferiorCommoditiesController</p>
 * 
 * <p>Description:三级商品的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Controller
public class InferiorCommoditiesController {

	@Autowired
	private InferiorCommoditiesService inferiorCommoditiesService ;
	
	/**
	 * 	初始化加载数据
	 * @return
	 */
	@RequestMapping("/inferiorCommoditiesTable")
	public Map<String,Object> inferiorCommoditiesTable(ProductCatBo bo){
	Map<String, Object> map = null ;
		
		try {
			map = inferiorCommoditiesService.inferiorCommoditiesTable(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	表单验证
	 * @param po
	 * @return
	 */
	@RequestMapping("/yanZhengSanJICatCode")
	public  Map<String, Object> validatePriorityCode(ProductCatPo po){
		po.setCatCode(po.getpCatCode()+po.getCatCode());
		int code = 0 ;
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = inferiorCommoditiesService.validatePriorityCode(po);
		if(count == 0) {
			code = 1;
		}
		map.put("resultCode", code);
		return map;
	}
	
	/**
	 * 新增商品小类的方法
	 * @return
	 */
	@RequestMapping("/addInferiorCommodities")
	public Map<String,Object> addInferiorCommodities(ProductCatPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", inferiorCommoditiesService.addInferiorCommodities(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改三级商品的方法
	 * @return
	 */
	@RequestMapping("/updInferiorCommodities")
	public Map<String,Object> updInferiorCommodities(ProductCatPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", inferiorCommoditiesService.updInferiorCommodities(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
