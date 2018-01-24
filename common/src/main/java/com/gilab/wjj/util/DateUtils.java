package com.gilab.wjj.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * DateUtils
 * Author: yuankui
 * Desc:
 * <p/>
 * Change Log:
 * 2017/10/31 - created by yuankui
 */
public class DateUtils {
    private static DateTimeZone timeZone = DateTimeZone.forTimeZone(TimeZone.getDefault());

    public static java.util.Date convertUtilDate(DateTime jodaDate) {
        return jodaDate.toDate();
    }

    public static java.util.Date convertUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Timestamp convertSqlTs(DateTime jodaDate) {
        return new java.sql.Timestamp(jodaDate.getMillis());
    }

    public static java.sql.Timestamp convertSqlTs(java.util.Date utilDate) {
        return new java.sql.Timestamp(utilDate.getTime());
    }

    public static DateTime convertJodaTime(java.util.Date utilDate) {
        return new DateTime(utilDate, timeZone);
    }

    public static DateTime convertJodaTime(java.sql.Date sqlDate) {
        return new DateTime(sqlDate, timeZone);
    }

    public static DateTime startOfToday() {
        return DateTime.now().withTimeAtStartOfDay();
    }

    public static DateTime startOfTomorrow() {
        return DateTime.now().withTimeAtStartOfDay().plusDays(1);
    }

    public static DateTime now() {
        return DateTime.now();
    }

    public static java.util.Date cronUpperBoundDate() {
        return convertUtilDate(startOfTomorrow().plusYears(100));
    }

    public static java.sql.Timestamp convertSqlTs(Long millis) {
        if (millis == null || millis == 0)
            return null;
        return new java.sql.Timestamp(millis);
    }

    public static java.util.Date convertUtilDate(Long millis) {
        if (millis == null || millis == 0)
            return null;
        return new java.util.Date(millis);
    }

    public static DateTime convertJodaTime(Long millis) {
        if (millis == null || millis == 0)
            return null;
        return new DateTime(millis, timeZone);
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String datetimeString(Long millis, String pattern) {
        if (millis == null) return null;
        return new DateTime(millis, timeZone).toString(pattern);
    }

    public static String datetimeString(Long millis) {
        if (millis == null) return null;
        return datetimeString(millis, "yyyy-MM-dd HH:mm:ss");
    }

    public static long parseDate(String date) {
        return DateTime.parse(date).withZone(timeZone).getMillis();
    }


    private static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .append(null, new DateTimeParser[]{
                    DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser()
            })
            .toFormatter();

    public static long parseDatetime(String datetime) {
        return DateTime.parse(datetime, formatter)
                .withZone(timeZone).getMillis();
    }

    public static String currDateString(String pattern) {
        return new DateTime(timeZone).toString(pattern);
    }

    public static String currDatetimeString() {
        return currDateString("yyyy-MM-dd HH:mm:ss");
    }

    public static int getDaysOfMonth(long dateTime) {
        Date date = convertUtilDate(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static long getFirstDayOfMonth(long dateTime){
        Date date = convertUtilDate(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime().getTime();
    }

    public static long getLastDayOfMonth(long dateTime){
        Date date = convertUtilDate(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime().getTime();
    }

    public static long getSomeDayOfMonth(long dateTime, int day){
        return convertJodaTime(getFirstDayOfMonth(dateTime)).plusDays(day-1).getMillis();
    }

}
