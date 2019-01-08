package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class IdentificationOfRecordsPo extends BasePo {

	/**
	 * 鉴定序号
	 */
	private Integer identifyId;
	
	/**
	 * 商品序号
	 */
	private Integer goodsId;
	
	/**
	 * 流程实例ID
	 */
	private String procInstId;
	
	/**
	 * 新旧程度
	 */
	private String goodsQuality;
	
	/**
	 * 备注
	 */
	private String identifyDesc;
	
	/**
	 * 鉴定结果
	 */
	private String identifyResult;
	
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
	 * 修改事件
	 */
	private String modifyTime;

	public Integer getIdentifyId() {
		return identifyId;
	}

	public void setIdentifyId(Integer identifyId) {
		this.identifyId = identifyId;
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

	public String getGoodsQuality() {
		return goodsQuality;
	}

	public void setGoodsQuality(String goodsQuality) {
		this.goodsQuality = goodsQuality;
	}

	public String getIdentifyDesc() {
		return identifyDesc;
	}

	public void setIdentifyDesc(String identifyDesc) {
		this.identifyDesc = identifyDesc;
	}

	public String getIdentifyResult() {
		return identifyResult;
	}

	public void setIdentifyResult(String identifyResult) {
		this.identifyResult = identifyResult;
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
