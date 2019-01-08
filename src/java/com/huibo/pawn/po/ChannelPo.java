package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

/**
* <p>Title: 典当系统 - 渠道商 - ChannelPo</p>
*
* <p>Description:实体类</p>
*
* <p>Copyright: Copyright HBRC(c) 2018</p>
*
* <p>Company: 汇博人才</p>
*
* @author 王杰
* @version 1.0
*/
public class ChannelPo extends BasePo {
	
	//渠道商编号
	private String channelCode;
	
	//渠道商名称
	private String channelName;
	
	//电话
	private String mobile;
	
	//证件号码
	private String idNo;
	
	//账户名称
	private String accountName;
	
	//开户行
	private String bankName;
	
	//帐号
	private String account;
	
	//会员数量
	private Integer memberCount;
	
	//累计充值
	private Long totalCharge;
	
	//累计消费
	private Long consume;
	
	//状态
	private Integer channelState;
	
	//创建人
	private String createBy;
	
	//创建时间
	private String createTime;
	
	//修改人
	private String modifyBy;
	
	//修改时间
	private String modifyTime;

	public ChannelPo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChannelPo(String channelCode, String channelName, String mobile, String idNo, String accountName,
			String bankName, String account, Integer memberCount, Long totalCharge, Long consume, Integer channelState,
			String createBy, String createTime, String modifyBy, String modifyTime) {
		super();
		this.channelCode = channelCode;
		this.channelName = channelName;
		this.mobile = mobile;
		this.idNo = idNo;
		this.accountName = accountName;
		this.bankName = bankName;
		this.account = account;
		this.memberCount = memberCount;
		this.totalCharge = totalCharge;
		this.consume = consume;
		this.channelState = channelState;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "ChannelPo [channelCode=" + channelCode + ", channelName=" + channelName + ", mobile=" + mobile
				+ ", idNo=" + idNo + ", accountName=" + accountName + ", bankName=" + bankName + ", account=" + account
				+ ", memberCount=" + memberCount + ", totalCharge=" + totalCharge + ", consume=" + consume
				+ ", channelState=" + channelState + ", createBy=" + createBy + ", createTime=" + createTime
				+ ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + "]";
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Long getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Long totalCharge) {
		this.totalCharge = totalCharge;
	}

	public Long getConsume() {
		return consume;
	}

	public void setConsume(Long consume) {
		this.consume = consume;
	}

	public Integer getChannelState() {
		return channelState;
	}

	public void setChannelState(Integer channelState) {
		this.channelState = channelState;
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
