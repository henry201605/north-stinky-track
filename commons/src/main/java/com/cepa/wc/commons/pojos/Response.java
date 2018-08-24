package com.cepa.wc.commons.pojos;

import com.cepa.wc.commons.utils.Payload;
import com.cepa.wc.commons.utils.GsonUtils;

/**
 * Created by mabinbin on 05/06/2017.
 */
public class Response<T> {
    public static final String OK_MSG = "ok";
    public static final Response OK = new Response();

    public Object code;
    public String msg;
    public T data;

    public Response() {
        this.code = 200;
        this.msg = OK_MSG;
    }

    public Response(Object code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(T data) {
        this();
        this.data = data;
    }

    public Response(Object code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Response simple(Object code, String msg) {
        return new Response(code, msg);
    }

    public Response code(Object code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * 使用Payload作为data
     *
     * @param data
     * @return
     */
    public static Response payload(Object... data) {
        return data(Payload.wrap(data));
    }

    public static Response data(Object data) {
        if (data == null) return new Response();
        return new Response(data);
    }

    @Override
    public String toString() {
        return GsonUtils.format(this);
    }
}
