package com.ssdut.SecondHand.dao;

import java.util.*;
import com.ssdut.SecondHand.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomer();//��ѯ�����й˿�
	public Customer getOneCustomer(String sql, Object[] param);//���ݲ�ѯ������ѯ��ĳ���˿�
	public int updateCustomer(String sql, Object[] param);//���¹˿���Ϣ
	public Boolean registerCustmer();//�û�ע��

}
