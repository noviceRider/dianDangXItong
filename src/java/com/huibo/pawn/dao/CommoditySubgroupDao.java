package com.huibo.pawn.dao;

import java.util.List;
import com.bn.javax.dao.Page;
import com.huibo.pawn.po.ProductCatPo;

public interface CommoditySubgroupDao {

	//初始化加载商品小类数据接口
	public List<ProductCatPo> commoditySubgroupTable(Page page);

	//获取所有大类数据的接口
	public List<ProductCatPo> getPCatNameDropdown();

	//新增商品小类的接口
	public Integer addCommoditySubgroup(ProductCatPo po);

	//修改商品小类的接口
	public Integer updCommoditySubgroup(ProductCatPo po);

	//禁用和启用的接口
	public Integer conversion(ProductCatPo po);

}
