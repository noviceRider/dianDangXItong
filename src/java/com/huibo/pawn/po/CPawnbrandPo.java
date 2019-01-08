package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - CPawnbrandPo</p>
 *
 * <p>Description:品牌管理的po</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public class CPawnbrandPo extends BasePo {

	/**
	 * 品牌编号
	 */
	private String brandCode;
	
	/**
	 * 品牌名称
	 */
	private String brandName; 
	
	/**
	 * 品牌首字母
	 */
	private String fLetter;
	
	/**
	 * 品牌简介
	 */
	private String brandDesc;
	
	/**
	 * 是否显示
	 */
	private String isShow;
	
	/**
	 * 排序
	 */
	private String sortNo;
	
	/**
	 * 分类编号
	 * @return
	 */
	private String catCode;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getfLetter() {
		return fLetter;
	}

	public void setfLetter(String fLetter) {
		this.fLetter = fLetter;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	
	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	@Override
	public String toString() {
		return "CPawnbrand [brandCode=" + brandCode + ", brandName=" + brandName + ", fLetter=" + fLetter
				+ ", brandDesc=" + brandDesc + ", isShow=" + isShow + ", sortNo=" + sortNo + "]";
	}
	
	
}
