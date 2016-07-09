package com.wen.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 *
 * @author Administrator
 * @version [版本号, 2015年1月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class DateUtils {

    /**
     * 对给定的日期以"yyyy-MM-dd"格式化
     *
     * @param date 指定的日期对象
     * @return
     */
    public static String format(Date date) {
        if (null != date) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

            return dateFormat.format(date);
        }

        return null;
    }

    /**
     * 根据指定的日期，计算距离到当前日期的天数(不包含当天)，指定是日期必须是2014-3-1格式
     *
     * @param strStartDate 指定的日期字符串
     * @return 表示距离的天数
     */
    public static long startDateToNowDays(String strStartDate) {
        long quot = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date now = new Date();
            DateFormat dateFormat = DateFormat.getDateInstance();
            String strNowDate = dateFormat.format(now);

            Date nowDate = simpleDateFormat.parse(strNowDate);
            Date startDate = simpleDateFormat.parse(strStartDate);
            quot = nowDate.getTime() - startDate.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return quot;
    }

    /**
     * 检查两个日期对象是否是同一天，忽略时间
     *
     * @param date1 第一个日期对象，不能为null
     * @param date2 第二个日期对象，不能为null
     * @return 如果是同一天，则返回true
     */
    public static boolean isSameDay(Date date1, Date date2) {
        //非空检查
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return isSameDay(cal1, cal2);
    }

    /**
     * 检查两个日历对象是否是同一天，忽略时间
     *
     * @param calendar1 第一个日历对象，不能为null
     * @param calendar2 第二个日历对象，不能为null
     * @return 如果是同一天，则返回true
     */
    public static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
        //非空检查
        if (calendar1 == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        return (calendar1.get(Calendar.ERA) == calendar2.get(Calendar.ERA)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 获取当前日期，所在月份第一天日期
     * <p>日期以"yyyy-MM-dd"格式化
     *
     * @return 表示月份第一天日期字符串
     */
    public String getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String monthFirstDay = DateUtils.format(calendar.getTime());

        return monthFirstDay;
    }

    /**
     * 获取当前日期所在月份最后一天日期字符串
     * <p>日期以"yyyy-MM-dd"格式化
     *
     * @return 表示月份最后一天日期字符串
     */
    public String getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String monthLastDay = DateUtils.format(calendar.getTime());

        return monthLastDay;
    }

    /**
     * 根据当前日期，获取当前日期所在星期一的日期字符串
     * <p>星期一的日期以"yyyy-MM-dd"格式化
     *
     * @return 表示星期一的日期字符串，如：2014-09-22
     */
    public static String getNowDateMonday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);

        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return format(calendar.getTime());
    }

    /**
     * 当前时间上周一日期
     *
     * @return 表示上周一日期对象
     */
    public static Date getLastWeekMonday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return calendar.getTime();
    }

    /**
     * 当前时间上周日日期
     *
     * @return 表示上周日期对象
     */
    public static Date getLastWeekSunday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return calendar.getTime();
    }
}