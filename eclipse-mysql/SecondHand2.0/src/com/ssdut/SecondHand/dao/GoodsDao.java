package com.ssdut.SecondHand.dao;
import java.util.*;
import com.ssdut.SecondHand.entity.Goods;
public interface GoodsDao {
	public List<Goods> getAllGoods();//查询出所有商品
	public Goods getOneGoods(String sql, Object[] param);//根据查询条件查询
	public int updateGoods(String sql, Object[] param);//更新信息
	public int soldout(int goodsId);//删除卖完的商品
}
