package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.service.UserService;
import com.util.Md5Utils;

/**
 * Servlet implementation class LoginServler
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String number2 = request.getParameter("code");
		String autoLogin =request.getParameter("autoLogin");
		String number =(String) request.getSession().getAttribute("number");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		UserService us = UserService.getInstance();
		User b = us.login(user);
		if (b != null) {
			request.getSession().setAttribute("user", b);
			Cookie cookie = new Cookie("username", b.getUsername());
			cookie.setMaxAge(365*24*60*60);
			response.addCookie(cookie);
			if (autoLogin != null &&autoLogin.equals("1")) {
				Cookie cookie2 = new Cookie("autoLogin", Md5Utils.md5(username+"_"+password));
				cookie2.setMaxAge(365*12*24);
				response.addCookie(cookie2);
			}
			response.sendRedirect("User/Index.html");
		}else{
			request.setAttribute("msg", "�û������������");
			//response.sendRedirect("Login.jsp");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
