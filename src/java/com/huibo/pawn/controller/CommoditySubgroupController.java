package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.po.ProductCatPo;
import com.huibo.pawn.service.CommoditySubgroupService;

/**
 * <p>title:广沣共享典当管理系统-commoditySubgroupController</p>
 * 
 * <p>Description:商品小类的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Controller
public class CommoditySubgroupController {

	@Autowired
	private CommoditySubgroupService commoditySubgroupService ;
	
	/**
	 * 	初始化加载数据
	 * @return
	 */
	@RequestMapping("/commoditySubgroupTable")
	public Map<String,Object> commoditySubgroupTable(ProductCatBo bo){
	Map<String, Object> map = null ;
		
		try {
			map = commoditySubgroupService.commoditySubgroupTable(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取所有大类的数据
	 * @return
	 */
	@RequestMapping("/getP_CatNameDropdown")
	public Map<String,Object> getPCatNameDropdown(){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", commoditySubgroupService.getPCatNameDropdown());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增商品小类的方法
	 * @return
	 */
	@RequestMapping("/addCommoditySubgroup")
	public Map<String,Object> addCommoditySubgroup(ProductCatPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", commoditySubgroupService.addCommoditySubgroup(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改商品小类的方法
	 * @return
	 */
	@RequestMapping("/updCommoditySubgroup")
	public Map<String,Object> updCommoditySubgroup(ProductCatPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", commoditySubgroupService.updCommoditySubgroup(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	禁用、启用
	 */
	@RequestMapping("/conversion")
	public Map<String,Object> conversion(ProductCatPo po) {
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", commoditySubgroupService.conversion(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
