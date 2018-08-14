package com.example.form.bean.response;

/**
 * Created by cj on 2018/6/20.
 */
public class BaseResponse {
    private String msg;
    private boolean success;

    public BaseResponse(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
