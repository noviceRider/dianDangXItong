package com.huibo.pawn.po;
import com.bn.javax.po.BasePo;

/**
 * 
* <p>Title: 广丰共享典当管理系统 - CPawnProductCatPo</p>
*
* <p>Description:品牌分类的po</p>
*
* <p>Copyright: Copyright bnkj(c) 2016</p>
*
* <p>Company: 重庆汇博有限公司</p>
*
* @author 彭忠义 
* @version 1.0
 */
public class CPawnProductCatPo extends BasePo {
	
	/**
	 * 分类编号
	 */
	private String catCode;
	
	/**
	 * 分类名称
	 */
	private String catName;
	
	/**
	 * 上级分类
	 */
	private String pCatCode;
	
	/**
	 * 分类级次
	 */
	private String catLvl;
	
	/**
	 * 分类路径
	 */
	private String catRoute;
	
	/**
	 * 数量单位
	 */
	private String unit;
	
	/**
	 * 分类描述
	 */
	private String catDesc;
	
	/**
	 * 鉴定图定义
	 */
	private String evalPicDef;
	
	/**
	 * 是否显示
	 */
	private String isShow;
	
	/**
	 * 排序
	 */
	private String sortNo;

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

	public String getCatLvl() {
		return catLvl;
	}

	public void setCatLvl(String catLvl) {
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

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	@Override
	public String toString() {
		return "CPawnProductCatPo [catCode=" + catCode + ", catName=" + catName + ", pCatCode=" + pCatCode + ", catLvl="
				+ catLvl + ", catRoute=" + catRoute + ", unit=" + unit + ", catDesc=" + catDesc + ", evalPicDef="
				+ evalPicDef + ", isShow=" + isShow + ", sortNo=" + sortNo + "]";
	}
	
	
}
