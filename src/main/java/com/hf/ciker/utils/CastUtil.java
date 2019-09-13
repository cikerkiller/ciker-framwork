package com.hf.ciker.utils;

import org.apache.commons.lang3.StringUtils;

public class CastUtil {
    public static String castString(Object obj,String defaultValue){
        return obj == null ? defaultValue : String.valueOf(obj);
    }
    public static String castString(Object obj){
        return castString(obj,"");
    }
    public static double castDouble(Object obj,double defaultValue){
        double value = defaultValue;
        try {
           return obj == null ? value :
                    StringUtils.isEmpty(castString(obj)) ?
                            value :Double.parseDouble(castString(obj));
        }catch (NumberFormatException e){
            return value;
        }
    }
    public static double castDouble(Object obj){
        return castDouble(obj,0.0);
    }
    public static long castLong(Object obj,long defaultValue){
        long value = defaultValue;
        try {
            return obj == null ? value :
                    StringUtils.isEmpty(castString(obj)) ?
                            value :Long.parseLong(castString(obj));
        }catch (NumberFormatException e){
            return value;
        }
    }
    public static long castLong(Object obj){
        return castLong(obj,0);
    }
    public static int castInt(Object obj,int defaultValue){
        int value = defaultValue;
        try {
            return obj == null ? value :
                    StringUtils.isEmpty(castString(obj)) ?
                            value :Integer.parseInt(castString(obj));
        }catch (NumberFormatException e){
            return value;
        }
    }
    public static int castInt(Object obj){
        return castInt(obj,0);
    }
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean value = defaultValue;
        return obj == null ? defaultValue : Boolean.parseBoolean(castString(obj));
    }
    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }
}
