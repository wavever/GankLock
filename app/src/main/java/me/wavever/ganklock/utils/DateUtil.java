package me.wavever.ganklock.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wavever on 2015/12/24.
 */
public class DateUtil {

    /**
     * 将日期格式化为 2015/12/24
     */
    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  //mm是分钟。。
        return dateFormat.format(date);
    }


    /**
     * 获取当天的日期
     */
    public static Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }


    public static Date getLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTodayDate());
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }


    /**
     * 获取当天格式化后的日期
     */
    public static String getTodayFormatDate() {
        return DateUtil.formatDate(DateUtil.getTodayDate());
    }


    public static String getYesterdayFormatDate() {
        return DateUtil.formatDate(DateUtil.getLastDate());
    }


    public static String get2YesterdayFormatDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTodayDate());
        calendar.add(Calendar.DATE, -2);
        return DateUtil.formatDate(calendar.getTime());
    }


    public static String getLastDayFormatDate() {
        return DateUtil.formatDate(getLastDate());
    }


    /**
     * 获取当天的星期
     */
    public static String getWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "null";
        }
    }


    /**
     * 获得上次Gank的时间，只是周末不发
     */
    public static String getLastGankDate() {
        String date = "";
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        if (week == 1) {
            date = DateUtil.get2YesterdayFormatDate();
        }
        else if(week == 7){
            date = DateUtil.getYesterdayFormatDate();
        }
        return date;
    }


    /**
     * @return 返回格式为“x月x日 星期x”
     */
    public static String getLockDateText(){
        StringBuilder str = new StringBuilder();
        String[] s = getTodayFormatDate().split("/");
        if(s[1].charAt(0)=='0'){
            str.append(s[1].charAt(1)).append("月");
        }else {
            str.append(s[1]).append("月");
        }

        if(s[2].charAt(0)=='0'){
            str.append(s[2].charAt(1)).append("日");
        }else {
            str.append(s[2]).append("日");
        }
        str.append(" ").append(getWeek());
        return str.toString();
    }

    /**
     * 将 "yyyy-MM-dd" 转化为 中文
     */
    public static String convertDateNum(String dateNum) {
        String month = "";
        String day = "";
        switch (Integer.parseInt(dateNum.substring(5, 7))) {
            case 1:
                month = "一月";
                break;
            case 2:
                month = "二月";
                break;
            case 3:
                month = "三月";
                break;
            case 4:
                month = "四月";
                break;
            case 5:
                month = "五月";
                break;
            case 6:
                month = "六月";
                break;
            case 7:
                month = "七月";
                break;
            case 8:
                month = "八月";
                break;
            case 9:
                month = "九月";
                break;
            case 10:
                month = "十月";
                break;
            case 11:
                month = "十一月";
                break;
            default:
                month = "十二月";
                break;

        }
        day= convertNum(Integer.parseInt(dateNum.substring(8, 10)));
        return month + "·" + day+"日";
    }


    private static String convertNum(int num) {
        String s1 = "";
        String s2 = "";
        if (num >= 30) {
            s1 = "三十";
        } else if (num >= 20) {
            s1 = "廿";
        } else if (num >= 10) {
            s1 = "十";
        }

        switch (num % 10) {
            case 1:
                s2 = "一";
                break;
            case 2:
                s2 = "二";
                break;
            case 3:
                s2="三";
                break;
            case 4:
                s2="四";
                break;
            case 5:
                s2="五";
                break;
            case 6:
                s2="六";
                break;
            case 7:
                s2="七";
                break;
            case 8:
                s2="八";
                break;
            case 9:
                s2="九";
                break;
        }

        return s1 + s2;
    }
}
