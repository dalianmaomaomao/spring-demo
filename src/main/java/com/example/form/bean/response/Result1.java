package com.example.form.bean.response;

import java.io.Serializable;

/**
 * Created by cj on 2018/8/12.
 */
public class Result1 implements Serializable {
    private int status;
    private String msg;
    private Object data;

    public Result1(int status, String msg, Object data) {
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

    public static Result1 getResult(int status, String msg, Object data) {
        return new Result1(status, msg, data);
    }

    public static Result1 getSuccess(String msg, Object data) {
        return getResult(200, msg, data);
    }

    public static Result1 getFail(int status, String msg) {
        return getResult(status, msg, null);
    }
}
