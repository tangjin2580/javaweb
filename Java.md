# **1. Java笔记**

## 1.1 Servlet

### 1.1.1  缓存

```java
//清理缓存
resp.setDateHeader("expires",-1);
resp.setHeader("Cache-Control","no-cache");
resp.setHeader("Pragma","no-cache");

```

### 1.1.2 文件下载

```java
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
```

### 1.1.3 重定向

```java
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //重定向
        /*
        resp.setHeader("Location","/Response/img");
        resp.sendRedirect("/Response/img");
         */
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }
}
```

***

> 面试题: 重定向和转发的区别

相同点：

​	    ●页面都会实现跳转

不同点：

​		●请求转发时，url不会产生变化

​		●重定向的时候，url地址会变化



### 1.1.4 HttpServletRequest

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 23240
  Date: 2022/6/3
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="get">
  用户名：<input type="text" name="username">
  密码: <input type="password" name="password">
  <input type="submit">
</form>

</body>
</html>
```



```java
public class RequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入这个请求");
        //设置文本类型
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("进入此页面");
        //处理请求
        String name =  req.getParameter("username");
        String password =  req.getParameter("password");
        System.out.println("账号："+name+"密码："+password);

        //重定向一定要注意路径问题，否则404
        resp.sendRedirect("/success.jsp");

    }
```

***

- > ​		HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器，HTTP请求中所有信息会被封装到HttpServletRequest，通过这 个HttpServletRequest的方法，获得客户端的所有信息

![图片](https://picture-1302338482.cos.ap-nanjing.myqcloud.com/Background202206071606874.png)

1.获取前端 传递的参数

![image-20220607161043075](https://picture-1302338482.cos.ap-nanjing.myqcloud.com/Background202206071610122.png)

```java
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobbies = request.getParameterValues("hobbies");

        System.out.println("==============");
        //后台接收中文乱码问题
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobbies));
        System.out.println(request.getContextPath());
        //这里的/代表当前的web应用
        request.getRequestDispatcher(request.getContextPath()+"/success.jsp").forward(request,response);
        request.setCharacterEncoding("utf-8");

    }
```

### 1.1.5

## 1.2 Java IO 流

### 1.2.1 什么是IO流

I的全称是input，表示输入

O的全称是Output表示输出

- 流：是一个在两个设备之间进行数据传输的管道

- 此时内存是一个设备，磁盘也是一个设备，磁盘中的文件数据显示在内存（Java程序中），就使用流来传输

- 磁盘中的数据显示到内存我们常用 输入流

- 输入流特征：***读***

- 输出流特征：***写***

***

IO流本质：在设备中进行数据传输的

按照流向分为：a.输入流  reader（读者）  b.输出流 writer（写者）

按照数据类型：a.字节流   InputStream  b.字符流 OutputStream

> 注意: 字节流用来读写bytes，字符流用来读写chars，一个字符（char 16bit）等于两个字节（byte 8 bit）



### 1.2.2 字节流

InputStream是所有字节输入流的父类，它也是一个抽象类，它定义了抽象方法由子类实现

- 已知直接子类： 

  [AudioInputStream](../../javax/sound/sampled/AudioInputStream.html) 读取媒体输入流 ， [ByteArrayInputStream](../../java/io/ByteArrayInputStream.html) 读取字节数组输入流 ， [FilerInputStream](../../java/io/FileInputStream.html) 带有过滤器的输入流 ，
  
    **[FilteInputStream](../../java/io/FilterInputStream.html) 读取硬盘文件输入流** ， [ObjectInputStream](../../java/io/ObjectInputStream.html) 读取对象的输入流  ，
  
  [StringBufferInputStream](../../java/io/StringBufferInputStream.html)  字符串缓冲区输入流
  
  字节输入特征：它所有的子类后缀都以InputStream结束

***

OutputStream是所有字节输出流的父类，他也是一个抽象类，它定义了抽象方法由子类实现

- - 已知直接子类： 

    [ByteArrayOutputStream](../../java/io/ByteArrayOutputStream.html) 写字节数组 ， [FilerOutputStream ](../../java/io/FileOutputStream.html)带有过滤器的输出流   ，

     **[FilteOutputStream](../../java/io/FilterOutputStream.html) 将数据写入磁盘文件**， [ObjectOutputStream](../../java/io/ObjectOutputStream.html) 写对象的输出流，



### 1.2.3 FilteOutputStream

- 它是一个基于磁盘字节输出流，作用是将Java程序的数据写入本地磁盘。

- 它有一次构造方法：

> new FiletOutputStream(String name)//创建基于磁盘的字节输出流对象，以字符串参数表示磁盘的路径   d:\a.txt

> new FiletOutputStream(File file)//创建基于磁盘的字节输出流对象，以File参数表示磁盘的路径，如File（路径）

它有以下成员方法：

- - | `void`          | `close()`  关闭此输出流并释放与此流相关联的任何系统资源。    |
    | --------------- | ------------------------------------------------------------ |
    | `void`          | `flush()`  刷新此输出流并强制任何缓冲的输出字节被写出。      |
    | `void`          | `write(byte[] b)`  将 `b.length`字节从指定的字节数组写入此输出流。 |
    | `void`          | `write(byte[] b,  int off, int len)`  从指定的字节数组写入 `len`个字节，从偏移 `off`开始输出到此输出流。 |
    | `abstract void` | `write(int b)`  将指定的字节写入此输出流。                   |

场景1：创建FileOutputStream

```java
package org.example;

