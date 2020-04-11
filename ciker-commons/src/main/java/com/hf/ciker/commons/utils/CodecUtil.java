package com.hf.ciker.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @desc 编解码工具类
 * @author ciker
 * @date 2020-04-11
 */
public class CodecUtil {

    public static String encodeURL(String source){
        try {
            return URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error("encodeURL error, source : {}", source, e);
        }
        return StringUtils.EMPTY;
    }

    public static String decodeURL(String source){
        try {
            return URLDecoder.decode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error("decodeURL error, source : {}", source, e);
        }
        return StringUtils.EMPTY;
    }


}
