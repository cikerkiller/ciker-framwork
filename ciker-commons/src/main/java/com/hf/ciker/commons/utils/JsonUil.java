package com.hf.ciker.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T>  String toJson(T o){
        String target="";
        try {
            target = MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LogUtil.error("toJson error",e);
            throw new RuntimeException(e);
        }
        return target;
    }
    public static <T>  T fromJson(String json,Class<T> value){
        T o = null;
        try {
            o = MAPPER.readValue(json,value);
        } catch (IOException e) {
            LogUtil.error("fromJson error",e);
            throw new RuntimeException(e);
        }
        return o;
    }
}
