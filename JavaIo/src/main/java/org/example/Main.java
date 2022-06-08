package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        //1，转换，此时将字符串转为字节数组，这个过程是一个编码的过程
        byte[] b ="李宁".getBytes();
        //此时编译环境是Utf-8编码，在utf-8中，一个汉字占用3个字节
        //汉字首字节以负数开始
        System.out.println(Arrays.toString(b));

        //字节数组转换为字符串的过程叫解码
        String s = new String(b,0,3);
        /*
          带有三个参数的String构造方法，提供对部分字节进行解码
          参数一：要解码的数组
          参数二：字节数组的偏移量（下标），姿势的0表示从字节数组的起始下标开始解码
          参数三：要接吗的字节数组长度，此时的3表示只解码3个字节

         */
        System.out.println(s);




    }
}
