package com.hf.ciker.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

	private static final long serialVersionUID = -1635625883264447862L;

	private String code;

    private String msg;

    private T data;

    private ServerResponse(String code){
        this.code = code;
    }

    private ServerResponse(String code, T data){
        this.code = code;
        this.data = data;
    }

    private ServerResponse(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    //使之不在json序列化结果当中
    @JsonIgnore
    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public String getCode(){
        return code;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }


    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }


    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(String errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(ResponseCode responseCode){
        return new ServerResponse<T>(responseCode.getCode(),responseCode.getDesc());
    }
}
