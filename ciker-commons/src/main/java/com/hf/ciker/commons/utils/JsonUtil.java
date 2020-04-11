package com.hf.ciker.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T>  String toJson(T o){
        String target="";
        try {
            target = MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LogUtil.error("toJson error, pre-json : {}", o, e);
        }
        return target;
    }
    public static <T>  T fromJson(String json,Class<T> value){
        T o = null;
        try {
            o = MAPPER.readValue(json,value);
        } catch (IOException e) {
            LogUtil.error("fromJson error, json : {}, value : {}", json, value, e);
        }
        return o;
    }
}
