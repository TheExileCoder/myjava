package com.ssdut.SecondHand.dao;

import java.util.*;
import com.ssdut.SecondHand.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomer();//��ѯ�����г��⳵˾��
	public Customer getOneCustomer(String sql, Object[] param);//���ݲ�ѯ������ѯ��ĳ���⳵˾��(���ڵ�½ʱ)
	public int updateCustomer(String sql, Object[] param);//���³��⳵˾����Ϣ
	public Boolean registerCustmer();

}
