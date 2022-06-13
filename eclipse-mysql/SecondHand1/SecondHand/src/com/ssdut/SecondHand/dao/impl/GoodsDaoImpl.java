package com.ssdut.SecondHand.dao.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssdut.SecondHand.dao.BaseDao;
import com.ssdut.SecondHand.dao.GoodsDao;
import com.ssdut.SecondHand.entity.Goods;

public class GoodsDaoImpl extends BaseDao implements GoodsDao{
	private Connection conn = null; // �������ݿ�����
	private PreparedStatement pstmt = null; // ����ִ��SQL���
	private ResultSet rs = null; // �û������ѯ���ļ�¼�����
	/**
	 * @Description TODO �õ��������г��⳵������Ϣ�ķ��ͼ���
	 * @param 
	 * @return List<Taxi>
	 */
	@Override
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
		String preparedSql = "select * from goods";// SQL ���
		conn = getConn(); // �õ����ݿ�����
		pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
		rs = pstmt.executeQuery(); // ִ��SQL���
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setGoodsNum(rs.getInt(3));
				goods.setGoodsPrice(rs.getFloat(4));
				goodsList.add(goods);// �ѽ���������������ӵ����ͼ���
			}
		} catch (SQLException e) {
			e.printStackTrace();//����SQLException�쳣
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//����ClassNotFoundException�쳣
		} finally {
			super.closeAll(conn, pstmt, rs);//�ͷ���Դ
		}
		return goodsList;
	}
	/**
	 * @Description TODO ���ݲ�ѯ�����õ�����һ����⳵�ķ��ͼ���
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return List<Taxi>
	 */
	@Override
	public Goods getOneGoods(String sql, Object[] param) {
		// TODO Auto-generated method stub
		Goods goods = null;
		try {
		conn = getConn(); // �õ����ݿ�����
		pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
			}
		}
		rs = pstmt.executeQuery(); // ִ��SQL���
		while (rs.next()) {
			goods = new Goods();
			goods.setGoodsId(rs.getInt(1));
			goods.setGoodsName(rs.getString(2));
			goods.setGoodsNum(rs.getInt(3));
			goods.setGoodsPrice(rs.getFloat(4));
			
		}
	} catch (SQLException e) {
		e.printStackTrace();//����SQLException�쳣
	} catch (ClassNotFoundException e) {
		e.printStackTrace();//����ClassNotFoundException�쳣
	} finally {
		super.closeAll(conn, pstmt, rs);//�ͷ���Դ
	}
	return goods;
	}
	/**
	 * @Description TODO ���³��⳵�����Ϣ(��������ɾ���ģ�û�в�)
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return int
	 */
	@Override
	public int updateGoods(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);//������Ӱ�������
		return count;
	}
	
	@Override
	public Boolean soldout(int goodsId) {
		int num = 0;
		Goods goods = null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		String sql = "select * from goods where goodsId = ?";//SQL���
		String[] param = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
		goods = goodsDao.getOneGoods(sql, param);
		num = goods.getGoodsNum();
		if(num==0) {
			
			String sql1 = "delete from goods where goodsId = ?";//SQL ���
			String[] param1 = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
			int count = goodsDao.updateGoods(sql1, param1);
			return false;
		}
		else
			return true;	
		
	}
}
