package com.huibo.pawn.bo;

import com.huibo.pawn.po.ConfPo;

public class ConfBo extends ConfPo{

	/**
	 * 关键字
	 */
	private String keyWord ;
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "ConfBo [keyWord=" + keyWord + "]";
	}
	
	
}
