package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - IPawnMemberInfoPo</p>
 *
 * <p>Description:会员档案的po</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public class IPawnMemberInfoPo extends BasePo {

	/**
	 * 会员编号
	 */
	private Integer memberId; 
	
	/**
	 * 姓名
	 */
	private String memberName; 
	
	/**
	 * 手机
	 */
	private String mobile; 
	
	/**
	 * 证件号码
	 */
	private String certNo;
	
	/**
	 * 账户名称
	 */
	private String accountName; 
	
	/**
	 * 开户行
	 */
	private String bankName;
	
	/**
	 * 账号
	 */
	private String account; 
	
	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 会员等级
	 */
	private String level;
	
	/**
	 * 可用余额
	 */
	private String balance;
	
	/**
	 * 充值金额
	 */
	private String rechargeMoney;
	
	/**
	 * 冻结金额
	 */
	private String blockedBalances;
	
	/**
	 * 透支额度
	 */
	private String credit;
	
	/**
	 * 是否允许透支
	 */
	private String creditEnabled;
	
	/**
	 * 可用积分
	 */
	private String availablePoint;
	
	/**
	 * 累计积分
	 */
	private String totalPoint;
	
	/**
	 * 入会日期
	 */
	private String joinDate;
	
	/**
	 * 所属渠道编号
	 */
	private String channelCode;
	
	/**
	 * 所属渠道名称
	 */
	private String channelName;
	
	/**
	 * 状态编号
	 */
	private String memberStat;
	
	/**
	 * 状态名称
	 */
	private String memberStatDesc;
	
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBlockedBalances() {
		return blockedBalances;
	}

	public void setBlockedBalances(String blockedBalances) {
		this.blockedBalances = blockedBalances;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getCreditEnabled() {
		return creditEnabled;
	}

	public void setCreditEnabled(String creditEnabled) {
		this.creditEnabled = creditEnabled;
	}

	public String getAvailablePoint() {
		return availablePoint;
	}

	public void setAvailablePoint(String availablePoint) {
		this.availablePoint = availablePoint;
	}

	public String getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getMemberStat() {
		return memberStat;
	}

	public void setMemberStat(String memberStat) {
		this.memberStat = memberStat;
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

	
	public String getMemberStatDesc() {
		return memberStatDesc;
	}

	public void setMemberStatDesc(String memberStatDesc) {
		this.memberStatDesc = memberStatDesc;
	}

	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	
	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	@Override
	public String toString() {
		return "iPawnMemberInfoPo [memberId=" + memberId + ", memberName=" + memberName + ", mobile=" + mobile
				+ ", certNo=" + certNo + ", accountName=" + accountName + ", bankName=" + bankName + ", account="
				+ account + ", birthday=" + birthday + ", level=" + level + ", balance=" + balance
				+ ", blockedBalances=" + blockedBalances + ", credit=" + credit + ", creditEnabled=" + creditEnabled
				+ ", availablePoint=" + availablePoint + ", totalPoint=" + totalPoint + ", joinDate=" + joinDate
				+ ", channelCode=" + channelCode + ", memberStat=" + memberStat + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifyBy=" + modifyBy + ", modifyTime=" + modifyTime + "]";
	}
	
	
}
