package com.huibo.pawn.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.po.ProductCatPo;

/**
 * <p>title:广沣共享典当管理系统-InferiorCommoditiesDao</p>
 * 
 * <p>Description:三级商品的Dao</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public interface InferiorCommoditiesDao {

	//初始化加载三级商品数据接口
	public List<ProductCatPo> inferiorCommoditiesTable(Page page);

	//表单验证的接口
	public Integer validatePriorityCode(ProductCatPo po);

	//新增三级商品的接口
	public Integer addInferiorCommodities(ProductCatPo po);

	//修改三级商品的接口
	public Integer updInferiorCommodities(ProductCatPo po);

	
}
