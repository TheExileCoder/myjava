package com.ssdut.SecondHand.dao;
import java.sql.*;
import java.util.*;
import java.io.*;
/** 

 * @author jqn
 * @date 2021��8��5��
 * @Description TODO ����JDBC��װ������ҵ���߼���������ݷ��ʴ���
 * @Package com.ssdut.SecondHnad.dao
   */
public class BaseDao {
	public static String DRIVER;	//���ݿ�����
    public static String URL;	//url
    public static String DBNAME;	//���ݿ��û���
    public static String DBPASS;	//���ݿ�����
    static {//��̬�����,������ص�ʱ��ִ��
    	init();
    }
    /**
    * @Description TODO ��ʼ�����Ӳ���,�������ļ�����
    * @param 
    * @return void
    * @throws
      */
    public static void init() {
    	Properties params = new Properties();
    	String config = "database.properties";//�����ļ�·��
    	InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(config);
    	//���������ļ�����������
    	try {
    		//���������ж�ȡ�����б�
    		params.load(is);
    	}catch(IOException e) {
    		e.printStackTrace();
    	 }
    	//����ָ���Ļ�ȡ��Ӧ��ֵ
    	DRIVER = params.getProperty("driver");
    	URL = params.getProperty("url");
    	DBNAME = params.getProperty("user");
    	DBPASS = params.getProperty("password");
    }
      /**
    * @Description TODO �õ����ݿ������
    * @param 
    * @return Connection
    * @throws ClassNotFoundException
    * @throws SQLException
      */
    
    public Connection getConn() throws ClassNotFoundException,SQLException{
    	Connection conn = null;
    	try {
    		Class.forName(DRIVER);// ע������
    		conn=DriverManager.getConnection(URL,DBNAME,DBPASS);// ������ݿ�����
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return conn;// ��������
    }
      /**
    * @Description TODO �ͷ���Դ
    * @param conn ���ݿ�����
    * @param pstmt PreparedStatement����
    * @param rs �����
    * @return void
    * @throws 
      */
    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
    	/* ���rs���գ��ر�rs */
    	if(rs != null) {
    		try {
    			rs.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	/* ���pstmt���գ��ر�pstmt */
    	if(pstmt != null) {
    		try {
    			pstmt.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	/* ���conn���գ��ر�conn */
    	if(conn != null) {
    		try {
    			conn.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
      /**
    * @Description TODO ִ����ɾ�Ĳ���������ִ�в�ѯ
    * @param prepareSql Ԥ����� SQL ���
    * @param param Ԥ����� SQL ����еġ������������ַ�������
    * @return int
    * @throws ��Ӱ�������
      */
    public int executeSQL(String prepareSql,Object[] param) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int num = 0;
        /* ����SQL,ִ��SQL */
        try {
        	conn = getConn();// �õ����ݿ�����
        	pstmt = conn.prepareStatement(prepareSql);// �õ�PreparedStatement����
        	if(param != null) {
        		for(int i = 0;i < param.length; i++) {
        			pstmt.setObject(i + 1, param[i]);// ΪԤ����sql���ò���
        		}
        	}
        	num = pstmt.executeUpdate();// ִ��SQL���
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();// ����ClassNotFoundException�쳣
        }catch(SQLException e) {
        	e.printStackTrace();// ����SQLException�쳣
        }finally {
        	this.closeAll(conn, pstmt, null);// �ͷ���Դ
        }
        return num;     
    }
}