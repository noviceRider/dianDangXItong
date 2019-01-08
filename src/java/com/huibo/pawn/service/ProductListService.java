package com.huibo.pawn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.bn.javax.po.BasePo;
import com.huibo.pawn.bo.AssessmentBo;
import com.huibo.pawn.bo.AttrGroupBo;
import com.huibo.pawn.bo.CPawnProductCatBo;
import com.huibo.pawn.bo.CPawnbrandBo;
import com.huibo.pawn.bo.ConfBo;
import com.huibo.pawn.bo.GoodsAttrRelBo;
import com.huibo.pawn.bo.IdentificationOfRecordsBo;
import com.huibo.pawn.bo.IpawnGoodsBo;
import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.bo.StoreInformationBo;
import com.huibo.pawn.dao.ProductListDao;
import com.huibo.pawn.po.CPawnProductCatPo;
import com.huibo.pawn.po.CPawnbrandPo;
import com.huibo.pawn.po.IpawnGoodsPo;
import com.huibo.pawn.po.PawnAttachPo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - ProductListService</p>
 *
 * <p>Description:商品列表的Service</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

@Service
public class ProductListService {

	@Autowired
	private ProductListDao productListDao;

	/**
	 * 加载数据表格以及搜索
	 * @param bo
	 * @return
	 */
	public Map<String, Object> productListSearch(IpawnGoodsBo bo) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = new Page(bo);
		
		List<IpawnGoodsPo> list = productListDao.productListSearch(page);
		
