package org.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //this.getInitParameter();  初始化参数
        //this.getServletConfig();   servlet配置
        //this.getServletContext();   servlet上下文
        ServletContext context = this.getServletContext();

        //数据
        String UserName="date";
        //setAttribute设置(k,v)将数据保存到ServletContext中
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        context.setAttribute("UserName",UserName);
        System.out.println("hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
