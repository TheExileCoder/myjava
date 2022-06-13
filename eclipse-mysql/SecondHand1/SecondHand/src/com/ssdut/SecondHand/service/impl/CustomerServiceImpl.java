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
	System.out.println("\t�û���¼");
	System.out.println("��������������:");
	String customerName = scanner.next();
	System.out.println("��������������:");
	String customerPassWord = scanner.next();
	CustomerDao customerDao = new CustomerDaoImpl();
	String sql = "select * from customer where customerName = ? and passWord = ?";//SQL ���
	String[] param = {customerName, customerPassWord};//���� SQL ����еġ������������ַ�������
	Customer customer = customerDao.getOneCustomer(sql, param);
	if(null != customer) {//����½�ɹ���չʾ������Ϣ
		System.out.println("-------��ϲ���ɹ���¼-------");
		System.out.println("-------���Ļ�����Ϣ��-------");
		System.out.println("��ţ�" + customer.getCustomerId());
		System.out.println("������" + customer.getCustomerName());
		System.out.println("�ֻ��ţ�" + customer.getPhoneId());
		
	}
	return customer;
	}
	
	@Override
	public Boolean updateMyInfo(Customer customer) {
	// TODO Auto-generated method stub
	Scanner scanner = new Scanner(System.in);
	System.out.println("��������Ҫ�޸ĵ�����(����С��20���ַ�):");
	String customerNewPassWord = scanner.next();
	System.out.println("��������Ҫ�޸ĵĵ绰����(����11λ):");
	String customerNewphonenum = scanner.next();
	CustomerDao customerDao = new CustomerDaoImpl();
	String sql = "update customer set passWord = ?, phonenum = ? where customerId = ? and (passWord <> ? or phonenum <> ?)";//SQL ���
	//���� SQL ����еġ������������ַ�������
	String[] param = {customerNewPassWord, customerNewphonenum, String.valueOf(customer.getCustomerId()),customerNewPassWord,customerNewphonenum};
	int count = customerDao.updateCustomer(sql, param);//��Ӱ�������
	if(count == 0)
		System.out.println("�޸�ʧ��,����͵绰������ԭ����ͬ");
	else
		System.out.println("�޸ĳɹ���������"+count+"�����ݡ�");
	return true;
	}
	
	
	@Override
	public void getMyInfo(Customer customer) {
	// TODO Auto-generated method stub
	System.out.println("��ţ�" + customer.getCustomerId());
	System.out.println("������" + customer.getCustomerName());
	System.out.println("�ֻ����룺" + customer.getPhoneId());
	
	}
	
	//�鿴������Ʒ
		public List<Goods> getAllGoods(){
			List<Goods> goodsList = new ArrayList<Goods>();
			GoodsDao goodsDao = new GoodsDaoImpl();
			goodsList = goodsDao.getAllGoods();
			return goodsList;
		}
	//������Ʒ	
		public float buyGoods() {
			float pay = 0;
			Goods goods = null;
			GoodsDao goodsDao = new GoodsDaoImpl();
			Scanner scanner = new Scanner(System.in);
			System.out.println("���������빺�����Ʒ�ı��:");
			int goodsId = scanner.nextInt();
			String sql = "select * from goods where goodsId = ?";//SQL���
			String[] param = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
			goods = goodsDao.getOneGoods(sql, param);
			
			if(goodsDao.soldout(goodsId)==false) {
				System.out.println("����ʧ�ܣ���Ʒ���ۿգ�");
			}
			else {
				pay = goods.getGoodsPrice();
				String sql1 = "update goods set goodsNum = goodsNum-1 where goodsId = ?";//SQL���
				String[] param1 = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
				goodsDao.updateGoods(sql1, param1);
				System.out.println("����ɹ���");
			}
			
			return pay;
			
		}

}




