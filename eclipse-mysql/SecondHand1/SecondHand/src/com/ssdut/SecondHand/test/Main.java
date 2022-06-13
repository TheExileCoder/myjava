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
		System.out.println("----------------------欢迎使用二手交易系统-----------------");
		System.out.println("请选择：\n1 管理员登录\n2 用户登录\n3 用户注册");
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
				System.out.println("输入有误，请按照规定输入！");
				System.out.println("请选择：\n1  管理员登录\n2 用户登录");
				flag = true;
			}
		}
	}
	private static Manager managerLogin() {
		ManagerService managerService = new ManagerServiceImpl();
		Manager manager = managerService.login();
		while (null == manager) {
			System.out.println("登陆失败！请重新输入。");
			manager = managerService.login();
		}
		//登陆成功
		ManagerChoose(manager);
		return manager;
	}
	/**
	 * @Description TODO 管理员是否退出
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void IsManagerLogOut(Manager manager) {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
		{
			
			ManagerChoose(manager);
		}
		else
		{
			System.out.println("您已成功退出系统");
		}
	}
	/**
	 * @Description TODO 管理员操作选择
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerChoose(Manager manager) {
		System.out.println("1：修改我的信息");
		System.out.println("2：我的基本信息");
		System.out.println("3：所有用户的基本信息");
		System.out.println("4：某个用户的基本信息");
		System.out.println("5：修改某个用户的登陆密码");
		System.out.println("6：删除用户");
		System.out.println("7：添加用户");
		System.out.println("8：删除商品");
		System.out.println("9：添加商品");
		System.out.println("10：查看所有商品");
		System.out.println("11：添加管理员");
		System.out.println("12：查看某一商品");
		System.out.println("13：更新商品信息");
		System.out.println("请根据需要执行的操作，选择序号输入，退出请输入0");
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		int type;
		while(flag) {
			type = scanner.nextInt();
			switch(type) {
			case 0:
				System.out.println("退出成功！");
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
				System.out.println("输入有误，请重新输入。");
				flag = true;
				break;
			}
		}
	}
	/**
	 * @Description TODO 修改我的密码
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerUpdateInfo(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.updateMyInfo(manager);
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 我的基本信息
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerShowMyInfo(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.getMyInfo(manager);
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 所有司机的基本信息(包括工资)
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerGetAllDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		List<Customer> customerList = new ArrayList<Customer>();
		customerList = managerService.getAllCustomer();
		Customer customer = null;
		System.out.println("编号\t姓名\t密码\t\t 手机号");
		for(int i = 0; i < customerList.size(); i++) {
			customer = customerList.get(i);
			System.out.println(customer.getCustomerId()+"\t"+customer.getCustomerName()+"\t"+customer.getPassWord()+"\t\t "+customer.getPhoneId());
		}
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 某个司机的基本信息(包括工资)
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerGetOneDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		Customer customer = null;
		customer = managerService.getOneCustomer();
		if(null == customer)
			System.out.println("没有用户的编号和您输入的编号相符。");
		else {
			System.out.println("编号\t姓名\t密码\t\t 手机号");
			System.out.println(customer.getCustomerId()+"\t"+customer.getCustomerName()+"\t"+customer.getPassWord()+"\t\t "+customer.getPhoneId());
		}
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 修改某个司机的登陆密码
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerUpdateDriverPassWord(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.updateCustomerPassWord();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 删除司机
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerDelDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.delCustomer();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 添加司机
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerAddDriver(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.addCustomer();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 删除出租车
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerDelGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.delGoods();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 添加出租车
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerAddGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.addGoods();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 添加司机工作记录
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerGetAllGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList = managerService.getAllGoods();
		Goods goods = null;
		System.out.println("编号\t名称\t\t数量\t价格");
		for(int i = 0; i < goodsList.size(); i++) {
			goods = goodsList.get(i);
			System.out.println(goods.getGoodsId()+"\t"+goods.getGoodsName()+" \t"+goods.getGoodsNum()+"\t"+goods.getGoodsPrice());
		}
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 添加管理员
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerAddManager(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		managerService.addManager();
		IsManagerLogOut(manager);
	}
	/**
	 * @Description TODO 显示所有工作记录
	 * @param manager 已登录的管理员
	 * @return void
	 * @throws
	 */
	private static void ManagerGetOneGoods(Manager manager) {
		ManagerService managerService = new ManagerServiceImpl();
		Goods goods = null;
		goods = managerService.getOneGoods();
		if(null == goods)
			System.out.println("没有商品的编号和您输入的编号相符。");
		else {
			System.out.println("编号\t名称\t\t数量\t 价格");
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
			System.out.println("登陆失败！请重新输入。");
			customer = customerService.login();
		}
		//登陆成功
		CustomerChoose(customer);
		return customer;
	}
	
	private static void IsCustomerLogOut(Customer customer) {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y")) {
			CustomerChoose(customer);
		}
		else
		{
			System.out.println("您已成功退出系统");
		}
	}
	
	private static void CustomerChoose(Customer customer) {
		System.out.println("1：修改我的信息");
		System.out.println("2：我的基本信息");
		System.out.println("3：查看所有商品");
		System.out.println("4：购买商品");
		System.out.println("请根据需要执行的操作，选择序号输入，退出请输入0");
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		int type;
		while(flag) {
			type = scanner.nextInt();
			switch(type) {
			case 0:
				System.out.println("退出成功！");
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
				System.out.println("输入有误，请重新输入。");
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
		System.out.println("编号\t名称\t数量\t价格");
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
			System.out.println("是否继续购买？\t若是请输入y,退出请按任意键");
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
		System.out.println("您需要支付"+totalpay+"元");
	}
	
	/*=================================================================================================*/
	
	private static void customerRegister() {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.registerCustmer();
		Scanner scanner = new Scanner(System.in);
		System.out.println("请选择：\n1 管理员登录\n2 用户登录\n3 用户注册");
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
				System.out.println("输入有误，请按照规定输入！");
				System.out.println("请选择：\n1  管理员登录\n2 用户登录\n3 用户注册");
				flag = true;
			}
		}
	}

}
