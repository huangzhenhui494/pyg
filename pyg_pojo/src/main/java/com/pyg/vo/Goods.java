package com.pyg.vo;

import com.pyg.pojo.TbGoods;
import com.pyg.pojo.TbGoodsDesc;
import com.pyg.pojo.TbItem;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7587731858847844283L;

	// 货品
	private TbGoods goods;
	
	// 护品描述
	private TbGoodsDesc goodsDesc;
	
	// 商品列表
	private List<TbItem> itemList;

	public TbGoods getGoods() {
		return goods;
	}

	public void setGoods(TbGoods goods) {
		this.goods = goods;
	}

	public TbGoodsDesc getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(TbGoodsDesc goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public List<TbItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TbItem> itemList) {
		this.itemList = itemList;
	}
	
	
}
