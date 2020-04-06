package com.hf.ciker.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CodecUtil {
    public static String encodeURL(String source){
        String target = "";
        try {
            target = URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error("encodeURL error",e);
            throw new RuntimeException(e);
        }
        return target;
    }
    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error("decodeURL error",e);
            throw new RuntimeException(e);
        }
        return target;
    }


}