		for (IpawnGoodsPo goodsPo : list) {
			goodsPo.setCatCode(goodsPo.getCatCode()+">"+goodsPo.getSubCatCode()+">" +goodsPo.getDetailCatCode());
		}
		map.put("rows", list);
		map.put("total", page.getTotalRecord());
		return map;
	}

	/**
	 * 加载分类一级下拉列表
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> classificationLevel(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.classificationLevel(bo);
	}

	/**
	 * 加载分类二级下拉列表
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> classificationOfTheSecondary(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.classificationOfTheSecondary(bo);
	}

	/**
	 * 加载分类三级下拉列表
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> classificationOfThree(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.classificationOfThree(bo);
	}

	/**
	 * 属性分类下拉列表
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> attributeTypes(AttrGroupBo bo) {
		// TODO Auto-generated method stub
		return productListDao.attributeTypes(bo);
	}

	/**
	 * 商品品牌下拉列表
	 * @param bo
	 * @return
	 */
	public List<CPawnbrandBo> commodityBrand(CPawnbrandBo bo) {
		// TODO Auto-generated method stub
		return productListDao.commodityBrand(bo);
	}

	/**
	 * 来源门店下拉列表
	 * @param bo
	 * @return
	 */
	public List<StoreInformationBo> sourceOfStores(StoreInformationBo bo) {
		// TODO Auto-generated method stub
		return productListDao.sourceOfStores(bo);
	}

	/**
	 * 查询属性
	 * @param bo
	 * @return
	 */
	public List<ConfBo> formattingProperties(ConfBo bo) {
		// TODO Auto-generated method stub
		return productListDao.formattingProperties(bo);
	}

	/**
	 * 查询商品属性可选值
	 * @param bo
	 * @return
	 */
	public List<ConfBo> propertyDropDownList(ConfBo bo) {
		// TODO Auto-generated method stub
		return productListDao.propertyDropDownList(bo);
	}

	/**
	 * 商品图片
	 * @param bo
	 * @return
	 */
	public List<CPawnProductCatBo> commodityImages(CPawnProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.commodityImages(bo);
	}

	/**
	 * 新增商品信息
	 * @param bo
	 * @return
	 */
	public Object addGoodsForm(IpawnGoodsBo bo) {
		// TODO Auto-generated method stub
		//bo.setInputUser(CommonFunction.getUserFromSession().getUserId());
		return productListDao.addGoodsForm(bo);
	}

	/**
	 * 新增商品属性
	 * @param bo
	 * @return
	 */
	public Integer addProductAttributeForm(String[] attrCode,String goodsId,String[] attrValue) {
		// TODO Auto-generated method stub
		System.out.println(attrCode.length);
		System.out.println(attrCode[1].toString());
		for(int i = 0;i < attrCode.length; i++) {
			productListDao.addProductAttributeForm(attrCode[i],goodsId,attrValue[i]);
		}
		return 1;
	}

	/**
	 * 修改查询
	 * @param bo
	 * @return
	 */
	public List<GoodsAttrRelBo> modifyProductList(GoodsAttrRelBo bo) {
		// TODO Auto-generated method stub
		return productListDao.modifyProductList(bo);
	}

	/**
	 * 删除
	 * @param arr
	 * @return
	 */
	public Integer delProductList(String[] arr) {
		// TODO Auto-generated method stub
		//删除商品的图片信息
		productListDao.delProductListImg(arr);
		//删除商品的鉴定和评估记录
		productListDao.delLog(arr);
		return productListDao.delProductList(arr);
	}

	/**
	 * 上传附件图标
	 * @param pawnAttachPo
	 * @return
	 */
	public Integer addPawnAttachPoImg(PawnAttachPo pawnAttachPo) {
		
		return productListDao.addPawnAttachPoImg(pawnAttachPo);
	}

	/**
	 * 查找附件表的最大编号
	 * @return
	 */
	public Integer getPawnAttachCodeImg() {
		
		return productListDao.getPawnAttachCodeImg();
	}

	/**
	 * 让附件和商品大类相关联
	 * @return
	 */
	public Integer addPawnAttachImg(String resId , String catCode) {
		
		return productListDao.addPawnAttachImg(resId , catCode);
	}

	/**
	 * 提交
	 * @param goodsId
	 * @return
	 */
	public Integer submitProductList(IpawnGoodsBo bo) {
		// TODO Auto-generated method stub
		bo.setInputUser(CommonFunction.getUserFromSession().getUserId());
		return productListDao.submitProductList(bo);
	}

	/**
	 * 查询商品属性
	 * @param bo
	 * @return
	 */
	public List<IpawnGoodsBo> authenticateProductList(IpawnGoodsBo bo) {
		// TODO Auto-generated method stub
		return productListDao.authenticateProductList(bo);
	}

	/**
	 * 商品历史鉴定
	 * @param goodsId
	 * @return
	 */
	public List<IdentificationOfRecordsBo> historicalAppraisal(IdentificationOfRecordsBo bo) {
		return productListDao.historicalAppraisal(bo);
	}

	/**
	 * 鉴定为正品
	 * @param bo
	 * @return
	 */
	public Integer qualityGoods(IdentificationOfRecordsBo bo) {
		// TODO Auto-generated method stub
		//注入创建人
		bo.setCreateBy(CommonFunction.getUserFromSession().getUserId());
		//将商品的状态变为待评估以及修改鉴定人
		productListDao.upGoodsStat(bo);
		return productListDao.qualityGoods(bo);
	}

	/**
	 * 鉴定为假货
	 * @param bo
	 * @return
	 */
	public Integer fake(IdentificationOfRecordsBo bo) {
		//注入创建人
		bo.setCreateBy(CommonFunction.getUserFromSession().getUserId());
		//将商品的状态变为待评估以及修改鉴定人
		productListDao.upGoodsStat(bo);
		return productListDao.fake(bo);
	}

	/**
	 * 鉴定为资料不全
	 * @param bo
	 * @return
	 */
	public Integer insufficientInformation(IdentificationOfRecordsBo bo) {
		//注入创建人
		bo.setCreateBy(CommonFunction.getUserFromSession().getUserId());
		//将商品的状态变为待评估以及修改鉴定人
		productListDao.upGoodsStat(bo);
		return productListDao.insufficientInformation(bo);
	}

	/**
	 * 商品鉴定查询
	 * @param bo
	 * @return
	 */
	public List<IdentificationOfRecordsBo> assessProductList(IdentificationOfRecordsBo bo) {
		// TODO Auto-generated method stub
		return productListDao.assessProductList(bo);
	}

	/**
	 * 商品估价
	 * @param bo
	 * @return
	 */
	public Integer goodsAssessmentForm(AssessmentBo bo) {
		// TODO Auto-generated method stub
		//注入创建人
		bo.setCreateBy(CommonFunction.getUserFromSession().getUserId());
		//将商品状态改为已估价以及修改评估人
		productListDao.evaluate(bo);
		return productListDao.goodsAssessmentForm(bo);
	}

	/**
	 * 商品估价历史
	 * @param bo
	 * @return
	 */
	public List<AssessmentBo> assessmentRecords(AssessmentBo bo) {
		// TODO Auto-generated method stub
		return productListDao.assessmentRecords(bo);
	}
	
	/**
	 * 查询最大附件编号
	 * @return
	 */
	public Integer getPawnAttachCode() {
		
		return productListDao.getPawnAttachCode();
	}
	
	/**
	 * 新增附件的方法
	 * @param pawnAttachPo
	 * @return
	 */
	public Integer addPawnAttachPo(PawnAttachPo pawnAttachPo) {
		
		return productListDao.addPawnAttachPo(pawnAttachPo);
	}
	
	/**
	 * 让附件和商品相关联
	 * @param b
	 * @param goodsId
	 */
	public void addPawnAttach(Integer b, String goodsId) {
		
		productListDao.addPawnAttach(b , goodsId) ;
	}

	/**
	 * 根据商品ID查询商品的信息
	 * @param bo
	 * @return
	 */
	public List<IpawnGoodsBo> shopInfoSerach(IpawnGoodsBo bo) {
		// TODO Auto-generated method stub
		return productListDao.shopInfoSerach(bo);
	}

	/**
	 * 修改分类二级下拉列表
	 * @param bo
	 * @return
	 */
	public Integer modifyClassificationOfTheSecondary(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.modifyClassificationOfTheSecondary(bo);
	}

	/**
	 * 修改分类三级下拉列表
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> modifyClassificationOfThree(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.modifyClassificationOfThree(bo);
	}

	/**
	 * 修改商品品牌下拉列表
	 * @param bo
	 * @return
	 */
	public List<CPawnbrandBo> modifyCommodityBrand(CPawnbrandBo bo) {
		// TODO Auto-generated method stub
		return productListDao.modifyCommodityBrand(bo);
	}

	/**
	 * 修改查询图片
	 * @param po
	 * @return
	 */
	public List<PawnAttachPo> queryImgs(PawnAttachPo po) {
		// TODO Auto-generated method stub
		return productListDao.queryImgs(po);
	}

	/**
	 * 修改属性
	 * @param attrCode
	 * @param goodsId
	 * @param attrValue
	 * @return
	 */
	public Integer modifyProductAttributeForm(String[] attrCode, String goodsId, String[] attrValue) {
		System.out.println(attrCode.length);
		System.out.println(attrCode[1].toString());
		for(int i = 0;i < attrCode.length; i++) {
			productListDao.modifyProductAttributeForm(attrCode[i],goodsId,attrValue[i]);
		}
		return 1;
	}

	/**
	 * 删除属性
	 * @param goodsId
	 * @return
	 */
	public Integer delNature(String goodsId) {
		// TODO Auto-generated method stub
		return productListDao.delNature(goodsId);
	}

	/**
	 * 修改商品信息
	 * @param bo
	 * @return
	 */
	public Integer modifyGoodsForm(IpawnGoodsBo bo) {
		// TODO Auto-generated method stub
		return productListDao.modifyGoodsForm(bo);
	}

	/**
	 * 删除图片
	 * @param goodsId
	 * @return
	 */
	public Integer delImgs(String resId) {
		// TODO Auto-generated method stub
		return productListDao.delImgs(resId);
	}

	/**
	 * 修改图片
	 * @param pawnAttachPo
	 * @return
	 */
	public Integer modifyPawnAttachPo(PawnAttachPo pawnAttachPo) {
		// TODO Auto-generated method stub
		return productListDao.modifyPawnAttachPo(pawnAttachPo);
	}

	/**
	 * 修改让附件和商品相关联
	 * @param resId
	 * @param goodsId
	 */
	public void modifyPawnAttach(String resId, String goodsId) {
		// TODO Auto-generated method stub
		productListDao.modifyPawnAttach(resId , goodsId) ;
	}

	/**
	 * 新增商品编号验证
	 * @param bo
	 * @return
	 */
	public Map<String, Object> shopVerify(IpawnGoodsBo bo) {
		int shopNumber = 0;
		Map<String,Object> map = new HashMap<>();
		Integer count = productListDao.shopVerify(bo);
		System.out.println(count);
		if(count == 0) {
			shopNumber = 1;
		}
		map.put("resultCode", shopNumber);
		return map;
	}

	/**
	 * 修改查询分类一级编号
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> stairOne(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.stairOne(bo);
	}

	/**
	 * 修改分类二级列表
	 * @param bo
	 * @return
	 */
	public List<ProductCatBo> modifyClassificationOfTheSecondaryTwo(ProductCatBo bo) {
		// TODO Auto-generated method stub
		return productListDao.modifyClassificationOfTheSecondaryTwo(bo);
	}
}
