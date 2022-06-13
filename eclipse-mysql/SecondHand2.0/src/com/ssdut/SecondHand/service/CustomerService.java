package com.ssdut.SecondHand.service;

import java.util.*;
import com.ssdut.SecondHand.entity.*;

public interface CustomerService {
	public Customer login();//顾客登录
	public Boolean updateMyInfo(Customer customer);//修改自己的信息
	public void getMyInfo(Customer customer);//得到顾客的基本信息(ID，姓名，手机号)
	public List<Goods> getAllGoods();//查看所有商品
	public float buyGoods(Customer customer, int printflag[]);
	public Boolean addCustomer();
	public Boolean addGoods();
	public Boolean Goodscommon(Customer customer);
}
