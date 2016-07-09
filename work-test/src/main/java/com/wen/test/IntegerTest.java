package com.wen.test;

/**
 * Created by hcw on 16-3-5.
 */
public class IntegerTest {
    public static void main(String[] args) {
        compare();
    }

    public static void compare() {
        /**
         * 基本类型比较：
         * int和int 类型比较：直接值比较
         * int和Integer(new Integer)比较:默认会进行拆箱，将Integer转换成int
         * Integer和Integer比较则不会进行拆箱，比较
         */
        int a = 10;
        int b = 10;
        Integer e = 10;
        Integer c = new Integer(10);
        Integer d = new Integer(10);

        System.out.println("int a==b :" + (a == b));
        System.out.println("int a == Integer e :" + (a == e));
        System.out.println("int b == new Integer d:" + (b == d));
        System.out.println("new Integer c==new Integer d :" + (c == d));
    }
}
