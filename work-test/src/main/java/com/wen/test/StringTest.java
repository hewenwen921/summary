package com.wen.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/7/29.
 */
public class StringTest {
    public static void main(String[] args) {
        String s="184|212|213|247|249|241|";

        System.out.println(1/2);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String strDate=dateFormat.format(new Date());

        System.out.println(strDate);
    }
}
