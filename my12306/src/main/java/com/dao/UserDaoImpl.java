package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.CertType;
import com.bean.City;
import com.bean.Province;
import com.bean.User;
import com.util.TextUtils;

public class UserDaoImpl implements UserDao {
    java.sql.Connection conn;
	public UserDaoImpl(java.sql.Connection conn) {
		this.conn=conn;
	}
	@Override
	public boolean addUser(User user)  {
String sql="INSERT INTO `my12306`.`user` VALUES (null, ?, ?,?,?,?,?,?,?)";
		PreparedStatement psmt;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUsername());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getRealname());
			psmt.setString(4, user.getSex());
			psmt.setInt(5, user.getProvince().getCity().getId());
			psmt.setInt(6, user.getCertType().getId());
			psmt.setDate(7, user.getBirthday());
			psmt.setString(8,user.getLoginIP());
			boolean b=psmt.execute();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean queryByName(String username) {
		String sql="select username from user where username=?";
		PreparedStatement psmt;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public User login(User user) {
		StringBuffer sql=new StringBuffer("select * from user,city,province,certtype ");
		boolean flag=true;
		int index=0;
		String username=user.getUsername();
		String password=user.getPassword();
		if (!TextUtils.isEmpty(username)) {
			if (flag) {
				flag=false;
				sql.append(" where username=? ");
			}else {
				sql.append(" and username=? ");
			}
		}
	   if(!TextUtils.isEmpty(password)) {
			if (flag) {
				flag=false;
				sql.append(" where password=? ");
			}else {
				sql.append(" and password=? ");
			}
		}
	   sql.append(" and user.cityid=city.id ");
	   sql.append(" and province.provinceid =city.provinceid ");
	   sql.append(" and user.certType = user.certType  ");
		PreparedStatement psmt;
		try {
			psmt = conn.prepareStatement(sql.toString());
			if (!TextUtils.isEmpty(username))
				psmt.setString(++index, user.getUsername());
			if(!TextUtils.isEmpty(password))
				psmt.setString(++index, user.getPassword());
			ResultSet rs = psmt.executeQuery();
			User one=new User();
			if(rs.next()) {
				one.setUsername(rs.getString("username"));
				one.setPassword(rs.getString("password"));
				one.setRealname(rs.getString("realname"));
				one.setSex(rs.getString("sex"));

				City city=new City();
				city.setCity(rs.getString("city.city"));
				Province province = new Province();
				province.setCity(city);
				province.setProvince(rs.getString("province.province"));
				one.setProvince(province);
				CertType certType =new CertType();
				certType.setCerttype(rs.getString("certtype.certtype"));
				one.setCertType(certType);
				return one;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
