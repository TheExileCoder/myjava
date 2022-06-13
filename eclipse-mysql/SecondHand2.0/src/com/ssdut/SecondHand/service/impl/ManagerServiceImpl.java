package com.ssdut.SecondHand.service.impl;
import java.util.*;

import com.ssdut.SecondHand.dao.*;
import com.ssdut.SecondHand.dao.impl.*;
import com.ssdut.SecondHand.entity.*;
import com.ssdut.SecondHand.service.ManagerService;
public class ManagerServiceImpl implements ManagerService{
	/**
	 * @Description TODO ����Ա��¼
	 * @param 
	 * @return Manager
	 */
	public Manager login() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("����Ա��¼");
		System.out.println("��������������:");
		String managerName = scanner.next();
		System.out.println("��������������:");
		String managerPassWord = scanner.next();
		ManagerDao managerDao = new ManagerDaoImpl();
		String sql = "select * from manager where managerName = ? and passWord = ?";// SQL���
		String[] param = {managerName,managerPassWord};//���� SQL ����еġ������������ַ�������
		Manager manager = managerDao.getOneManager(sql, param);
		if(null != manager) {//����¼�ɹ���չʾ������Ϣ
			System.out.println("-------��ϲ���ɹ���¼-------");
			System.out.println("-------���Ļ�����Ϣ��-------");
			System.out.println("��ţ�" + manager.getManagerId());
			System.out.println("������" + manager.getManagerName());
			System.out.println("�ֻ��ţ�" + manager.getPhoneId());
		}
		return manager;
	}
	/**
	 * @Description TODO ��������
	 * @param manager ִ�иò����Ĺ���Ա
	 * @return Boolean
	 */
	public Boolean updateMyInfo(Manager manager) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("��������Ҫ�޸ĵ�����(����С��20���ַ�):");
		String managerNewPassWord = scanner.next();
		System.out.println("��������Ҫ�޸ĵĵ绰����(����11λ):");
		String managerNewphonenum = scanner.next();
		ManagerDao managerDao = new ManagerDaoImpl();
		String sql = "update manager set passWord = ?, phonenum = ? where managerId = ? and (passWord <> ? or phonenum <> ?)";//SQL ���
		//���� SQL ����еġ������������ַ�������
		String[] param = {managerNewPassWord, managerNewphonenum, String.valueOf(manager.getManagerId()),managerNewPassWord,managerNewphonenum};
		int count = managerDao.updateManager(sql, param);//��Ӱ�������
		if(count == 0)
			System.out.println("�޸�ʧ��,����͵绰������ԭ����ͬ");
		else
			System.out.println("�޸ĳɹ���������"+count+"�����ݡ�");
		return true;
	}
	/**
	 * @Description TODO ��ʾ����Ա���˵Ļ�����Ϣ
	 * @param manager ִ�иò����Ĺ���Ա
	 * @return void
	 */
	
	public void getMyInfo(Manager manager) {
		// TODO Auto-generated method stub
		System.out.println("���:" + manager.getManagerId());
		System.out.println("����:" + manager.getManagerName());
		System.out.println("�ֻ�����:" + manager.getPhoneId());
	}
	
	/**
	 * @Description TODO ���ذ��������û��ķ��ͼ���
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
	 * @Description TODO �����û��ı�Ų���ĳһ���û�
	 * @param 
	 * @return customer
	 */
	
	public Customer getOneCustomer() {
		// TODO Auto-generated method stub
		Customer customer = null;
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����������ѯ���û��ı��:");
		int customerId = scanner.nextInt();
		String sql = "select * from customer where customerId = ?";//SQL���
		String[] param = {String.valueOf(customerId)};//���� SQL ����еġ������������ַ�������
		customer = customerDao.getOneCustomer(sql, param);
		return customer;
	}
	
	/**
	 * @Description TODO �޸��û��ĵ�¼����
	 * @param 
	 * @return Boolean
	 */
	
	public Boolean updateCustomerPassWord() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����������޸�������û��ı��:");
		int customerId = scanner.nextInt();
		System.out.println("����������Ϊ���û��޸ĵ�������:");
		String customerNewPassWord = scanner.next();
		String sql = "update customer set passWord = ? where customerId = ?";//SQL���
		String[] param = {customerNewPassWord,String.valueOf(customerId)};//���� SQL ����еġ������������ַ�������
		int count = customerDao.updateCustomer(sql, param);
		if(0 == count)
			System.out.println("������û��������");
		else
			System.out.println("�޸ĳɹ���������"+count+"�����ݡ�");
		return true;
	}
	
	/**
	 * @Description TODO ɾ��ĳһ���û�
	 * @param 
	 * @return Boolean
	 */
	
	public Boolean delCustomer() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("����������ɾ�����û��ı��:");
		int customerId = scanner.nextInt();
		String sql = "delete from customer where customerId = ?";//SQL ���
		String[] param = {String.valueOf(customerId)};//���� SQL ����еġ������������ַ�������
		
		int count = customerDao.updateCustomer(sql, param);
		if(0 == count)
			System.out.println("������û��������");
		else {
			String sql_update = "update customer set customerId = customerId - 1 where customerId > ?";
			String[] param_update = {String.valueOf(customerId)};
			customerDao.updateCustomer(sql_update, param_update);
			System.out.println("ɾ���ɹ���������"+count+"�����ݡ�");
		}

		return true;
	}
	/**
	 * @Description TODO ���һ���û�
	 * @param 
	 * @return Boolean
	 */
	public Boolean addCustomer() {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("������������ӵ��û�������:");
		String newCustomerName = scanner.next();
		System.out.println("������������ӵ��û�������:");
		String newCustomerPassWord = scanner.next();
		System.out.println("������������ӵ��û����ֻ�����(11λ):");
		String newCustomerPhoneId = scanner.next();
		
		String sql = "insert into customer values ((select * from(select max(customerId) from customer)as c)+1,?, ?, ?)";//SQL ���
		String[] param = {newCustomerName, newCustomerPassWord, newCustomerPhoneId};//���� SQL ����еġ������������ַ�����
		int count = customerDao.updateCustomer(sql, param);
		System.out.println("��ӳɹ���������"+count+"�����ݡ�");
		return true;
	}
	
	//�鿴������Ʒ
	public List<Goods> getAllGoods(){
		List<Goods> goodsList = new ArrayList<Goods>();
		GoodsDao goodsDao = new GoodsDaoImpl();
		goodsList = goodsDao.getAllGoods();
		return goodsList;
	}
	
	//�鿴ĳһ��Ʒ��Ϣ
	public Goods getOneGoods() {
		Goods goods = null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����������ѯ����Ʒ�ı��:");
		int goodsId = scanner.nextInt();
		String sql = "select * from goods where goodsId = ?";//SQL���
		String[] param = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
		goods = goodsDao.getOneGoods(sql, param);
		return goods;
	}
	
	/**
	 * @Description TODO ɾ����Ʒ
	 * @param 
	 * @return Boolean
	 */
	public Boolean delGoods() {
		// TODO Auto-generated method stub
		GoodsDao goodsDao = new GoodsDaoImpl();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("����������ɾ����Ʒ���:");
		int goodsId = scanner.nextInt();
		String sql = "delete from goods where goodsId = ?";//SQL ���
		String[] param = {String.valueOf(goodsId)};//���� SQL ����еġ������������ַ�������
		int count = goodsDao.updateGoods(sql, param);
		if(0 == count)
			System.out.println("�������Ʒ�������");
		else {
			String sql_updateString = "update Goods set goodsId = goodsId - 1 where goodsId > ?";
			String[] param_updateStrings = {String.valueOf(goodsId)};
			goodsDao.updateGoods(sql_updateString, param_updateStrings);
			System.out.println("ɾ���ɹ���������"+count+"�����ݡ�");
		}
					
		return true;
	}
	
	/**
	 * @Description TODO �����Ʒ
	 * @param 
	 * @return Boolean
	 */
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
	
	/**
	 * @Description TODO ��ӹ���Ա
	 * @param 
	 * @return Boolean
	 */
	public Boolean addManager() {
		// TODO Auto-generated method stub
		ManagerDao managerDao = new ManagerDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("������������ӵĹ���Ա������:");
		String newManagerName = scanner.next();
		System.out.println("������������ӵĹ���Ա������:");
		String newManagerPassWord = scanner.next();
		System.out.println("������������ӵĹ���Ա���ֻ�����(11λ):");
		String newManagerPhoneId = scanner.next();
		String sql = "insert into manager values (null, ?, ?, ?)";//SQL ���
		//���� SQL ����еġ������������ַ�������
		String[] param = {newManagerName, newManagerPassWord, newManagerPhoneId};
		int count = managerDao.updateManager(sql, param);
		System.out.println("��ӳɹ���������"+count+"�����ݡ�");
		return true;
	}
	
	//������Ʒ
	public Boolean updateGoods() {
		// TODO Auto-generated method stub
		GoodsDao goodsDao = new GoodsDaoImpl();
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����������޸ĵ���Ʒ���:");
		int goodsId = scanner.nextInt();
		System.out.println("�����������޸���Ʒ����:");
		String newGoodsName = scanner.next();
		System.out.println("�����������޸ĵ���Ʒ����:");
		int newGoodsNum = scanner.nextInt();
		System.out.println("�����������޸ĵ���Ʒ�ļ۸�:(2λ����)");
		float newGoodsPrice = scanner.nextFloat();	
		System.out.println("��������Ʒ��ʹ��ʱ��������λ:�죩");
		int newGoodsusedday = scanner.nextInt();
		String sql = "UPDATE goods SET goodsName=?, goodsNum=?, goodsPrice=?, goodsusedday=? WHERE goodsId=?";//SQL ���
		
		String[] param = {newGoodsName,String.valueOf(newGoodsNum),String.valueOf(newGoodsPrice),String.valueOf(newGoodsusedday),String.valueOf(goodsId)};
		int count = goodsDao.updateGoods(sql, param);
		if(0 != count)
			System.out.println("�޸ĳɹ���������"+count+"�����ݡ�");
		
		return true;
	}
}
