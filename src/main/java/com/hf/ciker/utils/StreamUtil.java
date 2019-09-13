package com.hf.ciker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream in){
        StringBuilder sbu = new StringBuilder();
        String line;
        try (BufferedReader  reader = new BufferedReader(new InputStreamReader(in))){
            while ((line = reader.readLine())!=null){
                sbu.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("getString error",e);
            throw new RuntimeException(e);
        }
        return sbu.toString();
    }
}
