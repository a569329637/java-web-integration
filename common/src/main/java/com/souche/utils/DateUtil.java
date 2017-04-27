package com.souche.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.utils
 * @date 17/3/29
 **/
public class DateUtil {

    /**
     * 获取 date 当月月份的开始时间
     *
     * @param date
     * @return
     */
    public static Date getMonthBegin(Date date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);

        return startCalendar.getTime();
    }

    /**
     * 获取 date 当月月份的结束时间
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(date);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return endCalendar.getTime();
    }

    /**
     * 获取 date 上个月月份的开始时间
     *
     * @param date
     * @return
     */
    public static Date getLastMonthBegin(Date date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.add(Calendar.MONTH, -1);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);

        return startCalendar.getTime();
    }

    /**
     * 获取 date 上个月月份的结束时间
     *
     * @param date
     * @return
     */
    public static Date getLastMonthEnd(Date date) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(date);
        endCalendar.add(Calendar.MONTH, -1);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return endCalendar.getTime();
    }

    /**
     * 获取 date 开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        setMinTime(startCalendar);

        return startCalendar.getTime();
    }

    /**
     * 获取 date 结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        setMaxTime(startCalendar);

        return startCalendar.getTime();
    }

    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getYesterday() {
        return getYesterday(new Date());
    }

    private static void setMinTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setMaxTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
    }
}
