package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ConfBo;
import com.huibo.pawn.dao.ConfDao;
import com.huibo.pawn.po.ConfPo;
/**
 * <p>title:广沣共享典当管理系统-ConfService</p>
 * 
 * <p>Description:商品属性的Service</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Service
public class ConfService {

	@Autowired
	private ConfDao confDao ;
	
	public Map<String, Object> confTable(ConfBo bo) {
		Map<String,Object> map = new HashMap<String, Object>();

		Page page = new Page(bo);
		
		List<ConfPo> list = confDao.confTable(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		return map;
	}

	/**
	 * 新增属性的方法
	 * @param po
	 * @return
	 */
	public Integer addConf(ConfPo po) {
		
		return confDao.addConf(po);
	}

	/**
	 * 编号验证
	 * @param po
	 * @return
	 */
	public Integer validatePriorityCode(ConfPo po) {
		
		return confDao.validatePriorityCode(po);
	}

	/**
	 * 根据编号获取信息
	 * @param attrCode
	 * @return
	 */
	public ConfPo getConfByAttrCode(String attrCode) {
		
		return confDao.getConfByAttrCode(attrCode);
	}

	/**
	 * 修改属性的方法
	 * @param po
	 * @return
	 */
	public Integer updConf(ConfPo po) {
		
		return confDao.updConf(po);
	}

	/**
	 * 删除属性的方法
	 * @param attrCode
	 * @return
	 */
	public Integer delConf(String attrCode) {
		
		return confDao.delConf(attrCode);
	}

}
