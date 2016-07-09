package com.wen.test;

import java.text.DecimalFormat;

/**
 * Created by hcw on 16-3-18.
 */
public class DoubleTest {
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Double s = Double.parseDouble(decimalFormat.format("1.577"));
        System.out.println(s);
    }
}
