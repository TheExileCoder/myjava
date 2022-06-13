package com.ssdut.SecondHand.dao;

import java.util.*;
import com.ssdut.SecondHand.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomer();//查询出所有出租车司机
	public Customer getOneCustomer(String sql, Object[] param);//根据查询条件查询出某个租车司机(用于登陆时)
	public int updateCustomer(String sql, Object[] param);//更新出租车司机信息
	public Boolean registerCustmer();

}
