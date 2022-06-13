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
	public float buyGoods(Customer customer, int printflag[]) {
		float pay = 0;
		Goods goods = null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("���������빺�����Ʒ�ı��:");
		int goodsId = scanner.nextInt();
		//�˿��������һ����Ʒ
		if(goodsDao.soldout(goodsId) == 1) {
			System.out.println("����ɹ�����Ʒ�ۿգ���鿴������Ʒ�б�");
			String sql = "select * from goods where goodsId = ?";//SQL���
			String[] param = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
			goods = goodsDao.getOneGoods(sql, param);
			pay = goods.getGoodsPrice();
			String sql1 = "delete from goods where goodsId = ?";//SQL ��䣬������Ʒ�����ݿ���ɾ��
			String[] param1 = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
			int count = goodsDao.updateGoods(sql1, param1);
			String sql_update_goods = "update goods set goodsId = goodsId - 1 where goodsId > ?";//������Ʒ��id
			String[] param_update_goodStrings = {String.valueOf(goodsId)};
			goodsDao.updateGoods(sql_update_goods, param_update_goodStrings);
			printflag[0] = 1;
		}
		//��Ʒ��Ŵ���
		else if (goodsDao.soldout(goodsId) == 2) {
			System.out.println("����ʧ�ܣ���Ʒ��Ŵ���");
		}
		//��������
		else {
			String sql = "select * from goods where goodsId = ?";//SQL���
			String[] param = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
			goods = goodsDao.getOneGoods(sql, param);
			pay = goods.getGoodsPrice();
			String sql1 = "update goods set goodsNum = goodsNum-1 where goodsId = ?";//SQL���
			String[] param1 = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
			goodsDao.updateGoods(sql1, param1);
			System.out.println("����ɹ���");
		}
		return pay;
	}

	public Boolean addCustomer() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û�������:");
		String newCustomerName = scanner.next();
		System.out.println("�������û�������:");
		String newCustomerPassWord = scanner.next();
		System.out.println("�������û����ֻ�����(11λ):");
		String newCustomerPhoneId = scanner.next();
		String sql = "insert into customer values (null,?, ?, ?)";//SQL ���
		String[] param = {newCustomerName, newCustomerPassWord, newCustomerPhoneId};//���� SQL ����еġ������������ַ�����
		int count = customerDao.updateCustomer(sql, param);
		System.out.println("ע��ɹ���");
		return true;
	}
	
	public Boolean addGoods() {
		// TODO Auto-generated method stub
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("���������������Ʒ����:");
		String newGoodsName = scanner.next();
		System.out.println("������������ӵ���Ʒ����:");
		int newGoodsNum = scanner.nextInt();
		System.out.println("������������ӵ���Ʒ�ļ۸�:(2λ����)");
		float newGoodsPrice = scanner.nextFloat();
		System.out.println("��������Ʒ��ʹ��ʱ��������λ:�죩");
		int newGoodsusedday = scanner.nextInt();
		String sql = "insert into goods values ((select * from(select max(goodsId) from goods)as g)+1,?,?,?,?,null)";//SQL ���
		String[] param = {newGoodsName,String.valueOf(newGoodsNum),String.valueOf(newGoodsPrice),String.valueOf(newGoodsusedday)};
		int count = goodsDao.updateGoods(sql, param);
		if(0 != count)
			System.out.println("��ӳɹ���������"+count+"�����ݡ�");
		return true;
	}

	public Boolean Goodscommon(Customer customer){
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("���������Ʒ���:");
		int goodsId = scanner.nextInt();
		int uid = customer.getCustomerId();
		System.out.println("����������:");
		String common = scanner.next();
		String sql = "UPDATE goods SET goodscommon=? WHERE goodsId=?";//SQL ���
		String[] param ={common,String.valueOf(goodsId)};
		int count = goodsDao.updateGoods(sql, param);
		if(0 != count)
			System.out.println("�޸ĳɹ���������"+count+"�����ݡ�");
		return true;
	}
}





