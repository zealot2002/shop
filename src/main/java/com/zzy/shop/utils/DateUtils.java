package com.zzy.shop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date string2Date(String s){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();

    }
}
