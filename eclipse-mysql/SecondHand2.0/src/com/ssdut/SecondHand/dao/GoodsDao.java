package com.ssdut.SecondHand.dao;
import java.util.*;
import com.ssdut.SecondHand.entity.Goods;
public interface GoodsDao {
	public List<Goods> getAllGoods();//��ѯ��������Ʒ
	public Goods getOneGoods(String sql, Object[] param);//���ݲ�ѯ������ѯ
	public int updateGoods(String sql, Object[] param);//������Ϣ
	public int soldout(int goodsId);//ɾ���������Ʒ
}
