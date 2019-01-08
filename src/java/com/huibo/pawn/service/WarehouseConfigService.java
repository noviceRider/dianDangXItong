package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.WareHouseConfigurationBo;
import com.huibo.pawn.dao.WarehouseConfigurationDao;
import com.huibo.pawn.po.WareHouseConfigurationPo;

/**
* <p>Title: 广沣共享典当管理平台- BusinesslogicService</p>
*
* <p>Description:仓库配置  Service 层</p>
*
* <p>Copyright: Copyright huibo(c) 2018</p>
*
* <p>Company: 汇博科技股份有限公司</p>
*
* @author 谭小东
* @version 1.0
*/
@Service
public class WarehouseConfigService {
	@Autowired
	private WarehouseConfigurationDao whConfigDao;

	/**
	 * 初始化：仓库配置基础数据
	 * @return
	 */
	public Map<String, Object> queryWarehouseConfig(WareHouseConfigurationBo whConfigBo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page(whConfigBo);
		map.put("rows", whConfigDao.loadWareHouseDataToTab(page));
		map.put("total", page.getTotalRecord());
		return map;
	}

	/**
	 * 初始化：仓库使用状态下拉框
	 * @return
	 */
	public Map<String, Object> queryWhStateCombobox() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WareHouseConfigurationPo> list = whConfigDao.loadStatueCombo();
		map.put("result",list);
		return map;
	}

	/**
	 * 状态:启用、停用
	 * @param whConfigBo
	 * @return
	 */
	public Map<String, Object> modifyWhState(WareHouseConfigurationBo whConfigBo) {
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		whConfigDao.updWarehouseStatue(whConfigBo);
		row = 1;
		map.put("result", row);
		return map;
	}

	/**
	 * 新增、修改：查询负责人下拉框
	 * @return
	 */
	public Map<String, Object> queryContactCombobox() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WareHouseConfigurationPo> list = whConfigDao.loadContentCombo();
		map.put("result",list);
		return map;
	}

	/**
	 * 新增：仓库配置
	 * @param whConfigBo
	 * @return
	 */
	public Map<String, Object> addWhConfig(WareHouseConfigurationBo whConfigBo) {
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		whConfigDao.addWareHouse(whConfigBo);
		row = 1;
		map.put("result", row);
		return map;
	}

	/**
	 * 删除：仓库配置
	 * @param delIds 仓库编号
	 * @return
	 */
	public Map<String, Object> removeWhConfig(String[] delIds) {
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		whConfigDao.delWarehouse(delIds);
		row = 1;
		map.put("result", row);
		return map;
	}

	/**
	 * 修改：仓库配置
	 * @param whConfigBo
	 * @return
	 */
	public Map<String, Object> modifyWhConfig(WareHouseConfigurationBo whConfigBo) {
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		whConfigDao.updWareHouse(whConfigBo);
		row = 1;
		map.put("result", row);
		return map;
	}

	/**
	 * 验证：仓库编号
	 * @param whConfigBo
	 * @return
	 */
	public Map<String, Object> validateWhCodeIsExist(WareHouseConfigurationBo whConfigBo) {
		int resultCode = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = whConfigDao.warehouseValidateState(whConfigBo);
		if(count == 0) {
			resultCode = 1;
		}
		map.put("resultCode", resultCode);
		return map;
	}
}
