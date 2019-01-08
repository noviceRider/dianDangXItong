package com.huibo.pawn.bo;

import com.huibo.pawn.po.ProductCatPo;

/**
 * <p>title:广沣共享典当管理系统-ProductCatBo</p>
 * 
 * <p>Description:商品分类的Bo</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */
public class ProductCatBo extends ProductCatPo {

	/**
	 * 关键字
	 */
	private String keyWord ;
	
	/**
	 * 
	 */
	private String keyWordByYes ;
	
	/**
	 * 
	 */
	private String keyWordByNo ;
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyWordByYes() {
		return keyWordByYes;
	}

	public void setKeyWordByYes(String keyWordByYes) {
		this.keyWordByYes = keyWordByYes;
	}

	public String getKeyWordByNo() {
		return keyWordByNo;
	}

	public void setKeyWordByNo(String keyWordByNo) {
		this.keyWordByNo = keyWordByNo;
	}

	@Override
	public String toString() {
		return "ProductCatBo [keyWord=" + keyWord + ", keyWordByYes=" + keyWordByYes + ", keyWordByNo=" + keyWordByNo
				+ "]";
	}

	
}
