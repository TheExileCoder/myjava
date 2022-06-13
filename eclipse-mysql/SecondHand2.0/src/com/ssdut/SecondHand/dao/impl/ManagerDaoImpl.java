package com.ssdut.SecondHand.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssdut.SecondHand.dao.BaseDao;
import com.ssdut.SecondHand.dao.ManagerDao;
import com.ssdut.SecondHand.entity.Manager;

public class ManagerDaoImpl extends BaseDao implements ManagerDao {
	private Connection conn = null; // �������ݿ�����
	private PreparedStatement pstmt = null; // ����ִ��SQL���
	private ResultSet rs = null; // �û������ѯ���Ĺ���Ա�����
	/**
	 * @Description TODO �õ��������й���Ա������Ϣ�ķ��ͼ���
	 * @param 
	 * @return List<Manager>
	 */
	@Override
	public List<Manager> getAllManager() {
		// TODO Auto-generated method stub
		List<Manager> managerList = new ArrayList<Manager>();
		try {
			String preparedSql = "select * from manager";// SQL���
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			rs = pstmt.executeQuery(); // ִ��SQL���
			while (rs.next()) {
				Manager manager = new Manager();
				manager.setManagerId(rs.getInt(1));
				manager.setManagerName(rs.getString(2));
				manager.setPassWord(rs.getString(3));
				manager.setPhoneId(rs.getString(4));
				managerList.add(manager);// �ѽ���������������ӵ����ͼ���
			}
		} catch (SQLException e) {
			e.printStackTrace();//����SQLException�쳣
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//����ClassNotFoundException�쳣
		} finally {
			super.closeAll(conn, pstmt, rs);//�ͷ���Դ
		}
		return managerList;
	}
	/**
	 * @Description TODO ���ݲ�ѯ�����õ�����һ�����Ա������Ϣ�ķ��ͼ���
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return List<Manager>
	 */
	@Override
	public List<Manager> getKindManager(String sql, Object[] param) {
		// TODO Auto-generated method stub
		List<Manager> managerList = new ArrayList<Manager>();
		try {
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���
			while (rs.next()) {
				Manager manager = new Manager();
				manager.setManagerId(rs.getInt(1));
				manager.setManagerName(rs.getString(2));
				manager.setPassWord(rs.getString(3));
				manager.setPhoneId(rs.getString(4));
				managerList.add(manager);// �ѽ���������������ӵ����ͼ���
			}
		} catch (SQLException e) {
			e.printStackTrace();//����SQLException�쳣
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//����ClassNotFoundException�쳣
		} finally {
			super.closeAll(conn, pstmt, rs);//�ͷ���Դ
		}
		return managerList;
	}
	/**
	 * @Description TODO ���¹���Ա�����Ϣ(��������ɾ���ģ�û�в�)
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return int
	 */
	@Override
	public int updateManager(String sql, Object[] param) {
		// TODO Auto-generated method stub
		int count = super.executeSQL(sql, param);//������Ӱ�������
		return count;
	}
	/**
	 * @Description TODO ���ݲ�ѯ�����õ�ĳһ������Ա
	 * @param sql Ԥ����� SQL ���
	 * @param param Ԥ����� SQL ����еġ������������ַ�������
	 * @return Manager
	 */
	@Override
	public Manager getOneManager(String sql, Object[] param) {
		// TODO Auto-generated method stub
		Manager manager = null;
		try {
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(sql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���
			while (rs.next()) {
				manager = new Manager();//�Ѳ�ѯ���Ĺ���Ա�Ļ�����Ϣ��ֵ���������ص�manager
				manager.setManagerId(rs.getInt(1));
				manager.setManagerName(rs.getString(2));
				manager.setPassWord(rs.getString(3));
				manager.setPhoneId(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();//����SQLException�쳣
		} catch (ClassNotFoundException e) {
			e.printStackTrace();//����ClassNotFoundException�쳣
		} finally {
			super.closeAll(conn, pstmt, rs);//�ͷ���Դ
		}
		return manager;
	}

}
