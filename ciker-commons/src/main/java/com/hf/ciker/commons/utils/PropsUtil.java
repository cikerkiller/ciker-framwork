package com.hf.ciker.commons.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    public static Properties loadProps(String fileName){
        Properties prop = null;
        InputStream in = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(in == null){
                throw new FileNotFoundException(fileName+" file is not found");
            }
            prop = new Properties();
            prop.load(in);
        }catch (IOException e){
            LogUtil.error("load properties error, fileName : {}", fileName, e);
        }finally {
            if (in != null){
                try {
                    in.close();
                }catch (IOException e){
                    LogUtil.error("close input stream failure",e);
                }
            }
        }
        return prop;
    }

    public static String getString(Properties prop,String key){
        return getString(prop,key,"");
    }
    public static String getString(Properties prop,String key,String defaultValue){
        String value = defaultValue;
        if(prop.containsKey(key)){
            value = prop.getProperty(key);
        }
        return value;
    }

    public static int getInt(Properties prop, String key,int defaultValue){
        int value = defaultValue;
        if(prop.contains(key)){
            value = CastUtil.castInt(prop.getProperty(key));
        }
        return value;
    }
    public static int getInt(Properties prop, String key){
        return getInt(prop,key,0);
    }
    public static boolean getBoolean(Properties prop ,String key,boolean defaultValue){
        boolean value = defaultValue;
        if(prop.contains(key)){
            value = CastUtil.castBoolean(prop.getProperty(key));
        }
        return value;
    }
    public static boolean getBoolean(Properties prop ,String key){
        return getBoolean(prop,key,false);
    }
    public static double getDouble(Properties prop, String key,double defaultValue){
        double value = defaultValue;
        if(prop.contains(key)){
            value = CastUtil.castDouble(prop.getProperty(key));
        }
        return value;
    }
    public static double getDouble(Properties prop, String key){
        return getDouble(prop,key,0.0);
    }
}
