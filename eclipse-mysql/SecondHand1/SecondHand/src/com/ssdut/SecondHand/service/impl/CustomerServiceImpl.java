package com.ssdut.SecondHand.service.impl;

import java.util.*;
import com.ssdut.SecondHand.entity.*;
import com.ssdut.SecondHand.dao.*;
import com.ssdut.SecondHand.dao.impl.*;
import com.ssdut.SecondHand.service.*;


public class CustomerServiceImpl implements CustomerService{
	@Override
	public Customer login() {
	// TODO Auto-generated method stub
	Scanner scanner = new Scanner(System.in);
	System.out.println("\t用户登录");
	System.out.println("请输入您的姓名:");
	String customerName = scanner.next();
	System.out.println("请输入您的密码:");
	String customerPassWord = scanner.next();
	CustomerDao customerDao = new CustomerDaoImpl();
	String sql = "select * from customer where customerName = ? and passWord = ?";//SQL 语句
	String[] param = {customerName, customerPassWord};//包含 SQL 语句中的‘？’参数的字符串数组
	Customer customer = customerDao.getOneCustomer(sql, param);
	if(null != customer) {//若登陆成功，展示基本信息
		System.out.println("-------恭喜您成功登录-------");
		System.out.println("-------您的基本信息：-------");
		System.out.println("编号：" + customer.getCustomerId());
		System.out.println("姓名：" + customer.getCustomerName());
		System.out.println("手机号：" + customer.getPhoneId());
		
	}
	return customer;
	}
	
	@Override
	public Boolean updateMyInfo(Customer customer) {
	// TODO Auto-generated method stub
	Scanner scanner = new Scanner(System.in);
	System.out.println("请输入您要修改的密码(长度小于20个字符):");
	String customerNewPassWord = scanner.next();
	System.out.println("请输入您要修改的电话号码(长度11位):");
	String customerNewphonenum = scanner.next();
	CustomerDao customerDao = new CustomerDaoImpl();
	String sql = "update customer set passWord = ?, phonenum = ? where customerId = ? and (passWord <> ? or phonenum <> ?)";//SQL 语句
	//包含 SQL 语句中的‘？’参数的字符串数组
	String[] param = {customerNewPassWord, customerNewphonenum, String.valueOf(customer.getCustomerId()),customerNewPassWord,customerNewphonenum};
	int count = customerDao.updateCustomer(sql, param);//受影响的行数
	if(count == 0)
		System.out.println("修改失败,密码和电话号码与原来相同");
	else
		System.out.println("修改成功！更新了"+count+"条数据。");
	return true;
	}
	
	
	@Override
	public void getMyInfo(Customer customer) {
	// TODO Auto-generated method stub
	System.out.println("编号：" + customer.getCustomerId());
	System.out.println("姓名：" + customer.getCustomerName());
	System.out.println("手机号码：" + customer.getPhoneId());
	
	}
	
	//查看所有商品
		public List<Goods> getAllGoods(){
			List<Goods> goodsList = new ArrayList<Goods>();
			GoodsDao goodsDao = new GoodsDaoImpl();
			goodsList = goodsDao.getAllGoods();
			return goodsList;
		}
	//购买商品	
		public float buyGoods() {
			float pay = 0;
			Goods goods = null;
			GoodsDao goodsDao = new GoodsDaoImpl();
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入您想购买的商品的编号:");
			int goodsId = scanner.nextInt();
			String sql = "select * from goods where goodsId = ?";//SQL语句
			String[] param = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
			goods = goodsDao.getOneGoods(sql, param);
			
			if(goodsDao.soldout(goodsId)==false) {
				System.out.println("购买失败，商品已售空！");
			}
			else {
				pay = goods.getGoodsPrice();
				String sql1 = "update goods set goodsNum = goodsNum-1 where goodsId = ?";//SQL语句
				String[] param1 = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
				goodsDao.updateGoods(sql1, param1);
				System.out.println("购买成功！");
			}
			
			return pay;
			
		}

}




