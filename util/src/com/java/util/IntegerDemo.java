package com.java.util;


import java.util.Scanner;

/**
*Integer类练习
* @author 李家劲
* 2019.3.12
 */
 public class IntegerDemo {
    public static void main(String[] args) {
        int num  = 31;
        //
        //
        String str1 = Integer.toBinaryString(num);
        System.out.println("二进制："+str1);
        //
        String str2 = Integer.toOctalString(num);
        System.out.println("八进制："+str2);

        String str3 = Integer.toHexString(num);
        System.out.println("十六进制："+str3 );

        String str4 = Integer.toString(num);
        System.out.println("十五进制："+str4);
        System.out.println("-----------分割线--------------------");
        System.out.println("转化7进制："+ convert(num,7));

    }

    private  static  String convert(int num,int radix){
        //创建一个StringBuffer，用来存放结果字符串
        StringBuffer stringBuffer = new StringBuffer();
        //被除数不为0
        while (num != 0){
            //求出余数
            int  remainder =num % radix;
            //将余数加入stringBuffer
            stringBuffer.append(String.valueOf(remainder));
            //更新被除数为商
            num = num / radix;
        }
        //将结果串取返回

        return stringBuffer.reverse().toString();
    }

}