package com.hf.ciker.commons.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {

    public static String getDataFromStream(InputStream in){
        StringBuilder sbu = new StringBuilder();
        String line;
        try (BufferedReader  reader = new BufferedReader(new InputStreamReader(in))){
            while ((line = reader.readLine())!=null){
                sbu.append(line);
            }
        } catch (IOException e) {
            LogUtil.error("getString error",e);
        }
        return sbu.toString();
    }
}
