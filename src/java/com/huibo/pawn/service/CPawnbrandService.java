package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.CPawnProductCatBo;
import com.huibo.pawn.bo.CPawnbrandBo;
import com.huibo.pawn.dao.CPawnbrandDao;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - CPawnbrandService</p>
 *
 * <p>Description:品牌管理的Service</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

@Service
public class CPawnbrandService {

	@Autowired
	private CPawnbrandDao cPawnbrandDao;
	/**
	 * 加载数据网格和搜索
	 * @param bo
	 * @return
	 */
	public Map<String,Object> brandManagementSearch(CPawnbrandBo bo) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = new Page(bo);
		map.put("rows", cPawnbrandDao.brandManagementSearch(page));
		map.put("total", page.getTotalRecord());
		return map;
	}
	
	/**
	 * 加载新增品牌界面中的待选商品大类的数据
	 * @param bo
	 * @return
	 */
	public List<CPawnProductCatBo> loadProductCategories(CPawnProductCatBo bo) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.loadProductCategories(bo);
	}

	/**
	 * 新增品牌
	 * @return
	 */
	public Integer brandManagementSave(CPawnbrandBo bo) {
		Map<String,Object> map = new HashMap<>();
		//新增品牌适用分类
		//map.put("rows", cPawnbrandDao.brandClassification(bo));
		return cPawnbrandDao.brandManagementSave(bo);
	}
	
	/**
	 * 新增品牌适用分类
	 * @param arr
	 * @return
	 */
	public Integer brandClassification(String[] arr,String brandCode) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.brandClassification(arr,brandCode);
	}

	/**
	 * 修改品牌信息填坑
	 */
	public List<CPawnbrandBo> modifyBrandManagement(CPawnbrandBo bo) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.modifyBrandManagement(bo);
	}

	/**
	 * 删除品牌
	 * @param arr
	 * @return
	 */
	public Integer delBrandManagement(String[] arr) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.delBrandManagement(arr);
	}

	/**
	 * 修改显示状态
	 * @param bo
	 * @return
	 */
	public Integer forbiddenBrandManagement(CPawnbrandBo bo) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.forbiddenBrandManagement(bo);
	}

	/**
	 * 新增品牌编号非重复验证
	 * @param bo
	 * @return
	 */
	public Map<String, Object> brandManagementValidation(CPawnbrandBo bo) {
		int  brandManagementNumber = 0;
		Map<String,Object> map = new HashMap<>();
		Integer count = cPawnbrandDao.brandManagementValidation(bo);
		if(count == 0) {
			brandManagementNumber = 1;
		}
		map.put("resultCode", brandManagementNumber);
		return map;
	}

	/**
	 * 修改品牌
	 * @param bo
	 * @return
	 */
	public Integer modifyBrandManagementSave(CPawnbrandBo bo) {
		Map<String,Object> map = new HashMap<>();
		//删除品牌适用分类
		map.put("rows", cPawnbrandDao.delbrandClassification(bo));
		//新增品牌适用分类
		//map.put("rows", cPawnbrandDao.brandClassification(bo));
		return cPawnbrandDao.modifyBrandManagementSave(bo);
	}

	/**
	 * 修改品牌适用分类
	 * @param arr
	 * @param brandCode
	 * @return
	 */
	public Integer modifyBrandClassification(String[] arr, String brandCode) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.modifyBrandClassification(arr,brandCode);
	}

	/**
	 * 加载修改品牌界面中的适用商品大类的数据
	 * @param bo
	 * @return
	 */
	public Object modifyApplicableCommodities(CPawnbrandBo bo) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.modifyApplicableCommodities(bo);
	}

	/**
	 * 加载修改品牌界面中的待选商品大类的数据
	 * @param bo
	 * @return
	 */
	public Object modifyLoadProductCategories(CPawnProductCatBo bo) {
		// TODO Auto-generated method stub
		return cPawnbrandDao.modifyLoadProductCategories(bo);
	}

	/**
	 *新增品牌名称非重复验证
	 * @param bo
	 * @return
	 */
	public Map<String, Object> addBrandNameVerification(CPawnbrandBo bo) {
		int  brandManagementNumber = 0;
		Map<String,Object> map = new HashMap<>();
		Integer count = cPawnbrandDao.addBrandNameVerification(bo);
		if(count == 0) {
			brandManagementNumber = 1;
		}
		map.put("resultCode", brandManagementNumber);
		return map;
	}

	/**
	 * 修改界面品牌名称验证
	 * @param brandName
	 * @param keyWord
	 * @return
	 */
	public Map<String, Object> modifyBrandNameVerification(String brandName, String keyWord) {
		int  brandManagementNumber = 0;
		Map<String,Object> map = new HashMap<>();
		Integer count = cPawnbrandDao.modifyBrandNameVerification(brandName,keyWord);
		if(count == 0) {
			brandManagementNumber = 1;
		}
		map.put("resultCode", brandManagementNumber);
		return map;
	}
	
}
