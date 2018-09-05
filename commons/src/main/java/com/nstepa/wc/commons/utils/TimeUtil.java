package com.nstepa.wc.commons.utils;

import java.util.Date;

public class TimeUtil {

    /**
     * 把数字转化为字符串，供前端显示，格式为 hours:minute
     *
     * @param i 单位秒
     * @return
     */
    public static String int2HoursMinute(Long i) {
        if (i == null) {
            return null;
        }

        long hours = i / 60 / 60;
        long minute = (i / 60) % 60;
        String result = String.format("%02d:%02d", hours, minute);
        return result;
    }

    /**
     * 把字符串形式的时间转化为数字型的，转化后以秒为单位
     *
     * @param s hours:minute
     * @return
     */
    public static Long hoursMinute2Int(String s) {
        if (s == null) {
            return null;
        }

        String[] ss = s.split(":");
        if (ss.length != 2) {
            return null;
        }

        int hours = Integer.parseInt(ss[0]);
        int minute = Integer.parseInt(ss[1]);
        return hours * 60l * 60l + minute * 60l;
    }

    /**
     * 构造Date
     *
     * @param l 基础的时间  long型
     * @param s "hh:mm" 格式
     * @return
     */
    public static Date generateTime(Long l, String s) {
        Date result = new Date(l);

        String[] ss = s.split(":");
        if (ss.length != 2) {
            return result;
        }

        int hours = Integer.parseInt(ss[0]);
        int minute = Integer.parseInt(ss[1]);

        result.setHours(hours);
        result.setMinutes(minute);

        return result;
    }

    public static Long hoursMinute2IntInDate(Date date) {
        return hoursMinute2Int(date.getHours() + ":" + date.getMinutes());
    }
}
