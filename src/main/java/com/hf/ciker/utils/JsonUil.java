package com.hf.ciker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static <T>  String toJson(T o){
        String target="";
        try {
            target = MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.error("toJson error",e);
            throw new RuntimeException(e);
        }
        return target;
    }
    public static <T>  T fromJson(String json,Class<T> value){
        T o = null;
        try {
            o = MAPPER.readValue(json,value);
        } catch (IOException e) {
            LOGGER.error("fromJson error",e);
            throw new RuntimeException(e);
        }
        return o;
    }
}
