package com.ssdut.SecondHand.dao;
import java.util.*;
import com.ssdut.SecondHand.entity.Manager;

public interface ManagerDao {
	public List<Manager> getAllManager();//��ѯ�����й���Ա
	public List<Manager> getKindManager(String sql, Object[] param);//���ݲ�ѯ������ѯ����Ա
	public Manager getOneManager(String sql, Object[] param);//���ݲ�ѯ������ѯĳ������Ա(���ڹ���Ա��¼ʱ)
	public int updateManager(String sql, Object[] param);//���¹���Ա��Ϣ
	
}
