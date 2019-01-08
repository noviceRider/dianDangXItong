package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class GoodsAttrRelPo extends BasePo {

	/**
	 * 属性编号
	 */
	private String attrCode;
	
	/**
	 * 商品序号
	 */
	private Integer goodsId;
	
	/**
	 * 属性值
	 */
	private String attrValue;

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	
	
}
