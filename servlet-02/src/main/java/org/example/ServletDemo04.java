package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDemo04", value = "/ServletDemo04")
public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        System.out.println("Demo04");
        //请求转发,请求路径
       // RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
        //调用forward实现 请求转发
        //requestDispatcher.forward(request,response);
        //转发给xml中的gp
        context.getRequestDispatcher("/gp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
