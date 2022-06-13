package com.ssdut.SecondHand.service.impl;
import java.util.*;

import com.ssdut.SecondHand.dao.*;
import com.ssdut.SecondHand.dao.impl.*;
import com.ssdut.SecondHand.entity.*;
import com.ssdut.SecondHand.service.ManagerService;
public class ManagerServiceImpl implements ManagerService{
	/**
	 * @Description TODO 管理员登录
	 * @param 
	 * @return Manager
	 */
	public Manager login() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("管理员登录");
		System.out.println("请输入您的姓名:");
		String managerName = scanner.next();
		System.out.println("请输入您的密码:");
		String managerPassWord = scanner.next();
		ManagerDao managerDao = new ManagerDaoImpl();
		String sql = "select * from manager where managerName = ? and passWord = ?";// SQL语句
		String[] param = {managerName,managerPassWord};//包含 SQL 语句中的‘？’参数的字符串数组
		Manager manager = managerDao.getOneManager(sql, param);
		if(null != manager) {//若登录成功，展示基本信息
			System.out.println("-------恭喜您成功登录-------");
			System.out.println("-------您的基本信息：-------");
			System.out.println("编号：" + manager.getManagerId());
			System.out.println("姓名：" + manager.getManagerName());
			System.out.println("手机号：" + manager.getPhoneId());
		}
		return manager;
	}
	/**
	 * @Description TODO 更改密码
	 * @param manager 执行该操作的管理员
	 * @return Boolean
	 */
	public Boolean updateMyInfo(Manager manager) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您要修改的密码(长度小于20个字符):");
		String managerNewPassWord = scanner.next();
		System.out.println("请输入您要修改的电话号码(长度11位):");
		String managerNewphonenum = scanner.next();
		ManagerDao managerDao = new ManagerDaoImpl();
		String sql = "update manager set passWord = ?, phonenum = ? where managerId = ? and (passWord <> ? or phonenum <> ?)";//SQL 语句
		//包含 SQL 语句中的‘？’参数的字符串数组
		String[] param = {managerNewPassWord, managerNewphonenum, String.valueOf(manager.getManagerId()),managerNewPassWord,managerNewphonenum};
		int count = managerDao.updateManager(sql, param);//受影响的行数
		if(count == 0)
			System.out.println("修改失败,密码和电话号码与原来相同");
		else
			System.out.println("修改成功！更新了"+count+"条数据。");
		return true;
	}
	/**
	 * @Description TODO 显示管理员本人的基本信息
	 * @param manager 执行该操作的管理员
	 * @return void
	 */
	
	public void getMyInfo(Manager manager) {
		// TODO Auto-generated method stub
		System.out.println("编号:" + manager.getManagerId());
		System.out.println("姓名:" + manager.getManagerName());
		System.out.println("手机号码:" + manager.getPhoneId());
	}
	
	/**
	 * @Description TODO 返回包含所有用户的泛型集合
	 * @param 
	 * @return List<Customer>
	 */
	
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customerList = new ArrayList<Customer>();
		CustomerDao customerDao = new CustomerDaoImpl();
		customerList = customerDao.getAllCustomer();
		return customerList;
	}
	/**
	 * @Description TODO 根据用户的编号查找某一个用户
	 * @param 
	 * @return customer
	 */
	
	public Customer getOneCustomer() {
		// TODO Auto-generated method stub
		Customer customer = null;
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想查询的用户的编号:");
		int customerId = scanner.nextInt();
		String sql = "select * from customer where customerId = ?";//SQL语句
		String[] param = {String.valueOf(customerId)};//包含 SQL 语句中的‘？’参数的字符串数组
		customer = customerDao.getOneCustomer(sql, param);
		return customer;
	}
	
	/**
	 * @Description TODO 修改用户的登录密码
	 * @param 
	 * @return Boolean
	 */
	
	public Boolean updateCustomerPassWord() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想修改密码的用户的编号:");
		int customerId = scanner.nextInt();
		System.out.println("请输入您想为该用户修改的新密码:");
		String customerNewPassWord = scanner.next();
		String sql = "update customer set passWord = ? where customerId = ?";//SQL语句
		String[] param = {customerNewPassWord,String.valueOf(customerId)};//包含 SQL 语句中的‘？’参数的字符串数组
		int count = customerDao.updateCustomer(sql, param);
		if(0 == count)
			System.out.println("输入的用户编号有误！");
		else
			System.out.println("修改成功！更新了"+count+"条数据。");
		return true;
	}
	
	/**
	 * @Description TODO 删除某一个用户
	 * @param 
	 * @return Boolean
	 */
	
	public Boolean delCustomer() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想删除的用户的编号:");
		int customerId = scanner.nextInt();
		String sql = "delete from customer where customerId = ?";//SQL 语句
		String[] param = {String.valueOf(customerId)};//包含 SQL 语句中的‘？’参数的字符串数组
		
		int count = customerDao.updateCustomer(sql, param);
		if(0 == count)
			System.out.println("输入的用户编号有误！");
		else {
			String sql_update = "update customer set customerId = customerId - 1 where customerId > ?";
			String[] param_update = {String.valueOf(customerId)};
			customerDao.updateCustomer(sql_update, param_update);
			System.out.println("删除成功！更新了"+count+"条数据。");
		}

		return true;
	}
	/**
	 * @Description TODO 添加一个用户
	 * @param 
	 * @return Boolean
	 */
	public Boolean addCustomer() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想添加的用户的姓名:");
		String newCustomerName = scanner.next();
		System.out.println("请输入您想添加的用户的密码:");
		String newCustomerPassWord = scanner.next();
		System.out.println("请输入您想添加的用户的手机号码(11位):");
		String newCustomerPhoneId = scanner.next();
		
		String sql = "insert into customer values ((select * from(select max(customerId) from customer)as c)+1,?, ?, ?)";//SQL 语句
		String[] param = {newCustomerName, newCustomerPassWord, newCustomerPhoneId};//包含 SQL 语句中的‘？’参数的字符串数
		int count = customerDao.updateCustomer(sql, param);
		System.out.println("添加成功！更新了"+count+"条数据。");
		return true;
	}
	
	//查看所有商品
	public List<Goods> getAllGoods(){
		List<Goods> goodsList = new ArrayList<Goods>();
		GoodsDao goodsDao = new GoodsDaoImpl();
		goodsList = goodsDao.getAllGoods();
		return goodsList;
	}
	
	//查看某一商品信息
	public Goods getOneGoods() {
		Goods goods = null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想查询的商品的编号:");
		int goodsId = scanner.nextInt();
		String sql = "select * from goods where goodsId = ?";//SQL语句
		String[] param = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
		goods = goodsDao.getOneGoods(sql, param);
		return goods;
	}
	
	/**
	 * @Description TODO 删除商品
	 * @param 
	 * @return Boolean
	 */
	public Boolean delGoods() {
		// TODO Auto-generated method stub
		GoodsDao goodsDao = new GoodsDaoImpl();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想删除商品编号:");
		int goodsId = scanner.nextInt();
		String sql = "delete from goods where goodsId = ?";//SQL 语句
		String[] param = {String.valueOf(goodsId)};//包含 SQL 语句中的‘？’参数的字符串数组
		int count = goodsDao.updateGoods(sql, param);
		if(0 == count)
			System.out.println("输入的商品编号有误！");
		else {
			String sql_updateString = "update Goods set goodsId = goodsId - 1 where goodsId > ?";
			String[] param_updateStrings = {String.valueOf(goodsId)};
			goodsDao.updateGoods(sql_updateString, param_updateStrings);
			System.out.println("删除成功！更新了"+count+"条数据。");
		}
					
		return true;
	}
	
	/**
	 * @Description TODO 添加商品
	 * @param 
	 * @return Boolean
	 */
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
	
	/**
	 * @Description TODO 添加管理员
	 * @param 
	 * @return Boolean
	 */
	public Boolean addManager() {
		// TODO Auto-generated method stub
		ManagerDao managerDao = new ManagerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想添加的管理员的姓名:");
		String newManagerName = scanner.next();
		System.out.println("请输入您想添加的管理员的密码:");
		String newManagerPassWord = scanner.next();
		System.out.println("请输入您想添加的管理员的手机号码(11位):");
		String newManagerPhoneId = scanner.next();
		String sql = "insert into manager values (null, ?, ?, ?)";//SQL 语句
		//包含 SQL 语句中的‘？’参数的字符串数组
		String[] param = {newManagerName, newManagerPassWord, newManagerPhoneId};
		int count = managerDao.updateManager(sql, param);
		System.out.println("添加成功！更新了"+count+"条数据。");
		return true;
	}
	
	//更新商品
	public Boolean updateGoods() {
		// TODO Auto-generated method stub
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入您想修改的商品编号:");
		int goodsId = scanner.nextInt();
		System.out.println("请输入您想修改商品名称:");
		String newGoodsName = scanner.next();
		System.out.println("请输入您想修改的商品数量:");
		int newGoodsNum = scanner.nextInt();
		System.out.println("请输入您想修改的商品的价格:(2位精度)");
		float newGoodsPrice = scanner.nextFloat();	
		System.out.println("请输入商品的使用时长：（单位:天）");
		int newGoodsusedday = scanner.nextInt();
		String sql = "UPDATE goods SET goodsName=?, goodsNum=?, goodsPrice=?, goodsusedday=? WHERE goodsId=?";//SQL 语句
		
		String[] param = {newGoodsName,String.valueOf(newGoodsNum),String.valueOf(newGoodsPrice),String.valueOf(newGoodsusedday),String.valueOf(goodsId)};
		int count = goodsDao.updateGoods(sql, param);
		if(0 != count)
			System.out.println("修改成功！更新了"+count+"条数据。");
		
		return true;
	}
}
