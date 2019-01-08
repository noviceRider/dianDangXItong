package com.huibo.pawn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.StoreInformationBo;
import com.huibo.pawn.bo.WareHouseConfigurationBo;
import com.huibo.pawn.po.StoreInformationPo;
import com.huibo.pawn.po.WareHouseConfigurationPo;

/**
 * <p>title:广沣共享典当管理系统-StoreInformationDao</p>
 * 
 * <p>Description:门店管理的Dao</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public interface StoreInformationDao {

	//状态下拉的接口
	public List<StoreInformationPo>  getshopStateDropdown();

	//初始化加载数据接口
	public List<StoreInformationPo> getStore(Page page);

	//加载仓库面板数据接口
	public List<WareHouseConfigurationPo> getWareHouse(WareHouseConfigurationBo bo);

	//新增门店的接口
	public Integer addStore(StoreInformationPo po);
	
	//新增关联仓库的接口
	public Integer addStoreAndWhouse(@Param("a") String[] a,@Param("shopCode") String shopCode);

	//重复验证的接口
	public Integer validatePriorityCode(StoreInformationPo po);

	//禁用、启用
	public Integer operation(StoreInformationBo bo);

	//单条查询
	public StoreInformationPo getStoreByShopCode(StoreInformationPo po);

	//修改的已选仓库面板
	public List<WareHouseConfigurationPo> getWareHouseByShopCode(@Param("shopCode")String shopCode);

	//修改的待选仓库面板
	public List<WareHouseConfigurationPo> getWareHouseNotInRight(WareHouseConfigurationBo bo);

	//修改店面的接口
	public Integer updStore(StoreInformationPo po);

	//删除关联店面的接口
	public Integer removeStoreAndWare(@Param("shopCode")String shopCode);

	//删除店铺接口
	public Integer delStore(@Param("shopCode")String shopCode);

}
