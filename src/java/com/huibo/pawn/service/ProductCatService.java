package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.dao.ProductCatDao;
import com.huibo.pawn.po.PawnAttachPo;
import com.huibo.pawn.po.ProductCatPo;
/**
 * <p>title:广沣共享典当管理系统-ProductCatService</p>
 * 
 * <p>Description:商品大类的Service</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Service
public class ProductCatService {

	@Autowired
	private  ProductCatDao  productCatDao ;

	/**
	 * 初始化加载数据
	 * @param bo
	 * @return
	 */
	public Map<String, Object> getCommodity(ProductCatBo bo) {
		
		Map<String,Object>map = new HashMap<String, Object>();

		Page page = new Page(bo);
		
		List<ProductCatPo> list = productCatDao.getCommodity(page);
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		
		return map;
	}

	/**
	 * 增加商品大类的方法
	 * @param po
	 * @return
	 */
	public Integer addProductCat(ProductCatPo po) {
		po.setCatLvl(1);
		po.setpCatCode("0");
		po.setIsShow("1");
		return productCatDao.addProductCat(po);
	}

	/**
	 * 上传附件图标
	 * @param pawnAttachPo
	 * @return
	 */
	public Integer addPawnAttachPo(PawnAttachPo pawnAttachPo) {
		
		return productCatDao.addPawnAttachPo(pawnAttachPo);
	}

	/**
	 * 查找附件表的最大编号
	 * @return
	 */
	public Integer getPawnAttachCode() {
		
		return productCatDao.getPawnAttachCode();
	}

	/**
	 * 让附件和商品大类相关联
	 * @return
	 */
	public Integer addPawnAttach(String resId , String catCode) {
		
		return productCatDao.addPawnAttach(resId , catCode);
	}

	/**
	 * 根据编号查询
	 * @param catCode
	 * @return
	 */
	public ProductCatPo getProductCatByCatCode(String catCode) {
		
		return productCatDao.getProductCatByCatCode(catCode);
	}

	/**
	 * 根据编号查询附件
	 * @param catCode
	 * @return
	 */
	public PawnAttachPo getPawnAttach(String catCode) {
		
		return productCatDao.getPawnAttach(catCode);
	}

	/**
	 * 修改附件和商品大类相关联数据
	 * @return
	 */
	public Integer updPawnAttachByCatCode(String resId, String catCode) {
		
		return productCatDao.updPawnAttachByCatCode(resId , catCode);
	}

	/**
	 * 修改商品大类的方法
	 * @param po
	 * @return
	 */
	public Integer updProductCat(ProductCatPo po) {
		
		return productCatDao.updProductCat(po);
	}

	/**
	 * 删除附件和商品大类相关联数据
	 * @param catCode
	 * @return
	 */
	public Integer removePawnAttachByCatCode(String catCode) {
		
		return productCatDao.removePawnAttachByCatCode(catCode);
	}

	/**
	 * 删除商品大类的方法
	 * @param catCode
	 * @return
	 */
	public Integer removeProductCatByCatCode(String catCode) {
		
		List<ProductCatPo> list = productCatDao.getPCatCodeByCatCodeABC(catCode) ;
		String pCatCode = "";
		for (int i = 0; i < list.size(); i++) {
			if(i == list.size() - 1) {
				pCatCode += list.get(i).getCatCode() ;
			}else {
				pCatCode += list.get(i).getCatCode() + "," ;
			}
		}
		return productCatDao.removeProductCatByCatCode(pCatCode);
	}

	/**
	 * 表单验证的方法
	 * @param catCode
	 * @return
	 */
	public Integer validatePriorityCode(String catCode) {
		
		return productCatDao.validatePriorityCode(catCode);
	}
}
