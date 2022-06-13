package com.ssdut.SecondHand.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssdut.SecondHand.dao.BaseDao;
import com.ssdut.SecondHand.dao.ManagerDao;
import com.ssdut.SecondHand.entity.Manager;

public class ManagerDaoImpl extends BaseDao implements ManagerDao {
	private Connection conn = null; // 保存数据库连接
	private PreparedStatement pstmt = null; // 用于执行SQL语句
	private ResultSet rs = null; // 用户保存查询到的管理员结果集
	/**
	 * @Description TODO 得到包含所有管理员基本信息的泛型集合
	 * @param 
	 * @return List<Manager>
	 */
	@Override
	public List<Manager> getAllManager() {
		// TODO Auto-generated method stub
		List<Manager> managerList = new ArrayList<Manager>();
		try {
			String preparedSql = "select * from manager";// SQL语句
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Manager manager = new Manager();
				manager.setManagerId(rs.getInt(1));
				manager.setManagerName(rs.getString(2));
				manager.setPassWord(rs.getString(3));
				manager.setPhoneId(rs.getString(4));
				managerList.add(manager);// 把结果集里面的数据添加到泛型集合
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNotFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return managerList;
	}
	/**
	 * @Description TODO 根据查询条件得到包含一类管理员基本信息的泛型集合
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return List<Manager>
	 */
	@Override
	public List<Manager> getKindManager(String sql, Object[] param) {
		// TODO Auto-generated method stub
		List<Manager> managerList = new ArrayList<Manager>();
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
				Manager manager = new Manager();
				manager.setManagerId(rs.getInt(1));
				manager.setManagerName(rs.getString(2));
				manager.setPassWord(rs.getString(3));
				manager.setPhoneId(rs.getString(4));
				managerList.add(manager);// 把结果集里面的数据添加到泛型集合
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNotFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return managerList;
	}
	/**
	 * @Description TODO 更新管理员表的信息(包括增、删、改，没有查)
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return int
	 */
	@Override
	public int updateManager(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);//返回受影响的行数
		return count;
	}
	/**
	 * @Description TODO 根据查询条件得到某一个管理员
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return Manager
	 */
	@Override
	public Manager getOneManager(String sql, Object[] param) {
		// TODO Auto-generated method stub
		Manager manager = null;
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
				manager = new Manager();//把查询到的管理员的基本信息赋值给即将返回的manager
				manager.setManagerId(rs.getInt(1));
				manager.setManagerName(rs.getString(2));
				manager.setPassWord(rs.getString(3));
				manager.setPhoneId(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNotFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return manager;
	}

}
