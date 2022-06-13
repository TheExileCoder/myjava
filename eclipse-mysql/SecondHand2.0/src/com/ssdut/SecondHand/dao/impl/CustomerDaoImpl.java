package com.ssdut.SecondHand.dao.impl;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssdut.SecondHand.dao.BaseDao;
import com.ssdut.SecondHand.dao.CustomerDao;
import com.ssdut.SecondHand.entity.Customer;

public class CustomerDaoImpl extends BaseDao implements CustomerDao{
	private Connection conn = null; // �������ݿ�����
	private PreparedStatement pstmt = null; // ����ִ��SQL���
	private ResultSet rs = null; // �û������ѯ���Ĺ˿ͽ����
	/**
	 * @Description TODO �õ��������й˿ͻ�����Ϣ�ķ��ͼ���
	 * @param 
	 * @return List<Customer>
	 */
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			String preparedSql = "select * from customer";// SQL���
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			rs = pstmt.executeQuery(); // ִ��SQL��䣬�õ������
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setPassWord(rs.getString(3));
				customer.setPhoneId(rs.getString(4));
				
				customerList.add(customer);// �ѽ���������������ӵ����ͼ���
			}
		} catch (SQLException e) {
			e.printStackTrace();//����SQLException�쳣
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//����ClassNoFoundException�쳣
		} finally {
			super.closeAll(conn, pstmt, rs);//�ͷ���Դ
		}
		return customerList;
	}

	

	/**
	 * @Description TODO ���¹˿ͱ����Ϣ
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return int
	 */
	@Override
	public int updateCustomer(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);//������Ӱ�������
		return count;
	}
	/**
	 * @Description TODO ���ݲ�ѯ�����õ�ĳһ���˿�
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return Customer
	 */
	@Override
	public Customer getOneCustomer(String sql, Object[] param) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���
			while (rs.next()) {//�Ѳ�ѯ���Ĺ˿͵Ļ�����Ϣ��ֵ���������ص�Customer
				customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setPassWord(rs.getString(3));
				customer.setPhoneId(rs.getString(4));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();//����SQLException�쳣
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//����ClassNoFoundException�쳣
		} finally {
			super.closeAll(conn, pstmt, rs);//�ͷ���Դ
		}
		return customer;
	}
	
	//�û�ע��
	@Override
	public Boolean registerCustmer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("��������������:");
		String newCustomerName = scanner.next();
		System.out.println("��������������:");
		String newCustomerPassWord = scanner.next();
		System.out.println("�����������ֻ�����(11λ):");
		String newCustomerPhoneId = scanner.next();
		
		String sql = "insert into customer values (null, ?, ?, ?)";//SQL ���
		String[] param = {newCustomerName, newCustomerPassWord, newCustomerPhoneId};//���� SQL ����еġ������������ַ�������
		
		int count = updateCustomer(sql, param);
		System.out.println("��ӳɹ���������"+count+"�����ݡ�");
		return true;
	}

}
