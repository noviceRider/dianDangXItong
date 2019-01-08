package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

/**
 * <p>title:广沣共享典当管理系统-ProductCatPo</p>
 * 
 * <p>Description:商品分类的Po</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public class ProductCatPo extends BasePo {

	/**
	 * 	分类编号
	 */
	private String catCode ;
	
	/**
	 * 分类名称
	 */
	private String catName ;
	
	/**
	 * 上级分类编号
	 */
	private String pCatCode ;
	
	/**
	 * 上级分类名称
	 */
	private String pCatName ;
	
	/**
	 * 分类层级
	 */
	private Integer catLvl ;
	
	/**
	 * 分类路径
	 */
	private String catRoute ;
	
	/**
	 * 数量单位
	 */
	private String unit ;
	
	/**
	 * 分类描述
	 */
	private String catDesc ;
	
	/**
	 * 鉴定图定义
	 */
	private String evalPicDef ;
	
	/**
	 * 是否显示
	 */
	private String isShow ;

	
	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getpCatCode() {
		return pCatCode;
	}

	public void setpCatCode(String pCatCode) {
		this.pCatCode = pCatCode;
	}

	public String getpCatName() {
		return pCatName;
	}

	public void setpCatName(String pCatName) {
		this.pCatName = pCatName;
	}

	public Integer getCatLvl() {
		return catLvl;
	}

	public void setCatLvl(Integer catLvl) {
		this.catLvl = catLvl;
	}

	public String getCatRoute() {
		return catRoute;
	}

	public void setCatRoute(String catRoute) {
		this.catRoute = catRoute;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getEvalPicDef() {
		return evalPicDef;
	}

	public void setEvalPicDef(String evalPicDef) {
		this.evalPicDef = evalPicDef;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return "ProductCatPo [catCode=" + catCode + ", catName=" + catName + ", pCatCode=" + pCatCode + ", pCatName="
				+ pCatName + ", catLvl=" + catLvl + ", catRoute=" + catRoute + ", unit=" + unit + ", catDesc=" + catDesc
				+ ", evalPicDef=" + evalPicDef + ", isShow=" + isShow + "]";
	}
	
	
}
