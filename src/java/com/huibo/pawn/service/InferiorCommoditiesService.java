package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.dao.InferiorCommoditiesDao;
import com.huibo.pawn.po.ProductCatPo;

/**
 * <p>title:广沣共享典当管理系统-InferiorCommoditiesService</p>
 * 
 * <p>Description:三级商品的Service</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Service
public class InferiorCommoditiesService {

	@Autowired
	private InferiorCommoditiesDao inferiorCommoditiesDao ;

	public Map<String, Object> inferiorCommoditiesTable(ProductCatBo bo) {
		Map<String,Object>map = new HashMap<String, Object>();

		Page page = new Page(bo);
		
		List<ProductCatPo> list = inferiorCommoditiesDao.inferiorCommoditiesTable(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		
		return map;
	}

	public Integer validatePriorityCode(ProductCatPo po) {
		
		return inferiorCommoditiesDao.validatePriorityCode(po);
	}

	/**
	 * 增加三级商品
	 * @param po
	 * @return
	 */
	public Integer addInferiorCommodities(ProductCatPo po) {
		po.setCatLvl(3);
		String pCatCode = po.getpCatCode() ;
		po.setCatRoute(pCatCode + "," + po.getCatCode());
		po.setIsShow("1");
		return inferiorCommoditiesDao.addInferiorCommodities(po);
	}

	/**
	 * 修改三级商品的方法
	 * @param po
	 * @return
	 */
	public Integer updInferiorCommodities(ProductCatPo po) {
		
		return inferiorCommoditiesDao.updInferiorCommodities(po);
	}

}
