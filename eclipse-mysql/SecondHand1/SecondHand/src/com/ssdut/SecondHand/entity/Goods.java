package com.ssdut.SecondHand.entity;

public class Goods {
	private int goodsId;//���ƺ�
	private String goodsName;//������ǰ״̬�����ѳ��⡱��δ���⡱
	private int goodsNum;
	private float goodsPrice;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsname) {
		this.goodsName = goodsname;
	}
	
	public int getGoodsNum() {
		return goodsNum;
	}
	
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public float getGoodsPrice() {
		return goodsPrice;
	}
	
	public void setGoodsPrice(float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

}
