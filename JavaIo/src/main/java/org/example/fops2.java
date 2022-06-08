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
