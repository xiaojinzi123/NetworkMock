package com.move.mock.util;

import java.io.Serializable;

/**
 * 对外输出的接口都需要用这个对象包一下
 */
public class ResultBean<T> implements Serializable {

    public static final int RES_CODE_FAIL = -1;
    public static final int RES_CODE_SUCCESS = 0;

    public static final String RES_MESSAGE_SUCCESS = "ok";

    /**
     * 当等于 {@link #RES_CODE_SUCCESS} 的时候表示成功,其他表示失败
     * 当等于 {@link #RES_CODE_FAIL} 的时候表示统一的失败
     */
    private int resCode;

    /**
     * 当resCode等于 {@link #RES_CODE_SUCCESS} 的时候表示成功
     * 此时这个值是 {@link #RES_MESSAGE_SUCCESS},其他时候表示错误信息
     */
    private String resMessage;

    /**
     * 表示输出的数据
     */
    private T data;

    public ResultBean(int resCode, String resMessage, T data) {
        this.resCode = resCode;
        this.resMessage = resMessage;
        this.data = data;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public static <T> ResultBean<T> success() {
        return new ResultBean<T>(RES_CODE_SUCCESS, RES_MESSAGE_SUCCESS, null);
    }

    public static <T> ResultBean<T> success(T t) {
        return new ResultBean<T>(RES_CODE_SUCCESS, RES_MESSAGE_SUCCESS, t);
    }

    public static <T> ResultBean<T> error(String resMessage) {
        return new ResultBean<T>(RES_CODE_FAIL, resMessage, null);
    }

}
