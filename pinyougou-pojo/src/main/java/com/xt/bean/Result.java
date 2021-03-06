package com.xt.bean;

import java.io.Serializable;

/**
 * 封装返回结果
 */
public class Result implements Serializable {

    private Boolean success; // 是否成功
    private String message;  // 返回信息

    public Result() {
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
