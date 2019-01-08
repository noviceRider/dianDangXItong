package com.huibo.pawn.bo;

import com.huibo.pawn.po.CPawnbrandPo;
/**
 * 
 * <p>Title: 广丰共享典当管理系统 - CPawnbrandBo</p>
 *
 * <p>Description:品牌管理的bo</p>
 *
 * <p>Copyright: Copyright bnkj(c) 2016</p>
 *
 * <p>Company: 重庆汇博有限公司</p>
 *
 * @author 彭忠义 
 * @version 1.0
 */

public class CPawnbrandBo extends CPawnbrandPo {

	/**
	 * 模糊条件搜索
	 */
	private String keyWord;
	
	/**
	 * 分类编号
	 */
	private String catCode;
	
	/**
	 * 分类名称
	 */
	private String catName;
	
	/**
	 * 是否显示名称
	 * @return
	 */
	private String isShowName;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getIsShowName() {
		return isShowName;
	}

	public void setIsShowName(String isShowName) {
		this.isShowName = isShowName;
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
	
}
