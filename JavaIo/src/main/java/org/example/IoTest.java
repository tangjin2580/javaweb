package org.example;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class IoTest {
    public static void  main(String[] args){
        //将字符串”李宁“，使用GBK编码（转换字节数组）
        try {
            byte[] b = "李宁".getBytes("GBK");
            //gbk格式，一个汉字两个字节（bytes）
            System.out.println(Arrays.toString(b));
            //没有指令解码格式，默认使用Idea定义的格式（uft-8）解码
            //由于编码使用的格式是utf-8，解码使用的是GBk，解码就会乱码
            String s = new String(b,2,2,"GBK");
            System.out.println(s);
            /*
             * 带有三个参数的String构造方法，提供对部分字节进行解码
             * 参数一：要解码的数组
             * 参数二：字节数组的偏移量（下标），姿势的0表示从字节数组的起始下标开始解码
             * 参数三：要接吗的字节数组长度，此时的3表示只解码3个字节
             * 控制下标字符要符合汉字占字节。（编码Gbk 2个，uft-8 3个）
            */
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);

        }



    }

}
