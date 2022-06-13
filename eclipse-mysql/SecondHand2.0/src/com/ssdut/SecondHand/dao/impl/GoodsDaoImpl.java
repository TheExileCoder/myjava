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
	private Connection conn = null; // 保存数据库连接
	private PreparedStatement pstmt = null; // 用于执行SQL语句
	private ResultSet rs = null; // 用户保存查询到的记录结果集
	/**
	 * @Description TODO 得到包含所有商品的泛型集合
	 * @param 
	 * @return List<Goods>
	 */
	@Override
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			String preparedSql = "select * from goods";// SQL 语句
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setGoodsNum(rs.getInt(3));
				goods.setGoodsPrice(rs.getFloat(4));
				goodsList.add(goods);// 把结果集里面的数据添加到泛型集合
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNotFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return goodsList;
	}
	/**
	 * @Description TODO 根据查询条件得到包含一类商品的泛型集合
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return List<Goods>
	 */
	@Override
	public Goods getOneGoods(String sql, Object[] param) {
		// TODO Auto-generated method stub
		Goods goods = null;
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				goods = new Goods();
				goods.setGoodsId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setGoodsNum(rs.getInt(3));
				goods.setGoodsPrice(rs.getFloat(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNotFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return goods;
	}
	/**
	 * @Description TODO 更新商品
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return int
	 */
	@Override
	public int updateGoods(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);//返回受影响的行数
		return count;
	}
	
	@Override
	public int soldout(int goodsId) {
		int num = 0;
		Goods goods = null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		String sql = "select * from goods where goodsId = ?";//SQL语句
		String[] param = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
		goods = goodsDao.getOneGoods(sql, param);
		if(goods != null) {
			num = goods.getGoodsNum();
			if(num==1) {
				return 1;	//顾客买走的是最后一件商品
			}
			else
				return 0;	//商品可以购买
		}
		else {
			
			return 2;	//商品编号不存在
		}
		
	}
}
