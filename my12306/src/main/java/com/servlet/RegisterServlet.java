package com.servlet;

import com.bean.CertType;
import com.bean.City;
import com.bean.Province;
import com.bean.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet implementation class RegisterServlet
 */
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");  //city
		String cityid = request.getParameter("city");  //city
		String certTypeid = request.getParameter("certType");  //city
		String birthday = request.getParameter("birthday");
		String loginIP=request.getRemoteAddr();

		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRealname(realname);
		user.setSex(sex);

		City city = new City();
		city.setId(Integer.parseInt(cityid));

		Province province = new Province();
		province.setCity(city);
		user.setProvince(province);

		CertType certType = new CertType();
		certType.setId(Integer.parseInt(certTypeid));
		user.setCertType(certType);

		user.setBirthday(Date.valueOf(birthday));
		user.setLoginIP(loginIP);
		UserService us = UserService.getInstance();
		boolean b=us.addUser(user);
		if (b) {
			response.sendRedirect("UserRegistration.html");
		} else {
			response.sendRedirect("Login.jsp");
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
