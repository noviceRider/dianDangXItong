package com.huibo.pawn.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huibo.pawn.bo.WareHouseConfigurationBo;
import com.huibo.pawn.service.WarehouseConfigService;

/**
* <p>Title: 广沣共享典当管理平台- BusinesslogicService</p>
*
* <p>Description:仓库配置  Controller 层</p>
*
* <p>Copyright: Copyright huibo(c) 2018</p>
*
* <p>Company: 汇博科技股份有限公司</p>
*
* @author 谭小东
* @version 1.0
*/
@Controller
public class WarehouseConfigController {
	@Autowired
	private WarehouseConfigService whConfigService;
	
	/**
	 * 初始化：仓库配置基础数据
	 * @return
	 */
	@RequestMapping("/loadWareHouseDataToTab")
	public Map<String,Object> queryWarehouseConfig(WareHouseConfigurationBo whConfigBo){
		Map<String, Object> map = null;
		try {
			map = whConfigService.queryWarehouseConfig(whConfigBo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 初始化：仓库使用状态下拉框
	 * @return
	 */
	@RequestMapping("/loadStatueCombo")
	public Map<String,Object> queryWhStateCombobox(){
		Map<String, Object> map = null;
		try {
			map = whConfigService.queryWhStateCombobox();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 状态:启用、停用
	 * @param whConfigBo
	 * @return
	 */
	@RequestMapping("/updWarehouseStatue")
	public Map<String,Object> modifyWhState(WareHouseConfigurationBo whConfigBo){
		Map<String, Object> map = null;
		try {
			map = whConfigService.modifyWhState(whConfigBo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增、修改：查询负责人下拉框
	 * @return
	 */
	@RequestMapping("/loadContentCombo")
	public Map<String,Object> queryContactCombobox(){
		Map<String, Object> map = null;
		try {
			map = whConfigService.queryContactCombobox();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增：仓库配置
	 * @param whConfigBo
	 * @return
	 */
	@RequestMapping("/addWareHouse")
	public Map<String,Object> addWhConfig(WareHouseConfigurationBo whConfigBo){
		Map<String, Object> map = null;
		try {
			map = whConfigService.addWhConfig(whConfigBo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除：仓库配置
	 * @param delIds 仓库编号
	 * @return
	 */
	@RequestMapping("/delWarehouse")
	public Map<String,Object> removeWhConfig(
						@RequestParam("delIds[]")String[] delIds){
		Map<String, Object> map = null;
		try {
			map = whConfigService.removeWhConfig(delIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改：仓库配置
	 * @param whConfigBo
	 * @return
	 */
	@RequestMapping("/updWareHouse")
	public Map<String,Object> modifyWhConfig(WareHouseConfigurationBo whConfigBo){
		Map<String, Object> map = null;
		try {
			map = whConfigService.modifyWhConfig(whConfigBo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 验证：仓库编号
	 * @param whConfigBo
	 * @return
	 */
	@RequestMapping("/warehouseValidateState")
	public Map<String,Object> validateWhCodeIsExist(WareHouseConfigurationBo whConfigBo){
		Map<String, Object> map = null;
		try {
			map = whConfigService.validateWhCodeIsExist(whConfigBo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
