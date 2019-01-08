package com.huibo.pawn.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.StoreInformationBo;
import com.huibo.pawn.bo.WareHouseConfigurationBo;
import com.huibo.pawn.dao.StoreInformationDao;
import com.huibo.pawn.po.StoreInformationPo;
import com.huibo.pawn.po.WareHouseConfigurationPo;
/**
 * <p>title:广沣共享典当管理系统-StoreInformationService</p>
 * 
 * <p>Description:门店管理的Service</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Service
public class StoreInformationService {

	@Autowired
	private StoreInformationDao storeInformationDao ;

	/**
	 * 	状态下拉的方法
	 * @return
	 */
	public List<StoreInformationPo> getshopStateDropdown() {
		
		return storeInformationDao.getshopStateDropdown();
	}

	/**
	 * 
	 * @param bo
	 * @return
	 */
	public Map<String, Object> getStore(StoreInformationBo bo) {
		Map<String,Object>map = new HashMap<String, Object>();

		Page page = new Page(bo);
		
		List<StoreInformationPo> list = storeInformationDao.getStore(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		
		return map;
	}

	/**
	 * 
	 */
	public List<WareHouseConfigurationPo> getWareHouse(WareHouseConfigurationBo bo) {
		return storeInformationDao.getWareHouse(bo);
	}

	/**
	 * 
	 * @param po
	 * @return
	 */
	public Integer addStore(StoreInformationPo po) {
		po.setShopState("01");
		return storeInformationDao.addStore(po);
	}

	/**
	 * 
	 * @param whCode
	 * @param shopCode
	 * @return
	 */
	public Integer addStoreAndWhouse(String whCode, String shopCode) {
		String[] a = whCode.split(",");
		return storeInformationDao.addStoreAndWhouse(a,shopCode);
	}

	/**
	 * 重复验证
	 * @param po
	 * @return
	 */
	public Integer validatePriorityCode(StoreInformationPo po) {
		
		return storeInformationDao.validatePriorityCode(po);
	}

	/**
	 * 	启用、禁用
	 * @param bo
	 * @return
	 */
	public Integer operation(StoreInformationBo bo) {
		
		return storeInformationDao.operation(bo);
	}

	/**
	 * 单条查询
	 * @param po
	 * @return
	 */
	public StoreInformationPo getStoreByShopCode(StoreInformationPo po) {
		
		return storeInformationDao.getStoreByShopCode(po);
	}

	/**
	 * 根据编号查询关联仓库
	 * @param shopCode
	 * @return
	 */
	public List<WareHouseConfigurationPo> getWareHouseByShopCode(String shopCode) {
		
		return storeInformationDao.getWareHouseByShopCode(shopCode);
	}

	/**
	 * 修改时的待选仓库数据
	 * @param bo
	 * @return
	 */
	public List<WareHouseConfigurationPo> getWareHouseNotInRight(WareHouseConfigurationBo bo) {
		
		return storeInformationDao.getWareHouseNotInRight(bo);
	}

	/**
	 * 修改门店
	 * @param po
	 * @return
	 */
	public Integer updStore(StoreInformationPo po) {
		
		return storeInformationDao.updStore(po);
	}

	/**
	 * 	修改关联门店的方法
	 * @param shopCode
	 * @return
	 */
	public Integer removeStoreAndWare(String shopCode) {
		
		return storeInformationDao.removeStoreAndWare(shopCode);
	}

	/**
	 *  删除店铺的方法
	 * @param shopCode
	 * @return
	 */
	public Integer delStore(String shopCode) {
		
		return storeInformationDao.delStore(shopCode);
	}
}
