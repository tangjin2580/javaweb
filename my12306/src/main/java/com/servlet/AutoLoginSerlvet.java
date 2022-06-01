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
import com.util.TextUtils;

/**
 * Servlet implementation class AutoLoginSerlvet
 */
@WebServlet("/autoLogin")
public class AutoLoginSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoLoginSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username="";
	    String autoLogin="";
	    Cookie[] cookies =request.getCookies();
	    if(cookies != null && cookies.length > 0){
	    	for(Cookie cookie : cookies){
	    		if(cookie.getName().equals("username"))
	    			username=cookie.getValue();
	    		else if(cookie.getName().equals("autoLogin"))
	    			autoLogin=cookie.getValue();
	    	}
	    }
	    if(!TextUtils.isEmpty(username)&&TextUtils.isEmpty(autoLogin)){
	    	response.sendRedirect("autoLogin");
	    }
	    User user = new User();
	    user.setUsername(username);

	    UserService us =UserService.getInstance();
	    User b = us.login(user);
	    String password = b.getPassword();
	    if(autoLogin.equals(Md5Utils.md5(username+""+password))) {
	    	request.getSession().setAttribute("user", b);
	    	response.sendRedirect("User/Index.html");
	    }else {
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
