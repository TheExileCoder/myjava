package com.ssdut.SecondHand.service;
import com.ssdut.SecondHand.entity.*;
import java.util.*;

public interface ManagerService {
	public Manager login();//����Ա��½
	public Boolean updateMyInfo(Manager manager);//�޸��Լ�����Ϣ
	public void getMyInfo(Manager manager);//�õ��Լ��Ļ�����Ϣ
	public List<Customer> getAllCustomer();//�õ�����˾����Ϣ(��������Ŀǰ�Ĺ���)
	public Customer getOneCustomer();//�õ�ĳһ��˾���Ļ�����Ϣ(��������Ŀǰ�Ĺ���)
	public Boolean updateCustomerPassWord();//�޸�ĳһ��˾���ĵ�½����(��˾�����ǵ�¼����������)
	public Boolean delCustomer();//ɾ��ĳһ��˾��
	public Boolean addCustomer();//���ĳһ��˾��
	public List<Goods> getAllGoods();//�鿴������Ʒ
	public Goods getOneGoods();//�õ�ĳһ��Ʒ��Ϣ
	public Boolean delGoods();//ɾ��ĳһ��Ʒ
	public Boolean addGoods();//���ĳһ��Ʒ
	
	public Boolean addManager();//��ӹ���Ա
	
	public Boolean updateGoods();

}
