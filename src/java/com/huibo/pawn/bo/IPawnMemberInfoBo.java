package com.huibo.pawn.bo;

import com.huibo.pawn.po.IPawnMemberInfoPo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - IPawnMemberInfoBo</p>
 *
 * <p>Description:会员档案的bo</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public class IPawnMemberInfoBo extends IPawnMemberInfoPo {
	/**
	 * 模糊条件查询
	 */
	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
