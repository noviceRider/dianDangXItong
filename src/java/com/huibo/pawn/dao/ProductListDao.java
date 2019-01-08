package com.huibo.pawn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.javax.dao.Page;
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
import com.huibo.pawn.po.CPawnProductCatPo;
import com.huibo.pawn.po.CPawnbrandPo;
import com.huibo.pawn.po.IpawnGoodsPo;
import com.huibo.pawn.po.PawnAttachPo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - ProductListDao</p>
 *
 * <p>Description:商品列表的Dao</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public interface ProductListDao {

	//加载数据表格以及搜索
	public List<IpawnGoodsPo> productListSearch(Page page);

	//加载分类一级下拉列表
	public List<ProductCatBo> classificationLevel(ProductCatBo bo);

	//加载分类二级下拉列表
	public List<ProductCatBo> classificationOfTheSecondary(ProductCatBo bo);

	//加载分类三级下拉列表
	public List<ProductCatBo> classificationOfThree(ProductCatBo bo);

	//属性分类下拉列表
	public List<ProductCatBo> attributeTypes(AttrGroupBo bo);

	//商品品牌下拉列表
	public List<CPawnbrandBo> commodityBrand(CPawnbrandBo bo);

	//来源门店下拉列表
	public List<StoreInformationBo> sourceOfStores(StoreInformationBo bo);

	//查询属性
	public List<ConfBo> formattingProperties(ConfBo bo);

	//查询商品属性可选值
	public List<ConfBo> propertyDropDownList(ConfBo bo);

	//商品图片
	public List<CPawnProductCatBo> commodityImages(CPawnProductCatBo bo);

	//新增商品信息
	public Integer addGoodsForm(IpawnGoodsBo bo);

	//修改查询
	public List<GoodsAttrRelBo> modifyProductList(GoodsAttrRelBo bo);

	//新增商品属性
	public Integer addProductAttributeForm(@Param("attrCode") String attrCode,@Param("goodsId") String goodsId,@Param("attrValue") String attrValue);

	//删除
	public Integer delProductList(String[] arr);
	
	//删除商品的图片信息
	public void delProductListImg(String[] arr);
	
	//删除商品的鉴定和评估记录
	public void delLog(String[] arr);

	//上传商品的图标
	public Integer addPawnAttachPoImg(PawnAttachPo pawnAttachPo);

	//查找附件做大的编号
	public Integer getPawnAttachCodeImg();

	//让附件和商品相关联
	public Integer addPawnAttachImg(@Param("resId") String resId , @Param("catCode")  String catCode);

	//提交
	public Integer submitProductList(IpawnGoodsBo goodsId);

	//查询商品属性
	public List<IpawnGoodsBo> authenticateProductList(IpawnGoodsBo bo);

	//商品历史鉴定
	public List<IdentificationOfRecordsBo> historicalAppraisal(IdentificationOfRecordsBo bo);

	//鉴定为正品
	public Integer qualityGoods(IdentificationOfRecordsBo bo);

	//鉴定为假货
	public Integer fake(IdentificationOfRecordsBo bo);

	//鉴定为资料不全
	public Integer insufficientInformation(IdentificationOfRecordsBo bo);

	//将商品的装填改为待评价
	public void upGoodsStat(IdentificationOfRecordsBo bo);

	//商品鉴定查询
	public List<IdentificationOfRecordsBo> assessProductList(IdentificationOfRecordsBo bo);

	//商品估价
	public Integer goodsAssessmentForm(AssessmentBo bo);

	//将商品状态改为已估价
	public void evaluate(AssessmentBo bo);

	//商品估价历史
	public List<AssessmentBo> assessmentRecords(AssessmentBo bo);

	//查询最大附件编号
	public Integer getPawnAttachCode();
	
	//新增商品图片
	public Integer addPawnAttachPo(PawnAttachPo pawnAttachPo);
	
	//让商品图片和附件相关联
	public void addPawnAttach(@Param("b")Integer b, @Param("goodsId")String goodsId);

	//根据商品ID查询商品的信息
	public List<IpawnGoodsBo> shopInfoSerach(IpawnGoodsBo bo);

	//修改分类二级下拉列表
	public Integer modifyClassificationOfTheSecondary(ProductCatBo bo);

	//修改分类三级下拉列表
	public List<ProductCatBo> modifyClassificationOfThree(ProductCatBo bo);

	//修改商品品牌下拉列表
	public List<CPawnbrandBo> modifyCommodityBrand(CPawnbrandBo bo);

	//修改查询图片
	public List<PawnAttachPo> queryImgs(PawnAttachPo po);

	//根据商品ID删除属性
	public Integer delNature(@Param("goodsId") String goodsId);
	
	//修改属性
	public Integer modifyProductAttributeForm(@Param("attrCode") String attrCode,@Param("goodsId") String goodsId,@Param("attrValue") String attrValue);

	//修改商品信息
	public Integer modifyGoodsForm(IpawnGoodsBo bo);

	//删除图片
	public Integer delImgs(@Param("resId") String resId);

	//修改图片
	public Integer modifyPawnAttachPo(PawnAttachPo pawnAttachPo);

	//修改让附件和商品相关联
	public void modifyPawnAttach(@Param("resId") String resId,@Param("goodsId") String goodsId);

	//新增商品编号验证
	public Integer shopVerify(IpawnGoodsBo bo);

	//修改查询分类一级编号
	public List<ProductCatBo> stairOne(ProductCatBo bo);

	//修改分类二级列表
	public List<ProductCatBo> modifyClassificationOfTheSecondaryTwo(ProductCatBo bo);

	

	

}
