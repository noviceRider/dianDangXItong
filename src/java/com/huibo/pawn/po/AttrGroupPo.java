package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class AttrGroupPo extends BasePo {

	/**
	 * 属性组编号
	 */
	private String groupCode ;
	
	/**
	 * 属性组名
	 */
	private String groupName ;
	
	/**
	 * 状态 00 无\\ 01
	 */
	private String groupState ;

	
	/**
	 * 适用商品大类编号
	 */
	private String catCode ;
	
	/**
	 * 适用商品大类名称
	 */
	private String catName ;
	
	/**
	 *  属性数量
	 */
	private Integer num ;

	
	
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

	public String getGroupState() {
		return groupState;
	}

	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}

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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "AttrGroupPo [groupCode=" + groupCode + ", groupName=" + groupName + ", groupState=" + groupState
				+ ", catCode=" + catCode + ", catName=" + catName + ", num=" + num + "]";
	}
	
}
