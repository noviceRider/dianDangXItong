package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class StoreInformationPo extends BasePo {

	/**
	 * 	门店编号
	 */
	private String shopCode ;
	
	/**
	 * 	门店名称
	 */
	private String shopName ;
	
	/**
	 * 	联系人
	 */
	private	String contact ;
	
	/**
	 * 	联系电话
	 */
	private	String phoneNo ;
	
	/**
	 * 	联系地址
	 */
	private String address ;
	
	/**
	 * 管辖仓库编号
	 */
	private String whCode ;
	
	/**
	 * 管辖仓库名称
	 */
	private String whName ;
	
	/**
	 * 	状态编号
	 */
	private String shopState ;
	
	/**
	 * 	状态描述
	 */
	private String shopDesc ;

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getShopState() {
		return shopState;
	}

	public void setShopState(String shopState) {
		this.shopState = shopState;
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	@Override
	public String toString() {
		return "StoreInformationPo [shopCode=" + shopCode + ", shopName=" + shopName + ", contact=" + contact
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", whCode=" + whCode + ", whName=" + whName
				+ ", shopState=" + shopState + ", shopDesc=" + shopDesc + "]";
	}


	

}
