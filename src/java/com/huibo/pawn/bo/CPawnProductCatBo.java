package com.huibo.pawn.bo;

import com.huibo.pawn.po.CPawnProductCatPo;

/**
 * 
* <p>Title: 广丰共享典当管理系统 - CPawnProductCatBo</p>
*
* <p>Description:商品大类的bo</p>
*
* <p>Copyright: Copyright bnkj(c) 2016</p>
*
* <p>Company: 重庆汇博有限公司</p>
*
* @author 彭忠义 
* @version 1.0
 */
public class CPawnProductCatBo extends CPawnProductCatPo {

	/**
	 * 模糊条件查询
	 */
	private String keyWord;
	
	/**
	 * 品牌编号
	 * @return
	 */
	private String brandCode;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	
	
}
