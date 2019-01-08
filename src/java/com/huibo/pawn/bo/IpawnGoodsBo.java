package com.huibo.pawn.bo;

import com.huibo.pawn.po.IpawnGoodsPo;

public class IpawnGoodsBo extends IpawnGoodsPo {

	/**
	 * 关键字搜索
	 */
	private String keyWord;
	
	/**
	 * 状态搜索
	 */
	private String state;
	
	/**
	 * 录入人搜索
	 */
	private String operator;
	
	/**
	 * 录入时间1搜索
	 */
	private String inputTimeOne;
	
	/**
	 * 录入时间2搜索
	 */
	private String inputTimeTwo;
	
	/**
	 * 属性名称
	 * @return
	 */
	private String attrName;
	
	/**
	 * 属性值
	 * @return
	 */
	private String attrValue;
	
	/**
	 * 分类一级编号
	 */
	private String numberOne;
	
	/**
	 * 分类二级编号
	 */
	private String numberTwo;
	
	/**
	 * 分类三级编号
	 */
	private String numberThree;
	
	/**
	 * 属性类型
	 * @return
	 */
	private String attrType;
	
	/**
	 * 属性编号
	 * @return
	 */
	private String attrCode;
	
	/**
	 * 状态编号
	 * @return
	 */
	private String goodsStatName;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInputTimeOne() {
		return inputTimeOne;
	}

	public void setInputTimeOne(String inputTimeOne) {
		this.inputTimeOne = inputTimeOne;
	}

	public String getInputTimeTwo() {
		return inputTimeTwo;
	}

	public void setInputTimeTwo(String inputTimeTwo) {
		this.inputTimeTwo = inputTimeTwo;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getNumberOne() {
		return numberOne;
	}

	public void setNumberOne(String numberOne) {
		this.numberOne = numberOne;
	}

	public String getNumberTwo() {
		return numberTwo;
	}

	public void setNumberTwo(String numberTwo) {
		this.numberTwo = numberTwo;
	}

	public String getNumberThree() {
		return numberThree;
	}

	public void setNumberThree(String numberThree) {
		this.numberThree = numberThree;
	}

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public String getGoodsStatName() {
		return goodsStatName;
	}

	public void setGoodsStatName(String goodsStatName) {
		this.goodsStatName = goodsStatName;
	}
	
	
}
