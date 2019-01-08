package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class AssessmentPo extends BasePo {

	/**
	 * 估价序号
	 */
	private Integer appraiseId;
	
	/**
	 * 商品序号
	 */
	private Integer goodsId;
	
	/**
	 * 流程实例ID
	 */
	private String procInstId;
	
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
	private String rchasePrice;
	
	/**
	 * 售卖价
	 */
	private String sellingPrice;
	
	/**
	 * 租价
	 */
	private String rentalPrice;
	
	/**
	 * 备注
	 */
	private String appraiseDesc;
	
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

	public Integer getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(Integer appraiseId) {
		this.appraiseId = appraiseId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
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

	public String getRchasePrice() {
		return rchasePrice;
	}

	public void setRchasePrice(String rchasePrice) {
		this.rchasePrice = rchasePrice;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(String rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getAppraiseDesc() {
		return appraiseDesc;
	}

	public void setAppraiseDesc(String appraiseDesc) {
		this.appraiseDesc = appraiseDesc;
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
	
	
}
