package com.ssdut.SecondHand.service;
import com.ssdut.SecondHand.entity.*;
import java.util.*;

public interface ManagerService {
	public Manager login();//管理员登陆
	public Boolean updateMyInfo(Manager manager);//修改自己的信息
	public void getMyInfo(Manager manager);//得到自己的基本信息
	public List<Customer> getAllCustomer();//得到所有司机信息(包括本月目前的工资)
	public Customer getOneCustomer();//得到某一个司机的基本信息(包括本月目前的工资)
	public Boolean updateCustomerPassWord();//修改某一个司机的登陆密码(在司机忘记登录密码的情况下)
	public Boolean delCustomer();//删除某一个司机
	public Boolean addCustomer();//添加某一个司机
	public List<Goods> getAllGoods();//查看所有商品
	public Goods getOneGoods();//得到某一商品信息
	public Boolean delGoods();//删除某一商品
	public Boolean addGoods();//添加某一商品
	
	public Boolean addManager();//添加管理员
	
	public Boolean updateGoods();

}