import java.io.FileOutputStream;

/**
 * 场景：创建FileOutputStream对象并指定路径
 * 1.使用new关键字创建FileOutputStream对象
 * 2。以字符串作为参数指定路径
 * 3.打印对象
 * FileOutputStream fileOutputStream =  new FileOutputStream(a.txt);做了哪些事
 * 1创建了一个基于字节的磁盘输出流
 * 2.再当前工程下创建了一个a.txt文件
 * 3.将磁盘的a.txt和java程序进行绑定
 */
public class fops {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream =  new FileOutputStream("H:\\开源\\maven\\javeweb-02-servlet\\JavaIo\\src\\main\\resources\\a.txt");
            System.out.println(fileOutputStream);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
```

场景2：创建FileOutputStream对象，将java程序的数据写入到磁盘

```java
package org.example;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 场景2：创建FileOutputStream对象，将java程序的数据写入到磁盘
 * 1.创建FileOutPutStream对象，指定磁盘路径
 * 2.使用write（int）方法写入到磁盘中
 * 3.关闭管道
 */
public class fops2 {
    public static void main(String[] args) throws IOException {

        /**
         * 创建FileOutputStream对象，会先进磁盘检查是否存在该文件，如果有直接写入，如果没有就创建一个新的
         */
        FileOutputStream fileOutputStream =  new FileOutputStream("H:\\\\开源\\\\maven\\\\javeweb-02-servlet\\\\JavaIo\\\\src\\\\main\\\\resources\\\\aa.txt");
        //写入字节流将66转场ASCII码
        fileOutputStream.write(66);
        //关闭FileOutputStream管道
        fileOutputStream.close();
    }
}
```

### 1.2.4  编码解码

默认

```java
public class Main {
    public static void main(String[] args){
        //1，转换，此时将字符串转为字节数组，这个过程是一个编码的过程
        byte[] b ="李宁".getBytes();
        //此时编译环境是Utf-8编码，在utf-8中，一个汉字占用3个字节
        //汉字首字节以负数开始
        System.out.println(Arrays.toString(b));
        
        //字节数组转换为字符串的过程叫解码
        String s = new String(b);
        System.out.println(s);
    }
}
```



```java
public static void  main(String[] args){
    //将字符串”李宁“，使用GBK编码（转换字节数组）
    try {
        byte[] b = "李宁".getBytes("GBK");
        //gbk格式，一个汉字两个字节（bytes）
        System.out.println(Arrays.toString(b));
        //没有指令解码格式，默认使用Idea定义的格式（uft-8）解码
        //由于编码使用的格式是utf-8，解码使用的是GBk，解码就会乱码
        String s = new String(b,"GBK");
        System.out.println(s);
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);

    }

}
```

**小结：解码编码必须一致**



```java
//解码
String s = new String(b,2,2,"GBK");
System.out.println(s);
/*
 * 带有三个参数的String构造方法，提供对部分字节进行解码
 * 参数一：要解码的数组
 * 参数二：字节数组的偏移量（下标），姿势的0表示从字节数组的起始下标开始解码
 * 参数三：要接吗的字节数组长度，此时的3表示只解码3个字节
 * 控制下标字符要符合汉字占字节。（编码Gbk 2个，uft-8 3个）
 * 数组下标从0开始，原理：[11,22,33,44]，以数组的第一个元素为参照物
 * 11，对于自己的偏移量是0，所以是0，22对于自己的偏移量是1，所以是1.
*/
```

### 1.2.5 try with resources

> JDK 7 的一个新特性，弱化了fInally，java程序在运行时能够自动关闭资源。

语法：

```java
try(定义要关闭的资源){
    容易出现异常的代码
}catch(异常类型 异常变量){
    异常处理
}
```

> **注意：使用try with resources关闭资源try必须要，catch可有可无**

场景：使用Scanner完成数据的输入，然后使用try with resources关闭资源



