package com.huibo.pawn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huibo.pawn.bo.AssessmentBo;
import com.huibo.pawn.bo.AttrGroupBo;
import com.huibo.pawn.bo.CPawnProductCatBo;
import com.huibo.pawn.bo.CPawnbrandBo;
import com.huibo.pawn.bo.ConfBo;
import com.huibo.pawn.bo.GoodsAttrRelBo;
import com.huibo.pawn.bo.IPawnMemberInfoBo;
import com.huibo.pawn.bo.IdentificationOfRecordsBo;
import com.huibo.pawn.bo.IpawnGoodsBo;
import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.bo.StoreInformationBo;
import com.huibo.pawn.po.CPawnProductCatPo;
import com.huibo.pawn.po.CPawnbrandPo;
import com.huibo.pawn.po.PawnAttachPo;
import com.huibo.pawn.service.ProductListService;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - ProductListController</p>
 *
 * <p>Description:商品列表的Controller</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

@Controller
public class ProductListController {

	@Autowired
	private ProductListService productListService;
	
	/**
	 * 加载数据表格以及搜索
	 */
	@RequestMapping("/productListSearch")
	public Map<String,Object> productListSearch(IpawnGoodsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map = productListService.productListSearch(bo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(map);
		return map;
	}
	
	/**
	 * 加载分类一级下拉列表
	 */
	@RequestMapping("/classificationLevel")
	public Map<String,Object> classificationLevel(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.classificationLevel(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 加载分类二级下拉列表
	 */
	@RequestMapping("/classificationOfTheSecondary")
	public Map<String,Object> classificationOfTheSecondary(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.classificationOfTheSecondary(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 加载分类三级下拉列表
	 */
	@RequestMapping("/classificationOfThree")
	public Map<String,Object> classificationOfThree(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.classificationOfThree(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 属性分类下拉列表
	 */
	@RequestMapping("/attributeTypes")
	public Map<String,Object> attributeTypes(AttrGroupBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.attributeTypes(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品品牌下拉列表
	 */
	@RequestMapping("/commodityBrand")
	public Map<String,Object> commodityBrand(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.commodityBrand(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 来源门店下拉列表
	 */
	@RequestMapping("/sourceOfStores")
	public Map<String,Object> sourceOfStores(StoreInformationBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.sourceOfStores(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询属性
	 */
	@RequestMapping("/formattingProperties")
	public Map<String,Object> formattingProperties(ConfBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.formattingProperties(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询商品属性可选值
	 */
	@RequestMapping("/propertyDropDownList")
	public Map<String,Object> propertyDropDownList(ConfBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.propertyDropDownList(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品图片
	 */
	@RequestMapping("/commodityImages")
	public Map<String,Object> commodityImages(CPawnProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.commodityImages(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增商品信息
	 */
	@RequestMapping("/addGoodsForm")
	public Map<String,Object> addGoodsForm(IpawnGoodsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.addGoodsForm(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增商品属性
	 */
	@RequestMapping("/addProductAttributeForm")
	public Map<String,Object> addProductAttributeForm(@RequestParam("attrCode[]") String[] attrCode,String goodsId,@RequestParam("attrValue[]") String[] attrValue){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.addProductAttributeForm(attrCode,goodsId,attrValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改查询
	 */
	@RequestMapping("/modifyProductList")
	public Map<String,Object> modifyProductList(GoodsAttrRelBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyProductList(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delProductList")
	public Map<String,Object> delProductList(@RequestParam("arr[]") String[] arr){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows",productListService.delProductList(arr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arr);
		return map;
	}
	
	/**
	 * 提交
	 */
	@RequestMapping("/submitProductList")
	public Map<String,Object> submitProductList(IpawnGoodsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.submitProductList(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询商品属性
	 */
	@RequestMapping("/authenticateProductList")
	public Map<String,Object> authenticateProductList(IpawnGoodsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.authenticateProductList(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	} 
	
	/**
	 * 商品鉴定历史
	 */
	@RequestMapping("/historicalAppraisal")
	public Map<String,Object> historicalAppraisal(IdentificationOfRecordsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.historicalAppraisal(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 鉴定为正品
	 */
	@RequestMapping("/qualityGoods")
	public Map<String,Object> qualityGoods(IdentificationOfRecordsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.qualityGoods(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 鉴定为假货
	 */
	@RequestMapping("/fake")
	public Map<String,Object> fake(IdentificationOfRecordsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.fake(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 鉴定为资料不全
	 */
	@RequestMapping("/insufficientInformation")
	public Map<String,Object> insufficientInformation(IdentificationOfRecordsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.insufficientInformation(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品鉴定查询
	 */
	@RequestMapping("/assessProductList")
	public Map<String,Object> assessProductList(IdentificationOfRecordsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.assessProductList(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品估价
	 */
	@RequestMapping("/goodsAssessmentForm")
	public Map<String,Object> goodsAssessmentForm(AssessmentBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.goodsAssessmentForm(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品估价历史
	 */
	@RequestMapping("/assessmentRecords")
	public Map<String,Object> assessmentRecords(AssessmentBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.assessmentRecords(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增商品图片
	 */
	@RequestMapping("/addPicture")
	public Map<String,Object> addPicture(@RequestParam("upfile")MultipartFile mf ,String goodsId ,String resDesc){
		Map<String,Object> map = new HashMap<String,Object>();
		//查询最大附件编号
		Integer a = productListService.getPawnAttachCode();
		//创建附件对象
		PawnAttachPo pawnAttachPo = new PawnAttachPo() ;
		//设置附件编号
		pawnAttachPo.setResId(String.valueOf(a+1));
		//设置附件大小
		pawnAttachPo.setFileSize(mf.getSize());
		//设置附件类型
		pawnAttachPo.setMimeType(mf.getContentType());
		//设置附件描述
		pawnAttachPo.setResDesc(resDesc);
		//设置附件名称
		pawnAttachPo.setResFile(mf.getOriginalFilename());
		//新增附件
		int row = this.productListService.addPawnAttachPo(pawnAttachPo);
		if(row > 0) {
			//查询最大附件编号
			Integer b = productListService.getPawnAttachCode();
			//让附件和商品相关联
			productListService.addPawnAttach(b , goodsId) ; 
			try {
				mf.transferTo(new File("d:\\upload\\resource1\\"+pawnAttachPo.getResId()+pawnAttachPo.getResFile()));
				map.put("resId", pawnAttachPo.getResId());
				map.put("resFile", pawnAttachPo.getResFile());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 修改图片
	 */
	@RequestMapping("/modifyPicture")
	public Map<String,Object> modifyPicture(@RequestParam("upfile")MultipartFile mf ,String goodsId ,String resDesc,String resId){
		Map<String,Object> map = new HashMap<String,Object>();
		//创建附件对象
		PawnAttachPo pawnAttachPo = new PawnAttachPo() ;
		//设置附件大小
		pawnAttachPo.setFileSize(mf.getSize());
		//设置附件类型
		pawnAttachPo.setMimeType(mf.getContentType());
		//设置附件描述
		pawnAttachPo.setResDesc(resDesc);
		//设置附件ID
		pawnAttachPo.setResId(resId);
		//设置附件名称
		pawnAttachPo.setResFile(mf.getOriginalFilename());
		//新增附件
		int row = this.productListService.modifyPawnAttachPo(pawnAttachPo);
		if(row > 0) {
			//让附件和商品相关联
			productListService.modifyPawnAttach(resId , goodsId) ; 
			try {
				mf.transferTo(new File("d:\\upload\\resource1\\"+pawnAttachPo.getResId()+pawnAttachPo.getResFile()));
				map.put("resId", pawnAttachPo.getResId());
				map.put("resFile", pawnAttachPo.getResFile());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 	下载文件
	 * @param attachId
	 * @param attachFile
	 * @param resp
	 */
	@RequestMapping("/previewPicturesByGoodsId")
	public void filedown(String resId,String resFile,HttpServletResponse resp){
		Path path=Paths.get("D://upload//resource1//"+resId+resFile);
		File file=path.toFile();	
    	InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			byte[] bs = new byte[1024];
			int len=-1;
			resp.setContentType(Files.probeContentType(path));
			resFile=new String( resFile.getBytes("gb2312"), "ISO8859-1" );
			if(resFile.length()>150)//解决IE 6.0 bug
				resFile=new String(resFile.getBytes("GBK"),"ISO-8859-1");
			resp.setHeader("Content-Disposition", "attachement;filename="+resId+resFile);
			out = new BufferedOutputStream(resp.getOutputStream());
			while((len=in.read(bs))!=-1) {
				out.write(bs, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		 	try {
				in.close();
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} 	
		}		
	}
	
	/**
	 * 根据商品ID查询商品的信息
	 */
	@RequestMapping("/shopInfoSerach")
	public Map<String,Object> shopInfoSerach(IpawnGoodsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.shopInfoSerach(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改分类二级下拉列表
	 */
	@RequestMapping("/modifyClassificationOfTheSecondary")
	public Map<String,Object> modifyClassificationOfTheSecondary(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyClassificationOfTheSecondary(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改分类三级下拉列表
	 */
	@RequestMapping("/modifyClassificationOfThree")
	public Map<String,Object> modifyClassificationOfThree(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyClassificationOfThree(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改商品品牌下拉列表
	 */
	@RequestMapping("/modifyCommodityBrand")
	public Map<String,Object> modifyCommodityBrand(CPawnbrandBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyCommodityBrand(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改查询图片
	 */
	@RequestMapping("/queryImgs")
	public Map<String,Object> queryImgs(PawnAttachPo po){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.queryImgs(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除属性
	 */
	@RequestMapping("/delNature")
	public Map<String,Object> delNature(String goodsId){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows",productListService.delNature(goodsId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改属性
	 */
	@RequestMapping("/modifyProductAttributeForm")
	public Map<String,Object> modifyProductAttributeForm(@RequestParam("attrCode[]") String[] attrCode,String goodsId,@RequestParam("attrValue[]") String[] attrValue){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyProductAttributeForm(attrCode,goodsId,attrValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改商品信息
	 */
	@RequestMapping("/modifyGoodsForm")
	public Map<String,Object> modifyGoodsForm(IpawnGoodsBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyGoodsForm(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除图片
	 */
	@RequestMapping("/delImgs")
	public Map<String,Object> delImgs(String resId){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows",productListService.delImgs(resId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增商品编号验证
	 */
	@RequestMapping("/shopVerify")
	public Map<String,Object> shopVerify(IpawnGoodsBo bo){
		System.out.println(bo);
		Map<String,Object> map = new HashMap<>();
		try {
			map = productListService.shopVerify(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改查询分类一级编号
	 */
	@RequestMapping("/stairOne")
	public Map<String,Object> stairOne(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.stairOne(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改分类二级列表
	 */
	@RequestMapping("/modifyClassificationOfTheSecondaryTwo")
	public Map<String,Object> modifyClassificationOfTheSecondaryTwo(ProductCatBo bo){
		Map<String,Object> map = new HashMap<>();
		try {
			map.put("rows", productListService.modifyClassificationOfTheSecondaryTwo(bo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
