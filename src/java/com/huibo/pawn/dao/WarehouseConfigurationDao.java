package com.huibo.pawn.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.WareHouseConfigurationBo;
import com.huibo.pawn.po.WareHouseConfigurationPo;

/**
* <p>Title: 广沣共享典当管理平台- BusinesslogicService</p>
*
* <p>Description:仓库配置  Dao 层</p>
*
* <p>Copyright: Copyright huibo(c) 2018</p>
*
* <p>Company: 汇博科技股份有限公司</p>
*
* @author 谭小东
* @version 1.0
*/
public interface WarehouseConfigurationDao {
	
	/**
	 * 初始化：仓库配置基础数据
	 * @param page
	 * @return
	 */
	public List<WareHouseConfigurationPo> loadWareHouseDataToTab(Page page);

	/**
	 * 初始化：仓库使用状态下拉框
	 * @return
	 */
	public List<WareHouseConfigurationPo> loadStatueCombo();

	/**
	 * 状态:启用、停用
	 * @param whConfigBo
	 */
	public void updWarehouseStatue(WareHouseConfigurationBo whConfigBo);

	/**
	 * 新增、修改：查询负责人下拉框
	 * @return
	 */
	public List<WareHouseConfigurationPo> loadContentCombo();

	/**
	 * 新增：仓库配置
	 * @param whConfigBo
	 */
	public void addWareHouse(WareHouseConfigurationBo whConfigBo);

	/**
	 * 删除：仓库配置
	 * @param delIds 仓库编号
	 * @return
	 */
	public void delWarehouse(String[] delIds);

	/**
	 * 修改：仓库配置
	 * @param whConfigBo
	 * @return
	 */
	public void updWareHouse(WareHouseConfigurationBo whConfigBo);

	/**
	 * 验证：仓库编号
	 * @param whConfigBo
	 * @return
	 */
	public Integer warehouseValidateState(WareHouseConfigurationBo whConfigBo);

}
