package com.wen.test;

/**
 * java反射测试
 */
public class ReflectTest {
    public static void main(String[] args) {
        try {
            //第一种方式：
            Class<?> c = Class.forName("User");
            //第二种方式：
            //java中每个类型都有class 属性.
//            Classc2 = User.class;

            //第三种方式：
            //java语言中任何一个java对象都有getClass 方法
//            Employeee = new User();
//            Classc3 = e.getClass(); //c3是运行时类 (e的运行时类是Employee)
            System.out.println(c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


