package com.wen.test;

import java.util.*;

/**
 * map测试类
 */
public class MapTest {

    public static void main(String[] strs) {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("11", "wen");
        maps.put("22", "rrr");
        maps.put("33", "hhhh");

        //通过Set视图循环
        for (Map.Entry<String, String> entry : maps.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        //遍历map中的键
        for (String key : maps.keySet()) {
//            System.out.println("Key = " + key);
        }

        //遍历map中的值
        for (String value : maps.values()) {
//            System.out.println("Value = " + value);
        }

        //使用Iterator遍历
        Iterator<Map.Entry<String, String>> entries = maps.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
