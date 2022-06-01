package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/my12306?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER = "root";
	private static final String PWD = "123456";
	@Test
	public static Connection getConnection() throws Exception {
		//?????????????????????Driver??????????????
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL,USER,PWD);
	}
}
