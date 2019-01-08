package com.huibo.pawn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bn.javax.dao.Page;
import com.huibo.pawn.po.PawnAttachPo;
import com.huibo.pawn.po.ProductCatPo;
/**
 * <p>title:广沣共享典当管理系统-ProductCatDao</p>
 * 
 * <p>Description:商品大类的Dao</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public interface ProductCatDao {

	//初始化加载数据的接口
	public List<ProductCatPo> getCommodity(Page page) ;

	//增加商品大类的接口
	public Integer addProductCat(ProductCatPo po);

	//上传商品大类的图标
	public Integer addPawnAttachPo(PawnAttachPo pawnAttachPo);

	//查找附件做大的编号
	public Integer getPawnAttachCode();

	//让附件和商品大类相关联
	public Integer addPawnAttach(@Param("resId") String resId , @Param("catCode")  String catCode);

	//根据编号查询信息接口
	public ProductCatPo getProductCatByCatCode(@Param("catCode")String catCode);

	//根据编号查询附件接口
	public PawnAttachPo getPawnAttach(@Param("catCode")String catCode);

	//修改附件和商品大类相关联数据
	public Integer updPawnAttachByCatCode(@Param("resId") String resId , @Param("catCode")  String catCode);

	//修改商品大类的接口
	public Integer updProductCat(ProductCatPo po);

	//删除附件和商品大类相关联
	public Integer removePawnAttachByCatCode(@Param("catCode")String catCode);

	//根据编号获取上级分类编号
	public List<ProductCatPo> getPCatCodeByCatCodeABC(@Param("catCode")String catCode);

	//删除商品大类的接口
	public Integer removeProductCatByCatCode(@Param("pCatCode") String pCatCode);

	//表达验证之非重验证接口
	public Integer validatePriorityCode(@Param("catCode")String catCode);


}
