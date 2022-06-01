package com.service;

import com.bean.User;
import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.util.JDBCUtil;

import java.sql.Connection;

public class UserService {
	public final static UserService intance = new UserService();

	public static UserService getInstance() {
		return intance;
	}

	public boolean addUser(User user) {
		try {
			Connection conn = JDBCUtil.getConnection();
			UserDao ud=new UserDaoImpl(conn);
			return ud.addUser(user);
		} catch (Exception e) {
		}
		return false;
	}

	public boolean queryByName(String username) {
		try {
			Connection conn = JDBCUtil.getConnection();
			UserDao ud=new UserDaoImpl(conn);
			return ud.queryByName(username);
		} catch (Exception e) {
		}
		return false;
	}

	public User login(User user) {
		try {
			Connection conn = JDBCUtil.getConnection();
			UserDao ud=new UserDaoImpl(conn);
			return ud.login(user);
		} catch (Exception e) {
		}
		return null;
	}

}
