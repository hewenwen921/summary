package com.wen.common.utils;

/**
 * Created by hcw on 16-3-16.
 */
public final class IntegerUtils {

    /**
     * 根据指定的整数数据，获取当中最小的整数
     *
     * @param arr
     * @return
     */
    public static int getMinInt(int[] arr) {
        int min = Integer.MAX_VALUE;

//        int[] arr = {5, 20, 15, 36, 2};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
//        System.out.println(min);
    }
}
