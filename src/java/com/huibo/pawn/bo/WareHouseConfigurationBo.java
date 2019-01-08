package com.huibo.pawn.bo;

import com.huibo.pawn.po.WareHouseConfigurationPo;

public class WareHouseConfigurationBo extends WareHouseConfigurationPo {


	/**
	 * 关键字1
	 */
	
	private String keyWord ;
	
	/**
	 * 关键字2
	 */
	
	private String keyword ;
	
	/**
	 *	关键字3
	 * @return
	 */ 
	private String keyByYes ;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyByYes() {
		return keyByYes;
	}

	public void setKeyByYes(String keyByYes) {
		this.keyByYes = keyByYes;
	}

	@Override
	public String toString() {
		return "WareHouseConfigurationBo [keyWord=" + keyWord + ", keyword=" + keyword + ", keyByYes=" + keyByYes + "]";
	}



}
