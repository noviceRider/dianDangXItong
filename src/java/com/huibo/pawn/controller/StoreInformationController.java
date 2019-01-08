package com.huibo.pawn.controller;
import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huibo.pawn.bo.StoreInformationBo;
import com.huibo.pawn.bo.WareHouseConfigurationBo;
import com.huibo.pawn.po.StoreInformationPo;
import com.huibo.pawn.service.StoreInformationService;

/**
 * <p>title:广沣共享典当管理系统-StoreInformationController</p>
 * 
 * <p>Description:门店管理的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Controller
public class StoreInformationController {
	
	@Autowired
	private StoreInformationService storeInformationService ;
	
	/**
	 * 	状态下拉的方法
	 * @return
	 */
	@RequestMapping("/getshopStateDropdown")
	public Map<String,Object> getshopStateDropdown(){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.getshopStateDropdown());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	初始化加载数据
	 * @return
	 */
	@RequestMapping("/getStore")
	public Map<String,Object> getStore(StoreInformationBo bo){
	Map<String, Object> map = null ;
		
		try {
			map = storeInformationService.getStore(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 加载仓库面板的方法
	 * @return
	 */
	@RequestMapping("/getWareHouse")
	public Map<String,Object> getWareHouse(WareHouseConfigurationBo bo){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.getWareHouse(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增店面的方法
	 * @return
	 */
	@RequestMapping("/addStore")
	public Map<String,Object> addStore(StoreInformationPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.addStore(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增关联仓库的方法
	 * @param whCode
	 * @param shopCode
	 * @return
	 */
	@RequestMapping("/addStoreAndWare")
	public Map<String,Object> addStoreAndWare(String whCode ,String shopCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.addStoreAndWhouse(whCode,shopCode));
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
	@RequestMapping("/yanzheng")
	public  Map<String, Object> validatePriorityCode(StoreInformationPo po){
		int code = 0 ;
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = storeInformationService.validatePriorityCode(po);
		if(count == 0) {
			code = 1;
		}
		map.put("resultCode", code);
		return map;
	}
	
	/**
	 * 	禁用、启用
	 */
	@RequestMapping("/operation")
	public Map<String,Object> operation(StoreInformationBo bo) {
		return new HashMap<String,Object>(){
			{
				put("result",storeInformationService.operation(bo));
			}
		};
	}
	
	/**
	 * 	根据编号查询单挑数据
	 */
	@RequestMapping("/getStoreByShopCode")
	public  Map<String,Object> getStoreByShopCode(StoreInformationPo po){
		Map<String,Object> result = null ;
		try {
			result = new HashMap<String,Object>();
			result.put("result", storeInformationService.getStoreByShopCode(po));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result ;
	}
	
	/**
	 * 	根据编号查询关联仓库
	 */
	@RequestMapping("/getWareHouseByShopCode")
	public Map<String,Object> getWareHouseByShopCode(String shopCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.getWareHouseByShopCode(shopCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	修改时的待选仓库数据
	 * @param bo
	 * @return
	 */
	@RequestMapping("/getWareHouseNotInRight")
	public Map<String,Object> getWareHouseNotInRight(WareHouseConfigurationBo bo){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.getWareHouseNotInRight(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改店面的方法
	 * @return
	 */
	@RequestMapping("/updStore")
	public Map<String,Object> updStore(StoreInformationPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.updStore(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改关联店铺的方法
	 */
	@RequestMapping("/removeStoreAndWare")
	public Map<String,Object> removeStoreAndWare(String shopCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.removeStoreAndWare(shopCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除店铺的方法
	 */
	@RequestMapping("/delStore")
	public Map<String,Object> delStore(String shopCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", storeInformationService.delStore(shopCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
