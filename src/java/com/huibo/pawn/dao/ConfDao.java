package com.huibo.pawn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bn.javax.dao.Page;
import com.huibo.pawn.po.ConfPo;

/**
 * <p>title:广沣共享典当管理系统-ConfDao</p>
 * 
 * <p>Description:商品属性的Dao</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public interface ConfDao {

	//初始化加载数据接口
	public List<ConfPo> confTable(Page page);

	//新增属性的接口
	public Integer addConf(ConfPo po);

	//编号验证接口
	public Integer validatePriorityCode(ConfPo po);

	//根据编号获取信息的接口
	public ConfPo getConfByAttrCode(@Param("attrCode")String attrCode);

	//修改商品属性的接口
	public Integer updConf(ConfPo po);

	//删除属性的接口
	public Integer delConf(@Param("attrCode")String attrCode);

}
