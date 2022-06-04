package org.example;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如何让浏览器5秒自动刷新一次
        resp.setHeader("refresh","3");
        //在内存中创建一个图片
        BufferedImage bufferedImage = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        //得到图片
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();//pen
        //设置图片背景颜色
        g.setColor(Color.white);
        g.fillRect(0,0,80,20);
        //给图片写数据
        g.setColor(Color.blue);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(MakeNumber(),0,20);

        //告诉浏览器，这个请求用浏览器打开
        resp.setContentType("image/jpeg");

        //清理缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        //把图片写给浏览器
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());
    }



    //生成随机数
    private  String MakeNumber(){
        Random random = new Random();
        String num = random.nextInt(99999999)+"";
        //StringBuffer拼接字段串
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 7-num.length(); i++) {
            stringBuffer.append("0");
        }
       String s = stringBuffer.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
