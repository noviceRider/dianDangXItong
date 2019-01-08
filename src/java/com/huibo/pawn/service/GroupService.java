package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.AttrGroupBo;
import com.huibo.pawn.dao.GroupDao;
import com.huibo.pawn.po.AttrGroupPo;
import com.huibo.pawn.po.ConfPo;
import com.huibo.pawn.po.ProductCatPo;

/**
 * <p>title:广沣共享典当管理系统-GroupService</p>
 * 
 * <p>Description:商品属性组的Service</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Service
public class GroupService {

	@Autowired
	private GroupDao groupDao ;

	/**
	 * 初始化加载数据
	 * @param bo
	 * @return
	 */
	public Map<String, Object> groupTable(AttrGroupBo bo) {
		
		Map<String,Object>map = new HashMap<String, Object>();

		Page page = new Page(bo);
		
		List<AttrGroupPo> list = groupDao.groupTable(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		
		return map;
	}

	/**
	 * 禁用、启用
	 * @param po
	 * @return
	 */
	public Integer changeGroupState(AttrGroupPo po) {
		
		return groupDao.changeGroupState(po);
	}

	/**
	 * 表单验证
	 * @param brandCode
	 * @return
	 */
	public Integer validatePriorityCode(String groupCode) {
		
		return groupDao.validatePriorityCode(groupCode);
	}

	/**
	 * 新增属性组
	 * @param po
	 * @return
	 */
	public Integer addGroup(AttrGroupPo po) {
		po.setGroupState("01");
		return groupDao.addGroup(po);
	}

	/**
	 * 新增属性组关联大类的方法
	 * @param catCode
	 * @param groupCode
	 * @return
	 */
	public Integer addAttrAndProd(String catCode, String groupCode) {
		String[] a = catCode.split(",");
		return groupDao.addAttrAndProd(a,groupCode);
	}

	/**
	 * 根据编号查询属性组数据
	 * @param groupCode
	 * @return
	 */
	public AttrGroupPo getAttrByGroupCode(String groupCode) {
		
		return groupDao.getAttrByGroupCode(groupCode);
	}

	/**
	 * 根据编号查询已选商品大类
	 * @param groupCode
	 * @return
	 */
	public List<ProductCatPo> getCommodityByGroupCode(String groupCode) {
	
		return groupDao.getCommodityByGroupCode(groupCode);
	}

	/**
	 * 修改属性组
	 * @param po
	 * @return
	 */
	public Integer updGroup(AttrGroupPo po) {
		
		return groupDao.updGroup(po);
	}

	/**
	 * 修改关联商品大类的方法
	 * @param groupCode
	 * @return
	 */
	public Integer removeGroupAndProd(String groupCode) {
		
		return groupDao.removeGroupAndProd(groupCode);
	}

	/**
	 * 删除属性组的方法
	 * @param groupCode
	 * @return
	 */
	public Integer delGroup(String groupCode) {
		List<ConfPo> list = groupDao.getAttrCode(groupCode) ;
		String attrCode = "";
		for (int i = 0; i < list.size(); i++) {
			if(i == list.size() - 1) {
				attrCode += list.get(i).getAttrCode() ;
			}else {
				attrCode += list.get(i).getAttrCode() + "," ;
			}
		}
		groupDao.delConf(attrCode) ;
		return  groupDao.delGroup(groupCode);
	}
}
