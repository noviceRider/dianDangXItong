package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class ConfPo extends BasePo {

	/**
	 * 属性编号
	 */
	private String attrCode ;
	
	/**
	 * 属性名称
	 */
	private String attrName ;
	
	/**
	 * 所属属性组编号
	 */
	private String groupCode ;
	
	/**
	 * 所属属性组名称
	 */
	private String groupName ;
	
	/**
	 *  属性类型
	 */
	private String attrType ;
	
	/**
	 * 可选值列表
	 */
	private String options ;
	
	/**
	 * 分类编号
	 */
	private String catCode ;

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	
	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	@Override
	public String toString() {
		return "ConfPo [attrCode=" + attrCode + ", attrName=" + attrName + ", groupCode=" + groupCode + ", groupName="
				+ groupName + ", attrType=" + attrType + ", options=" + options + "]";
	}
	
}
