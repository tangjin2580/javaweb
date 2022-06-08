package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "FileServlet", value = "/FileServlet")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().println("下载文件");
        //1.获取下载文件路径
        String realPath="H:\\开源\\maven\\javeweb-02-servlet\\Response\\src\\main\\resources\\bg.jpg";
        System.out.println("下载文件路径"+realPath);
    //2.下载文件名是什么
        String filename= realPath.substring(realPath.lastIndexOf("\\")+1);
    //3.设置想办法让浏览器能够支持下载我们的东西
        response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
    //4.获取下载文件的输入流
        FileInputStream fileInputStream= new FileInputStream(realPath);
    //5.创建缓冲区
        int len = 0;
        byte[] b= new byte[1024];
    //6.获取OutPutStream对象
        ServletOutputStream outputStream = response.getOutputStream();
    //7.将FileOutoutStream流写入到buffer缓冲区
        while ((len=fileInputStream.read(b))>0){
            outputStream.write(b,0,len);
        }
        //关闭io流
        fileInputStream.close();
        outputStream.close();
    //8.使用outputSream将缓冲区中的数据输出到客户端
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
