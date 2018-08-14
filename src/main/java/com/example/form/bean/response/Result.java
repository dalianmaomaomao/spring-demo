package com.example.form.bean.response;

import java.io.Serializable;

/**
 * Created by cj on 2018/8/7.
 */
public class Result implements Serializable{
    private int status;
    private String msg;
    private Object data;

    public Result(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result getResult(int status,String msg,Object data){
        return new Result(status, msg, data);
    }
    public static Result getSuccess(Object data){
        return getResult(200,"",data);
    }
    public static Result getFail(int status,String msg){
        return getResult(status,msg,null);
    }
    public static Result getError(String msg){
        return getResult(500,msg,null);
    }
}
