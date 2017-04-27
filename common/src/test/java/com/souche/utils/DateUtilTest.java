package com.souche.utils;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.utils
 * @date 17/4/27
 **/
public class DateUtilTest {
    private Date date;
    private DateFormat format;

    @Before
    public void setDate() {
        // 2017-03-29
        date = new Date(1490768473386L);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void testGetMonthBegin() {
        Date dateBegin = DateUtil.getMonthBegin(date);

        String dateBeginStr = format.format(dateBegin);

        Assert.assertTrue(dateBeginStr.equals("2017-03-01 00:00:00"));
    }

    @Test
    public void testGetMonthEnd() {
        Date dateEnd = DateUtil.getMonthEnd(date);

        String dateEndStr = format.format(dateEnd);

        Assert.assertTrue(dateEndStr.equals("2017-03-31 23:59:59"));
    }

    @Test
    public void testGetLastMonthBegin() {
        Date dateBegin = DateUtil.getLastMonthBegin(date);

        String dateBeginStr = format.format(dateBegin);

        Assert.assertTrue(dateBeginStr.equals("2017-02-01 00:00:00"));
    }

    @Test
    public void testGetLastMonthEnd() {
        Date dateEnd = DateUtil.getLastMonthEnd(date);

        String dateEndStr = format.format(dateEnd);

        Assert.assertTrue(dateEndStr.equals("2017-02-28 23:59:59"));
    }

    @Test
    public void testGetDayBegin() {
        Date dateBegin = DateUtil.getDayBegin(date);

        String dateBeginStr = format.format(dateBegin);

        Assert.assertTrue(dateBeginStr.equals("2017-03-29 00:00:00"));
    }

    @Test
    public void testGetDayEnd() {
        Date dateEnd = DateUtil.getDayEnd(date);

        String dateEndStr = format.format(dateEnd);

        Assert.assertTrue(dateEndStr.equals("2017-03-29 23:59:59"));
    }

    @Test
    public void testGetYesterday() {
        Date yesterday = DateUtil.getYesterday(date);

        String yesterdayStr = format.format(yesterday);

        Assert.assertTrue(yesterdayStr.equals("2017-03-28 14:21:13"));
    }
}
