package com.huibo.pawn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bn.javax.dao.Page;
import com.huibo.pawn.bo.CPawnProductCatBo;
import com.huibo.pawn.bo.CPawnbrandBo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - CPawnbrandDao</p>
 *
 * <p>Description:品牌管理的Dao</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public interface CPawnbrandDao {

	//加载数据网格和搜索
	public List<CPawnbrandBo> brandManagementSearch(Page page);

	//加载新增品牌界面中的待选商品大类的数据
	public List<CPawnProductCatBo> loadProductCategories(CPawnProductCatBo bo);

	//新增品牌
	public Integer brandManagementSave(CPawnbrandBo bo);

	//新增品牌适用分类
	public Integer brandClassification(@Param("arr") String[] arr,@Param("brandCode") String brandCode);

	///修改品牌信息填坑
	public List<CPawnbrandBo> modifyBrandManagement(CPawnbrandBo bo);

	//删除品牌
	public Integer delBrandManagement(String[] arr);

	//修改显示状态
	public Integer forbiddenBrandManagement(CPawnbrandBo bo);

	//新增品牌编号非重复验证
	public Integer brandManagementValidation(CPawnbrandBo bo);

	//删除品牌适用分类
	public Integer delbrandClassification(CPawnbrandBo bo);

	//修改品牌
	public Integer modifyBrandManagementSave(CPawnbrandBo bo);

	//修改品牌适用分类
	public Integer modifyBrandClassification(String[] arr, String brandCode);

	//加载修改品牌界面中的适用商品大类的数据
	public List<CPawnbrandBo> modifyApplicableCommodities(CPawnbrandBo bo);

	//加载修改品牌界面中的待选商品大类的数据
	public List<CPawnProductCatBo> modifyLoadProductCategories(CPawnProductCatBo bo);

	//新增品牌名称非重复验证
	public Integer addBrandNameVerification(CPawnbrandBo bo);

	//修改界面品牌名称验证
	public Integer modifyBrandNameVerification(@Param("brandName") String brandName, @Param("keyWord") String keyWord);

}
