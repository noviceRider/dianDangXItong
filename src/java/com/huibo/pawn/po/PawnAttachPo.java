package com.huibo.pawn.po;

import com.bn.javax.po.BasePo;

public class PawnAttachPo extends BasePo {

	/**
	 * 附件编号
	 */
	private String resId ;
	
	/**
	 * 附件名
	 */
	private String resFile ;
	
	/**
	 * 附件大小
	 */
	private Long fileSize ;
	
	/**
	 * 附件类型
	 */
	private String mimeType ;
	
	/**
	 * 附件描述
	 * @return
	 */
	private String resDesc;
	
	/**
	 * 商品ID 
	 * @return
	 */
	private String goodsId;

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResFile() {
		return resFile;
	}

	public void setResFile(String resFile) {
		this.resFile = resFile;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return "PawnAttachPo [resId=" + resId + ", resFile=" + resFile + ", fileSize=" + fileSize + ", mimeType="
				+ mimeType + "]";
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
