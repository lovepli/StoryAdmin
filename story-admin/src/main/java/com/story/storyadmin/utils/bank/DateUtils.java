package com.story.storyadmin.utils.bank;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: 59688
 * @date: 2021/5/24
 * @description:
 */
public class DateUtils {

    public static  final  String FORMAT_ONE ="yyyy-MM-dd";
    public static  final  String FORMAT_TWO ="yyyy/MM/dd";
    public static  final  String FORMAT_YEAR ="yyyy";

    static SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_ONE);
    static SimpleDateFormat sdf_two= new SimpleDateFormat(FORMAT_TWO);
    static SimpleDateFormat sdf_year= new SimpleDateFormat(FORMAT_YEAR);

    static List<String> quart_one =new ArrayList<String>(){{add("01");add("02");add("03");}};
    static List<String> quart_two =new ArrayList<String>(){{add("04");add("05");add("06");}};
    static List<String> quart_three =new ArrayList<String>(){{add("07");add("08");add("09");}};
    static List<String> quart_four =new ArrayList<String>(){{add("10");add("11");add("12");}};

    /**
     * 获取系统当前时间
     * @return
     */
    public static String getDate(){
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * Date 类型转String
     * @param date
     * @return
     */
    public static  String getDate(Date date){
        String time =sdf.format(date);
        return time;
    }

    /**
     * String 转 Date
     * @param dateStr
     * @return
     */
    public static  Date getDate(String dateStr){
        Date date = null;
        try{
            date = sdf.parse(dateStr);
           // date = sdf_two.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * 获取上一年年份
     * @param currYear
     * @return
     */
    public static String getLastYear(String currYear){
        String lastYear = null;
        Calendar calendar = new GregorianCalendar();
        try{
            Date date = sdf_year.parse(currYear);
            calendar.setTime(date);
            calendar.add(Calendar.YEAR,-1);
            lastYear =sdf_year.format(calendar.getTime());
        }catch (ParseException e){
            e.printStackTrace();
        }
        return  lastYear;
    }


    /**
     * 根据日期生成当前季度字符串（yyyy-季度（1,2,3,4））
     * @param uploadDate （yyyy-MM-dd）
     * @return
     */
    public  static  String getCurrentQuarterly(String uploadDate){
        String quart = null;
        String[] dateStr =uploadDate.split("-");
        if(quart_one.contains(dateStr[1])){
             quart =dateStr[0]+"-1";
        } else if(quart_two.contains(dateStr[1])){
            quart =dateStr[0]+"-2";
        }else  if(quart_three.contains(dateStr[1])){
            quart =dateStr[0]+"-3";
        }else  if(quart_four.contains(dateStr[1])){
            quart =dateStr[0]+"-4";
        }
        return  quart;
    }


    public static void main(String[] args) {
        String ss= getCurrentQuarterly("2021-05-24");
        System.out.println(ss+"季度");
    }






}
