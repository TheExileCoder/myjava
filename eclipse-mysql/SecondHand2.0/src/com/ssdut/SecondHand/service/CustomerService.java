package com.ssdut.SecondHand.service;

import java.util.*;
import com.ssdut.SecondHand.entity.*;

public interface CustomerService {
	public Customer login();//�˿͵�¼
	public Boolean updateMyInfo(Customer customer);//�޸��Լ�����Ϣ
	public void getMyInfo(Customer customer);//�õ��˿͵Ļ�����Ϣ(ID���������ֻ���)
	public List<Goods> getAllGoods();//�鿴������Ʒ
	public float buyGoods(Customer customer, int printflag[]);
	public Boolean addCustomer();
	public Boolean addGoods();
	public Boolean Goodscommon(Customer customer);
}
