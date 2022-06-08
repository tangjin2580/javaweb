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
