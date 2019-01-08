package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class IpawnGoodsPo extends BasePo {

	/**
	 * 商品序号
	 */
	private Integer goodsId;
	
	/**
	 * 原商品序号
	 */
	private Integer sourceGoodsId;
	
	/**
	 * 流程实例ID
	 */
	private String  procInstId;
	
	/**
	 * 品牌编号
	 */
	private String brandCode;
	
	/**
	 * 品牌名称
	 */
	private String brandName;
	
	/**
	 * 分类编号
	 */
	private String catCode;
	
	/**
	 * 二级分类
	 */
	private String subCatCode;
	
	/**
	 * 二级分类名称
	 */
	private String subCatName;
	
	/**
	 * 三级分类
	 */
	private String detailCatCode;
	
	/**
	 * 三级分类名称
	 */
	private String detailCatName;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 来源门店
	 */
	private String shopCode;
	
	/**
	 * 来源门店
	 */
	private String shopName;
	
	/**
	 * 创库编号
	 */
	private String whCode;
	
	/**
	 * 商品货号
	 */
	private String articleNumber;
	
	/**
	 * 客户首买价
	 */
	private String firstPrice;
	
	/**
	 * 官方价
	 */
	private String officialPrice;
	
	/**
	 * 评估价
	 */
	private String valuationPrice;
	
	/**
	 * 典当价
	 */
	private String pawnPrice;
	
	/**
	 * 收购价
	 */
	private String purchasePrice;
	
	/**
	 * 售卖价
	 */
	private String sellingPrice;
	
	/**
	 * 租价
	 */
	private String rentPrice; 
	
	/**
	 * 寄卖最低价
	 */
	private String bottomPrice;
	
	/**
	 * 商品描述
	 */
	private String goodsDesc;
	
	/**
	 * 录入人
	 */
	private String inputUser;
	
	/**
	 * 录入时间
	 */
	private String inputDate;
	
	/**
	 * 鉴定人
	 */
	private String surveyor;
	
	/**
	 * 鉴定时间
	 */
	private String surveyTime;
	
	/**
	 * 评估人
	 */
	private String assessor;
	
	/**
	 * 评估时间
	 */
	private String assessTime;
	
	/**
	 * 是否可租
	 */
	private String isRentable;
	
	/**
	 * 是否可售
	 */
	private String isSalable;
	
	/**
	 * 商品来源
	 */
	private String sourceType;
	
	/**
	 * 是否处于流程中
	 */
	private String isinProc;
	
	/**
	 * 库存状态
	 */
	private String stockStat;
	
	/**
	 * 状态
	 */
	private String goodsStat;
	
	/**
	 * 创建人
	 */
	private String createBy;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 修改人
	 */
	private String modifyBy;
	
	/**
	 * 修改时间
	 */
	private String modifyTime;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSourceGoodsId() {
		return sourceGoodsId;
	}

	public void setSourceGoodsId(Integer sourceGoodsId) {
		this.sourceGoodsId = sourceGoodsId;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getSubCatCode() {
		return subCatCode;
	}

	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}

	public String getDetailCatCode() {
		return detailCatCode;
	}

	public void setDetailCatCode(String detailCatCode) {
		this.detailCatCode = detailCatCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(String firstPrice) {
		this.firstPrice = firstPrice;
	}

	public String getOfficialPrice() {
		return officialPrice;
	}

	public void setOfficialPrice(String officialPrice) {
		this.officialPrice = officialPrice;
	}

	public String getValuationPrice() {
		return valuationPrice;
	}

	public void setValuationPrice(String valuationPrice) {
		this.valuationPrice = valuationPrice;
	}

	public String getPawnPrice() {
		return pawnPrice;
	}

	public void setPawnPrice(String pawnPrice) {
		this.pawnPrice = pawnPrice;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getBottomPrice() {
		return bottomPrice;
	}

	public void setBottomPrice(String bottomPrice) {
		this.bottomPrice = bottomPrice;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getInputUser() {
		return inputUser;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}

	public String getSurveyTime() {
		return surveyTime;
	}

	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}

	public String getAssessor() {
		return assessor;
	}

	public void setAssessor(String assessor) {
		this.assessor = assessor;
	}

	public String getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(String assessTime) {
		this.assessTime = assessTime;
	}

	public String getIsRentable() {
		return isRentable;
	}

	public void setIsRentable(String isRentable) {
		this.isRentable = isRentable;
	}

	public String getIsSalable() {
		return isSalable;
	}

	public void setIsSalable(String isSalable) {
		this.isSalable = isSalable;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getIsinProc() {
		return isinProc;
	}

	public void setIsinProc(String isinProc) {
		this.isinProc = isinProc;
	}

	public String getStockStat() {
		return stockStat;
	}

	public void setStockStat(String stockStat) {
		this.stockStat = stockStat;
	}

	public String getGoodsStat() {
		return goodsStat;
	}

	public void setGoodsStat(String goodsStat) {
		this.goodsStat = goodsStat;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getDetailCatName() {
		return detailCatName;
	}

	public void setDetailCatName(String detailCatName) {
		this.detailCatName = detailCatName;
	}
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Override
	public String toString() {
		return "IpawnGoods [goodsId=" + goodsId + ", sourceGoodsId=" + sourceGoodsId + ", procInstId=" + procInstId
				+ ", brandCode=" + brandCode + ", catCode=" + catCode + ", subCatCode=" + subCatCode
				+ ", detailCatCode=" + detailCatCode + ", goodsName=" + goodsName + ", shopCode=" + shopCode
				+ ", whCode=" + whCode + ", articleNumber=" + articleNumber + ", firstPrice=" + firstPrice
				+ ", officialPrice=" + officialPrice + ", valuationPrice=" + valuationPrice + ", pawnPrice=" + pawnPrice
				+ ", purchasePrice=" + purchasePrice + ", sellingPrice=" + sellingPrice + ", rentPrice=" + rentPrice
				+ ", bottomPrice=" + bottomPrice + ", goodsDesc=" + goodsDesc + ", inputUser=" + inputUser
				+ ", inputDate=" + inputDate + ", surveyor=" + surveyor + ", surveyTime=" + surveyTime + ", assessor="
				+ assessor + ", assessTime=" + assessTime + ", isRentable=" + isRentable + ", isSalable=" + isSalable
				+ ", sourceType=" + sourceType + ", isinProc=" + isinProc + ", stockStat=" + stockStat + ", goodsStat="
				+ goodsStat + ", createBy=" + createBy + ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", modifyTime=" + modifyTime + "]";
	}
	
	
	
}
