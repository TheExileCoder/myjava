package com.ssdut.SecondHand.dao;

import java.util.*;
import com.ssdut.SecondHand.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomer();//查询出所有顾客
	public Customer getOneCustomer(String sql, Object[] param);//根据查询条件查询出某个顾客
	public int updateCustomer(String sql, Object[] param);//更新顾客信息
	public Boolean registerCustmer();//用户注册

}
