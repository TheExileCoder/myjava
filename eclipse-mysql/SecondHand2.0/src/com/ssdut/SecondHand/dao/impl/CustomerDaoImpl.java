package com.ssdut.SecondHand.dao.impl;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssdut.SecondHand.dao.BaseDao;
import com.ssdut.SecondHand.dao.CustomerDao;
import com.ssdut.SecondHand.entity.Customer;

public class CustomerDaoImpl extends BaseDao implements CustomerDao{
	private Connection conn = null; // 保存数据库连接
	private PreparedStatement pstmt = null; // 用于执行SQL语句
	private ResultSet rs = null; // 用户保存查询到的顾客结果集
	/**
	 * @Description TODO 得到包含所有顾客基本信息的泛型集合
	 * @param 
	 * @return List<Customer>
	 */
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			String preparedSql = "select * from customer";// SQL语句
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			rs = pstmt.executeQuery(); // 执行SQL语句，得到结果集
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setPassWord(rs.getString(3));
				customer.setPhoneId(rs.getString(4));
				
				customerList.add(customer);// 把结果集里面的数据添加到泛型集合
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNoFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return customerList;
	}

	

	/**
	 * @Description TODO 更新顾客表的信息
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return int
	 */
	@Override
	public int updateCustomer(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);//返回受影响的行数
		return count;
	}
	/**
	 * @Description TODO 根据查询条件得到某一个顾客
	 * @param sql 预编译的 SQL 语句
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return Customer
	 */
	@Override
	public Customer getOneCustomer(String sql, Object[] param) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {//把查询到的顾客的基本信息赋值给即将返回的Customer
				customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setPassWord(rs.getString(3));
				customer.setPhoneId(rs.getString(4));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();//处理SQLException异常
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//处理ClassNoFoundException异常
		} finally {
			super.closeAll(conn, pstmt, rs);//释放资源
		}
		return customer;
	}
	
	//用户注册
	@Override
	public Boolean registerCustmer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您的姓名:");
		String newCustomerName = scanner.next();
		System.out.println("请输入您的密码:");
		String newCustomerPassWord = scanner.next();
		System.out.println("请输入您的手机号码(11位):");
		String newCustomerPhoneId = scanner.next();
		
		String sql = "insert into customer values (null, ?, ?, ?)";//SQL 语句
		String[] param = {newCustomerName, newCustomerPassWord, newCustomerPhoneId};//包含 SQL 语句中的‘？’参数的字符串数组
		
		int count = updateCustomer(sql, param);
		System.out.println("添加成功！更新了"+count+"条数据。");
		return true;
	}

}
