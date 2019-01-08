package com.huibo.pawn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bn.javax.dao.Page;
import com.huibo.pawn.po.AttrGroupPo;
import com.huibo.pawn.po.ConfPo;
import com.huibo.pawn.po.ProductCatPo;

/**
 * <p>title:广沣共享典当管理系统-GroupDao</p>
 * 
 * <p>Description:商品属性组的Dao</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public interface GroupDao {

	//初始化加载数据的接口
	public List<AttrGroupPo> groupTable(Page page);

	//禁用、启用接口
	public Integer changeGroupState(AttrGroupPo po);

	//验证编号是否重复接口
	public Integer validatePriorityCode(@Param("groupCode")String groupCode);

	//新增属性组的接口
	public Integer addGroup(AttrGroupPo po);

	//新增属性组关联大类的方法
	public Integer addAttrAndProd(@Param("a")String[] a, @Param("groupCode")String groupCode);

	//根据编号查询数据
	public AttrGroupPo getAttrByGroupCode(@Param("groupCode")String groupCode);

	//根据编号查询已选商品大类
	public List<ProductCatPo> getCommodityByGroupCode(@Param("groupCode")String groupCode);

	//修改属性组的接口
	public Integer updGroup(AttrGroupPo po);

	//修改关联商品大类的接口
	public Integer removeGroupAndProd(@Param("groupCode")String groupCode);

	//删除属性组的方法
	public Integer delGroup(@Param("groupCode")String groupCode);

	//获取属性组下属性的编号
	public List<ConfPo> getAttrCode(@Param("groupCode")String groupCode);

	//删除属性的接口
	public void delConf(@Param("attrCode")String attrCode);

}
