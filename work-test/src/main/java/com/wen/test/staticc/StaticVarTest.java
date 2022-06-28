package com.wen.test.staticc;

public class StaticVarTest {
    /**
     * 静态变量只有一个副本，被所有实例共享
     */
    static int sum = 0;

    /**
     * 求两个数平均值，并累加到下一次
     * @param a
     * @param b
     */
    static void addAverageSum(int a, int b) {
        int total = a + b;
        int average = total / 2;
        sum +=  average;//加上上次平均值

    }

    public static void main(String[] args) {
        addAverageSum(1, 3);
        System.out.println("addAverage(1,3):" + sum);
        addAverageSum(2,6);
        System.out.println("addAverage(2,6):" + sum);
        addAverageSum(3,6);
        System.out.println("addAverage(3,6):" + sum);

    }
}
