package com.dao;

import com.bean.User;

public interface UserDao {
	boolean addUser(User user) ;

	boolean queryByName(String username);

	User login(User user);
}
