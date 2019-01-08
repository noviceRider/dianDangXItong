package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.dao.CommoditySubgroupDao;
import com.huibo.pawn.po.ProductCatPo;

/**
 * <p>title:广沣共享典当管理系统-commoditySubgroupController</p>
 * 
 * <p>Description:商品小类的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Service
public class CommoditySubgroupService {
	
	@Autowired
	private CommoditySubgroupDao commoditySubgroupDao ;

	/**
	 * 初始化加载数据
	 * @param bo
	 * @return
	 */
	public Map<String, Object> commoditySubgroupTable(ProductCatBo bo) {
		
		Map<String,Object>map = new HashMap<String, Object>();

		Page page = new Page(bo);
		
		List<ProductCatPo> list = commoditySubgroupDao.commoditySubgroupTable(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		
		return map;
	}

	/**
	 * 获取所有大类的数据
	 * @return
	 */
	public List<ProductCatPo> getPCatNameDropdown() {
		
		return commoditySubgroupDao.getPCatNameDropdown();
	}

	/**
	 * 新增商品小类
	 * @param po
	 * @return
	 */
	public Integer addCommoditySubgroup(ProductCatPo po) {
		String pCatCode = po.getpCatCode() ;
		po.setCatLvl(2);
		po.setCatRoute(pCatCode + "," + po.getCatCode());
		po.setIsShow("1");
		return commoditySubgroupDao.addCommoditySubgroup(po);
	}

	/**
	 * 修改商品小类的方法
	 * @param po
	 * @return
	 */
	public Integer updCommoditySubgroup(ProductCatPo po) {
		String pCatCode = po.getpCatCode() ;
		po.setCatRoute(pCatCode + "," + po.getCatCode());
		return commoditySubgroupDao.updCommoditySubgroup(po);
	}

	/**
	 * 禁用 和 启用
	 * @param po
	 * @return
	 */
	public Integer conversion(ProductCatPo po) {
		
		return commoditySubgroupDao.conversion(po);
	}
}
