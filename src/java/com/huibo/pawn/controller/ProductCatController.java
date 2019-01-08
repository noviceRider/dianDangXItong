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

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huibo.pawn.bo.ProductCatBo;
import com.huibo.pawn.po.PawnAttachPo;
import com.huibo.pawn.po.ProductCatPo;
import com.huibo.pawn.service.ProductCatService;
/**
 * <p>title:广沣共享典当管理系统-ProductCatController</p>
 * 
 * <p>Description:商品大类的Controller</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
@Controller
public class ProductCatController {

	@Autowired
	private  ProductCatService productCatService ;
	
	/**
	 * 	初始化加载数据
	 * @return
	 */
	@RequestMapping("/getCommodity")
	public Map<String,Object> getCommodity(ProductCatBo bo){
	Map<String, Object> map = null ;
		
		try {
			map = productCatService.getCommodity(bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 新增商品大类的方法
	 * @return
	 */
	@RequestMapping("/addProductCat")
	public Map<String,Object> addProductCat(ProductCatPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.addProductCat(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *  上传分类图标的方法
	 */
	@RequestMapping("/addHeadImg")
	public Map<String,Object> addHeadImg(@RequestParam("upfile")MultipartFile mf){
		Map<String,Object> map = new HashMap<String,Object>();
		Integer a = productCatService.getPawnAttachCode();
		PawnAttachPo pawnAttachPo = new PawnAttachPo() ;
		pawnAttachPo.setResId(String.valueOf(a+1));
		pawnAttachPo.setFileSize(mf.getSize());
		pawnAttachPo.setMimeType(mf.getContentType());
		pawnAttachPo.setResFile(mf.getOriginalFilename());
		int row = this.productCatService.addPawnAttachPo(pawnAttachPo);
		if(row > 0) {
			try {
				mf.transferTo(new File("d:\\upload\\resource3\\"+pawnAttachPo.getResId()+pawnAttachPo.getResFile()));
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
	@RequestMapping("/previewPictures")
	public void filedown(String resId,String resFile,HttpServletResponse resp){
		Path path=Paths.get("D://upload//resource3//"+resId+resFile);
		File file=path.toFile();	
    	InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			byte[] bs=new byte[1024];
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
	 * 获取当前的附件编号
	 * @return
	 */
	@RequestMapping("/getPawnAttachCode")
	public Map<String,Object> getPawnAttachCode(){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.getPawnAttachCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 让附件和商品大类相关联
	 * @return
	 */
	@RequestMapping("/addPawnAttach")
	public Map<String,Object> addPawnAttach(String resId , String catCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.addPawnAttach(resId , catCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	根据编号加载信息
	 * @return
	 */
	@RequestMapping("/getProductCatByCatCode")
	public Map<String,Object> getProductCatByCatCode(String catCode){
		Map<String,Object> map = null ;
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.getProductCatByCatCode(catCode));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return map ;
	}
	
	/**
	 *	根据编号查询附件 
	 */
	@RequestMapping("/getPawnAttach")
	public Map<String,Object> getPawnAttach(String catCode){
		Map<String,Object> map = null ;
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.getPawnAttach(catCode));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return map ;
	}
	
	/**
	 * 修改附件和商品大类相关联数据
	 * @return
	 */
	@RequestMapping("/updPawnAttachByCatCode")
	public Map<String,Object> updPawnAttachByCatCode(String resId , String catCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.updPawnAttachByCatCode(resId , catCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	修改商品大类数据
	 * @param bo
	 * @return
	 */
	@RequestMapping("/updProductCat")
	public Map<String,Object> updProductCat(ProductCatPo po){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.updProductCat(po));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除附件和商品大类相关联数据
	 * @return
	 */
	@RequestMapping("/removePawnAttachByCatCode")
	public Map<String,Object> removePawnAttachByCatCode(String catCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.removePawnAttachByCatCode(catCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除商品大类的方法
	 * @return
	 */
	@RequestMapping("/removeProductCatByCatCode")
	public Map<String,Object> removeProductCatByCatCode(String catCode){
		Map<String, Object> map = null ;
		
		try {
			map = new HashMap<String,Object>();
			map.put("rows", productCatService.removeProductCatByCatCode(catCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 	表单验证
	 * @param po
	 * @return
	 */
	@RequestMapping("/yanZhengCatCode")
	public  Map<String, Object> validatePriorityCode(String catCode){
		int code = 0 ;
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = productCatService.validatePriorityCode(catCode);
		if(count == 0) {
			code = 1;
		}
		map.put("resultCode", code);
		return map;
	}
}
