package com.nstepa.wc.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static int getMondayPlus(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static Date getMonday(Date date) {
        int mondayPlus = getMondayPlus(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, mondayPlus);
        return calendar.getTime();
    }

    public static Date getNextMonday(Date date) {
        int mondayPlus = getMondayPlus(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, mondayPlus + 7);
        return calendar.getTime();
    }

    public static Date getSunday(Date date) {
        int mondayPlus = getMondayPlus(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, mondayPlus + 6);
        return calendar.getTime();
    }

    public static Date addDays(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, num);
        return calendar.getTime();
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static Date generateSameDateInAnotherWeek(Date date, Date weekStart){
        Date currentWeekStart = getMonday(date);
        DateUtil.set0H0M0S(currentWeekStart);
        return new Date(weekStart.getTime() + (date.getTime() - currentWeekStart.getTime()));
    }
    
    public static void set0H0M0S(Date date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
    }

    public static Date beforeOneHourToNowDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return calendar.getTime();
    }
}
