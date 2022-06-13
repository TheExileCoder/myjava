package com.ssdut.SecondHand.dao;
import java.util.*;
import com.ssdut.SecondHand.entity.Manager;

public interface ManagerDao {
	public List<Manager> getAllManager();//查询出所有管理员
	public List<Manager> getKindManager(String sql, Object[] param);//根据查询条件查询管理员
	public Manager getOneManager(String sql, Object[] param);//根据查询条件查询某个管理员(用于管理员登录时)
	public int updateManager(String sql, Object[] param);//更新管理员信息
	
}
