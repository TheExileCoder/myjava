package com.ssdut.SecondHand.service.impl;

import java.util.*;
import com.ssdut.SecondHand.entity.*;
import com.ssdut.SecondHand.dao.*;
import com.ssdut.SecondHand.dao.impl.*;
import com.ssdut.SecondHand.service.*;


public class CustomerServiceImpl implements CustomerService{
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
	public float buyGoods(Customer customer, int printflag[]) {
		float pay = 0;
		Goods goods = null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想购买的商品的编号:");
		int goodsId = scanner.nextInt();
		//顾客买走最后一件商品
		if(goodsDao.soldout(goodsId) == 1) {
			System.out.println("购买成功，商品售空，请查看最新商品列表");
			String sql = "select * from goods where goodsId = ?";//SQL语句
			String[] param = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
			goods = goodsDao.getOneGoods(sql, param);
			pay = goods.getGoodsPrice();
			String sql1 = "delete from goods where goodsId = ?";//SQL 语句，将此商品从数据库中删除
			String[] param1 = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
			int count = goodsDao.updateGoods(sql1, param1);
			String sql_update_goods = "update goods set goodsId = goodsId - 1 where goodsId > ?";//更新商品的id
			String[] param_update_goodStrings = {String.valueOf(goodsId)};
			goodsDao.updateGoods(sql_update_goods, param_update_goodStrings);
			printflag[0] = 1;
		}
		//商品编号错误
		else if (goodsDao.soldout(goodsId) == 2) {
			System.out.println("购买失败，商品编号错误！");
		}
		//正常购买
		else {
			String sql = "select * from goods where goodsId = ?";//SQL语句
			String[] param = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
			goods = goodsDao.getOneGoods(sql, param);
			pay = goods.getGoodsPrice();
			String sql1 = "update goods set goodsNum = goodsNum-1 where goodsId = ?";//SQL语句
			String[] param1 = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
			goodsDao.updateGoods(sql1, param1);
			System.out.println("购买成功！");
		}
		return pay;
	}

	public Boolean addCustomer() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户的姓名:");
		String newCustomerName = scanner.next();
		System.out.println("请输入用户的密码:");
		String newCustomerPassWord = scanner.next();
		System.out.println("请输入用户的手机号码(11位):");
		String newCustomerPhoneId = scanner.next();
		String sql = "insert into customer values (null,?, ?, ?)";//SQL 语句
		String[] param = {newCustomerName, newCustomerPassWord, newCustomerPhoneId};//包含 SQL 语句中的‘？’参数的字符串数
		int count = customerDao.updateCustomer(sql, param);
		System.out.println("注册成功。");
		return true;
	}
	
	public Boolean addGoods() {
		// TODO Auto-generated method stub
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想添加商品名称:");
		String newGoodsName = scanner.next();
		System.out.println("请输入您想添加的商品数量:");
		int newGoodsNum = scanner.nextInt();
		System.out.println("请输入您想添加的商品的价格:(2位精度)");
		float newGoodsPrice = scanner.nextFloat();
		System.out.println("请输入商品的使用时长：（单位:天）");
		int newGoodsusedday = scanner.nextInt();
		String sql = "insert into goods values ((select * from(select max(goodsId) from goods)as g)+1,?,?,?,?,null)";//SQL 语句
		String[] param = {newGoodsName,String.valueOf(newGoodsNum),String.valueOf(newGoodsPrice),String.valueOf(newGoodsusedday)};
		int count = goodsDao.updateGoods(sql, param);
		if(0 != count)
			System.out.println("添加成功！更新了"+count+"条数据。");
		return true;
	}

	public Boolean Goodscommon(Customer customer){
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入的商品编号:");
		int goodsId = scanner.nextInt();
		int uid = customer.getCustomerId();
		System.out.println("请输入评论:");
		String common = scanner.next();
		String sql = "UPDATE goods SET goodscommon=? WHERE goodsId=?";//SQL 语句
		String[] param ={common,String.valueOf(goodsId)};
		int count = goodsDao.updateGoods(sql, param);
		if(0 != count)
			System.out.println("修改成功！更新了"+count+"条数据。");
		return true;
	}
}





