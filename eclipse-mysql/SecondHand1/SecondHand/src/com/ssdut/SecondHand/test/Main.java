package com.ssdut.SecondHand.test;
import com.ssdut.SecondHand.dao.*;

import com.ssdut.SecondHand.dao.impl.*;
import com.ssdut.SecondHand.entity.*;
import com.ssdut.SecondHand.service.*;
import com.ssdut.SecondHand.service.impl.*;

import java.util.*;
public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main.startSH();
	}
	private static void startSH() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------------------��ӭʹ�ö��ֽ���ϵͳ-----------------");
		System.out.println("��ѡ��\n1 ����Ա��¼\n2 �û���¼\n3 �û�ע��");
		System.out.println("********************************************************************");
		Boolean flag = true;
		String type;
		while(true) {
			type = scanner.next();
			if("1".equals(type)) {
				Main.managerLogin();
				flag = false;
			}else if("2".equals(type)) {
				Main.customerLogin();
				flag = false;
			}else if("3".equals(type)) {
				Main.customerRegister();
			}
			else {
				System.out.println("���������밴�չ涨���룡");
				System.out.println("��ѡ��\n1  ����Ա��¼\n2 �û���¼");
				flag = true;
			}
		}
	}
	private static Manager managerLogin() {
		ManagerService managerService = new ManagerServiceImpl();
		Manager manager = managerService.login();
		while (null == manager) {
			System.out.println("��½ʧ�ܣ����������롣");
			manager = managerService.login();
		}
		//��½�ɹ�
		ManagerChoose(manager);
		return manager;
	}
	/**
	 * @Description TODO ����Ա�Ƿ��˳�
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void IsManagerLogOut(Manager manager) {
		System.out.println("���Ƿ����������������������y,�˳��밴�����");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
		{
			
			ManagerChoose(manager);
		}
		else
		{
			System.out.println("���ѳɹ��˳�ϵͳ");
		}
	}
	/**
	 * @Description TODO ����Ա����ѡ��
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerChoose(Manager manager) {
		System.out.println("1���޸��ҵ���Ϣ");
		System.out.println("2���ҵĻ�����Ϣ");
		System.out.println("3�������û��Ļ�����Ϣ");
		System.out.println("4��ĳ���û��Ļ�����Ϣ");
		System.out.println("5���޸�ĳ���û��ĵ�½����");
		System.out.println("6��ɾ���û�");
		System.out.println("7������û�");
		System.out.println("8��ɾ����Ʒ");
		System.out.println("9�������Ʒ");
		System.out.println("10���鿴������Ʒ");
		System.out.println("11����ӹ���Ա");
		System.out.println("12���鿴ĳһ��Ʒ");
		System.out.println("13��������Ʒ��Ϣ");
		System.out.println("�������Ҫִ�еĲ�����ѡ��������룬�˳�������0");
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		int type;
		while(flag) {
			type = scanner.nextInt();
			switch(type) {
			case 0:
				System.out.println("�˳��ɹ���");
				flag = false;
				break;
			case 1:
				Main.ManagerUpdateInfo(manager);
				flag = false;
				break;
			case 2:
				Main.ManagerShowMyInfo(manager);
				flag = false;
				break;
			case 3:
				Main.ManagerGetAllDriver(manager);
				flag = false;
				break;
			case 4:
				Main.ManagerGetOneDriver(manager);
				flag = false;
				break;
			case 5:
				Main.ManagerUpdateDriverPassWord(manager);
				flag = false;
				break;
			case 6:
				Main.ManagerDelDriver(manager);
				flag = false;
				break;
			case 7:
				Main.ManagerAddDriver(manager);
				flag = false;
				break;
			case 8:
				Main.ManagerDelGoods(manager);
				flag = false;
				break;
			case 9:
				Main.ManagerAddGoods(manager);
				flag = false;
				break;
			case 10:
				Main.ManagerGetAllGoods(manager);
				flag = false;
				break;
			case 11:
				Main.ManagerAddManager(manager);
				flag = false;
				break;
			case 12:
				Main.ManagerGetOneGoods(manager);
				flag = false;
				break;
			case 13:
				Main.ManagerUpdateGoods(manager);
				flag = false;
				break;
		
			default:
				System.out.println("�����������������롣");
				flag = true;
				break;
			}
		}
	}
	/**
	 * @Description TODO �޸��ҵ�����
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerUpdateInfo(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.updateMyInfo(manager);
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO �ҵĻ�����Ϣ
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerShowMyInfo(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.getMyInfo(manager);
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ����˾���Ļ�����Ϣ(��������)
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerGetAllDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		List<Customer> customerList = new ArrayList<Customer>();
		customerList = managerService.getAllCustomer();
		Customer customer = null;
		System.out.println("���\t����\t����\t\t �ֻ���");
		for(int i = 0; i < customerList.size(); i++) {
			customer = customerList.get(i);
			System.out.println(customer.getCustomerId()+"\t"+customer.getCustomerName()+"\t"+customer.getPassWord()+"\t\t "+customer.getPhoneId());
		}
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ĳ��˾���Ļ�����Ϣ(��������)
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerGetOneDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		Customer customer = null;
		customer = managerService.getOneCustomer();
		if(null == customer)
			System.out.println("û���û��ı�ź�������ı�������");
		else {
			System.out.println("���\t����\t����\t\t �ֻ���");
			System.out.println(customer.getCustomerId()+"\t"+customer.getCustomerName()+"\t"+customer.getPassWord()+"\t\t "+customer.getPhoneId());
		}
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO �޸�ĳ��˾���ĵ�½����
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerUpdateDriverPassWord(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.updateCustomerPassWord();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ɾ��˾��
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerDelDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.delCustomer();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ���˾��
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerAddDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.addCustomer();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ɾ�����⳵
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerDelGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.delGoods();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ��ӳ��⳵
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerAddGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.addGoods();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ���˾��������¼
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerGetAllGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList = managerService.getAllGoods();
		Goods goods = null;
		System.out.println("���\t����\t\t����\t�۸�");
		for(int i = 0; i < goodsList.size(); i++) {
			goods = goodsList.get(i);
			System.out.println(goods.getGoodsId()+"\t"+goods.getGoodsName()+" \t"+goods.getGoodsNum()+"\t"+goods.getGoodsPrice());
		}
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ��ӹ���Ա
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerAddManager(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.addManager();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO ��ʾ���й�����¼
	 * @param manager �ѵ�¼�Ĺ���Ա
	 * @return void
	 * @throws
	 */
	private static void ManagerGetOneGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		Goods goods = null;
		goods = managerService.getOneGoods();
		if(null == goods)
			System.out.println("û����Ʒ�ı�ź�������ı�������");
		else {
			System.out.println("���\t����\t\t����\t �۸�");
			System.out.println(goods.getGoodsId()+"\t"+goods.getGoodsName()+"\t"+goods.getGoodsNum()+"\t "+goods.getGoodsPrice());
		}
		IsManagerLogOut(manager);
	}
	
	private static void ManagerUpdateGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.updateGoods();
		IsManagerLogOut(manager);
	}
	
	/*=================================================================================================*/
	
	private static Customer customerLogin() {
		CustomerService customerService = new CustomerServiceImpl();
		Customer customer = customerService.login();
		while (null == customer) {
			System.out.println("��½ʧ�ܣ����������롣");
			customer = customerService.login();
		}
		//��½�ɹ�
		CustomerChoose(customer);
		return customer;
	}
	
	private static void IsCustomerLogOut(Customer customer) {
		System.out.println("���Ƿ����������������������y,�˳��밴�����");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y")) {
			CustomerChoose(customer);
		}
		else
		{
			System.out.println("���ѳɹ��˳�ϵͳ");
		}
	}
	
	private static void CustomerChoose(Customer customer) {
		System.out.println("1���޸��ҵ���Ϣ");
		System.out.println("2���ҵĻ�����Ϣ");
		System.out.println("3���鿴������Ʒ");
		System.out.println("4��������Ʒ");
		System.out.println("�������Ҫִ�еĲ�����ѡ��������룬�˳�������0");
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		int type;
		while(flag) {
			type = scanner.nextInt();
			switch(type) {
			case 0:
				System.out.println("�˳��ɹ���");
				flag = false;
				break;
			case 1:
				Main.CustomerUpdateInfo(customer);
				flag = false;
				break;
			case 2:
				Main.CustomerShowMyInfo(customer);
				flag = false;
				break;
			case 3:
				Main.CustomerGetAllGoods(customer);
				flag = false;
				break;
			case 4:
				Main.CustomerBuyGoods(customer);
				flag = false;
				break;
			default:
				System.out.println("�����������������롣");
				flag = true;
				break;
			}
		}
	}
	
	private static void CustomerUpdateInfo(Customer customer) {
		CustomerService customerService = new CustomerServiceImpl();
		customerService.updateMyInfo(customer);
		IsCustomerLogOut(customer);
	}
	
	private static void CustomerShowMyInfo(Customer customer) {
		CustomerService customerService = new CustomerServiceImpl();
		customerService.getMyInfo(customer);
		IsCustomerLogOut(customer);
	}
	
	private static void CustomerGetAllGoods(Customer customer) {
		CustomerService customerService = new CustomerServiceImpl();
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList = customerService.getAllGoods();
		Goods goods = null;
		System.out.println("���\t����\t����\t�۸�");
		for(int i = 0; i < goodsList.size(); i++) {
			goods = goodsList.get(i);
			System.out.println(goods.getGoodsId()+"\t"+goods.getGoodsName()+" \t"+goods.getGoodsNum()+"\t"+goods.getGoodsPrice());
		}
		IsCustomerLogOut(customer);
	}
	
	private static void CustomerBuyGoods (Customer customer) {
		Boolean flag = true;
		String type;
		float totalpay = 0;
		CustomerService customerService = new CustomerServiceImpl();
			
		totalpay += customerService.buyGoods();
		while(flag) {
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("�Ƿ��������\t����������y,�˳��밴�����");
			type = scanner.next();
			if("y".equals(type)) {
				totalpay += customerService.buyGoods();
				flag = true;
			}
			else {
				flag = false;
			}
		}
		totalpay = (float)Math.round(totalpay*100)/100;
		System.out.println("����Ҫ֧��"+totalpay+"Ԫ");
	}
	
	/*=================================================================================================*/
	
	private static void customerRegister() {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.registerCustmer();
		Scanner scanner = new Scanner(System.in);
		System.out.println("��ѡ��\n1 ����Ա��¼\n2 �û���¼\n3 �û�ע��");
		System.out.println("********************************************************************");
		Boolean flag = true;
		String type;
		while(flag) {
			type = scanner.next();
			if("1".equals(type)) {
				Main.managerLogin();
				flag = false;
			}else if("2".equals(type)) {
				Main.customerLogin();
				flag = false;
			}else if("3".equals(type)) {
				Main.customerRegister();
				flag = true;
			}
			else {
				System.out.println("���������밴�չ涨���룡");
				System.out.println("��ѡ��\n1  ����Ա��¼\n2 �û���¼\n3 �û�ע��");
				flag = true;
			}
		}
	}

}
