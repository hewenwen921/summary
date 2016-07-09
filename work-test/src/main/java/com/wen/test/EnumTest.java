package com.wen.test;

import java.util.Iterator;
import java.util.Map;

/**
 * 枚举测试
 */
public class EnumTest {
    public static void main(String args[]) {

        //循环全部枚举
        for (Grade grade : Grade.values()) {
            System.out.println("name:" + grade.name() + ", 描述：" + grade.description + ", ordinal " + grade.ordinal());
        }
    }

    enum Grade {
        QUALIFIED("合格品"),
        FIRST("一等品"),
        EXCELLENT("优等品"),
        HIGH_EXCELLENT("高于优等品"),
        MANUAL("纯手工制作");

        /**
         * 描述
         */
        final String description;

        Grade(String desp) {
            this.description = desp;
        }
    }
}

