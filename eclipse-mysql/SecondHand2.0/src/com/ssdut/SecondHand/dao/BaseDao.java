package com.ssdut.SecondHand.dao;
import java.sql.*;
import java.util.*;
import java.io.*;
/** 

 * @author jqn
 * @date 2021年8月5日
 * @Description TODO 进行JDBC封装，隔离业务逻辑代码和数据访问代码
 * @Package com.ssdut.SecondHnad.dao
   */
public class BaseDao {
	public static String DRIVER;	//数据库驱动
    public static String URL;	//url
    public static String DBNAME;	//数据库用户名
    public static String DBPASS;	//数据库密码
    static {//静态代码块,在类加载的时候执行
    	init();
    }
    /**
    * @Description TODO 初始化连接参数,从配置文件里获得
    * @param 
    * @return void
    * @throws
      */
    public static void init() {
    	Properties params = new Properties();
    	String config = "database.properties";//配置文件路径
    	InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(config);
    	//加载配置文件到输入流中
    	try {
    		//从输入流中读取属性列表
    		params.load(is);
    	}catch(IOException e) {
    		e.printStackTrace();
    	 }
    	//根据指定的获取对应的值
    	DRIVER = params.getProperty("driver");
    	URL = params.getProperty("url");
    	DBNAME = params.getProperty("user");
    	DBPASS = params.getProperty("password");
    }
      /**
    * @Description TODO 得到数据库的连接
    * @param 
    * @return Connection
    * @throws ClassNotFoundException
    * @throws SQLException
      */
    
    public Connection getConn() throws ClassNotFoundException,SQLException{
    	Connection conn = null;
    	try {
    		Class.forName(DRIVER);// 注册驱动
    		conn=DriverManager.getConnection(URL,DBNAME,DBPASS);// 获得数据库连接
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return conn;// 返回连接
    }
      /**
    * @Description TODO 释放资源
    * @param conn 数据库连接
    * @param pstmt PreparedStatement对象
    * @param rs 结果集
    * @return void
    * @throws 
      */
    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
    	/* 如果rs不空，关闭rs */
    	if(rs != null) {
    		try {
    			rs.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	/* 如果pstmt不空，关闭pstmt */
    	if(pstmt != null) {
    		try {
    			pstmt.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	/* 如果conn不空，关闭conn */
    	if(conn != null) {
    		try {
    			conn.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
      /**
    * @Description TODO 执行增删改操作，不能执行查询
    * @param prepareSql 预编译的 SQL 语句
    * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组
    * @return int
    * @throws 受影响的行数
      */
    public int executeSQL(String prepareSql,Object[] param) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int num = 0;
        /* 处理SQL,执行SQL */
        try {
        	conn = getConn();// 得到数据库连接
        	pstmt = conn.prepareStatement(prepareSql);// 得到PreparedStatement对象
        	if(param != null) {
        		for(int i = 0;i < param.length; i++) {
        			pstmt.setObject(i + 1, param[i]);// 为预编译sql设置参数
        		}
        	}
        	num = pstmt.executeUpdate();// 执行SQL语句
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();// 处理ClassNotFoundException异常
        }catch(SQLException e) {
        	e.printStackTrace();// 处理SQLException异常
        }finally {
        	this.closeAll(conn, pstmt, null);// 释放资源
        }
        return num;     
    }
}