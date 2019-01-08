package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;
/**
* <p>Title: 广沣共享典当管理系统 - WareHouseConfigurationPo</p>
*
* <p>Description:仓储系统的实体类</p>
*
* <p>Copyright: Copyright hbkj(c) 2018</p>
*
* <p>Company: 重庆汇博科技股份有限公司</p>
*
* @author 揭振宇
* @version 1.0
*/
public class WareHouseConfigurationPo extends BasePo {
	/**
	 * 仓库编号
	 */
	private	String	whCode	;
	
	
	/**
	 * 仓库名称
	 */
	private	String	whName	;
	
	
	/**
	 * 联系人编号
	 */
	private	String	contact	;
	
	/**
	 * 联系人名称
	 */
	private	String	contactName	;
	
	/**
	 * 联系方式
	 */
	private	String	phoneNo	;
	
	
	/**
	 * 地址
	 */
	private	String	address	;
	
	/**
	 * 状态ID
	 * @return
	 */
	private String whStateId;
	
	
	/**
	 * 状态
	 */
	private	String	whStat	;
	
	
	/**
	 * 关联店铺
	 * @return
	 */
	private String relShop	;
	
	
	
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getWhStateId() {
		return whStateId;
	}
	public void setWhStateId(String whStateId) {
		this.whStateId = whStateId;
	}
	public String getRelShop() {
		return relShop;
	}
	public void setRelShop(String relShop) {
		this.relShop = relShop;
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
	public String getWhStat() {
		return whStat;
	}
	public void setWhStat(String whStat) {
		this.whStat = whStat;
	}
	@Override
	public String toString() {
		return "WareHouseConfigurationPo [whCode=" + whCode + ", whName=" + whName + ", contact=" + contact
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", whStateId=" + whStateId + ", whStat=" + whStat
				+ ", relShop=" + relShop + "]";
	}
	
	

	
}
