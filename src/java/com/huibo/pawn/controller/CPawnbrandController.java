package com.huibo.pawn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huibo.pawn.bo.CPawnProductCatBo;
import com.huibo.pawn.bo.CPawnbrandBo;
import com.huibo.pawn.bo.IPawnMemberInfoBo;
import com.huibo.pawn.service.CPawnbrandService;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - CPawnbrandController</p>
 *
 * <p>Description:品牌管理的Controller</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

@Controller
public class CPawnbrandController {

	@Autowired
	private CPawnbrandService cPawnbrandService;
	
	/**
	 * 加载数据网格和搜索
	 */
	@RequestMapping("/brandManagementSearch")
	public Map<String,Object> brandManagementSearch(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map = cPawnbrandService.brandManagementSearch(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 加载新增品牌界面中的待选商品大类的数据
	 */
	@RequestMapping("/loadProductCategories")
	public Map<String,Object> loadProductCategories(CPawnProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.loadProductCategories(bo)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增品牌
	 */
	@RequestMapping("/brandManagementSave")
	public Map<String,Object> brandManagementSave(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.brandManagementSave(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增品牌适用分类
	 */
	@RequestMapping("/brandClassification")
	public Map<String,Object> brandClassification(@RequestParam("arr[]") String[] arr,String brandCode){
		Map<String,Object> map = new HashMap<>();
		try {
			//System.out.println(map);
			map.put("rows", cPawnbrandService.brandClassification(arr,brandCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	/**
	 * 修改品牌信息填坑
	 */
	@RequestMapping("/modifyBrandManagement")
	public Map<String,Object> modifyBrandManagement(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.modifyBrandManagement(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除品牌
	 */
	@RequestMapping("/delBrandManagement")
	public Map<String,Object> delBrandManagement(@RequestParam("arr[]") String[] arr){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.delBrandManagement(arr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改显示状态
	 */
	@RequestMapping("/forbiddenBrandManagement")
	public Map<String,Object> forbiddenBrandManagement(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.forbiddenBrandManagement(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增品牌编号非重复验证
	 */
	@RequestMapping("/brandManagementValidation")
	public Map<String,Object> brandManagementValidation(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map = cPawnbrandService.brandManagementValidation(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增品牌名称非重复验证
	 */
	@RequestMapping("/addBrandNameVerification")
	public Map<String,Object> addBrandNameVerification(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map = cPawnbrandService.addBrandNameVerification(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改品牌
	 */
	@RequestMapping("/modifyBrandManagementSave")
	public Map<String,Object> modifyBrandManagementSave(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.modifyBrandManagementSave(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改品牌适用分类
	 */
	@RequestMapping("/modifyBrandClassification")
	public Map<String,Object> modifyBrandClassification(@RequestParam("arr[]") String[] arr,String brandCode){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.modifyBrandClassification(arr,brandCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 加载修改品牌界面中的适用商品大类的数据
	 */
	@RequestMapping("/modifyApplicableCommodities")
	public Map<String,Object> modifyApplicableCommodities(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.modifyApplicableCommodities(bo)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 加载修改品牌界面中的待选商品大类的数据
	 */
	@RequestMapping("/modifyLoadProductCategories")
	public Map<String,Object> modifyLoadProductCategories(CPawnProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", cPawnbrandService.modifyLoadProductCategories(bo)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改界面品牌名称验证
	 */
	@RequestMapping("/modifyBrandNameVerification")
	public Map<String,Object> modifyBrandNameVerification(String brandName,String keyWord){
		Map<String,Object> map = new HashMap<>();
		if(brandName.equals(keyWord)) {
			
		}else {
			try {
				map = cPawnbrandService.modifyBrandNameVerification(brandName,keyWord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
