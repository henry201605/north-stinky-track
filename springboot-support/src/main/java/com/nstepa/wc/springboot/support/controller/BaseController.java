package com.nstepa.wc.springboot.support.controller;

import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.springboot.support.ResponseCode;

/**
 * 基础Rest类.
 */
public abstract class BaseController {

    /**
     * 请求参数不对的时候返回的请求
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response badRequest(T body) {
        return statusResponse(ResponseCode.BAD_REQUEST, body);
    }

    /**
     * 请求出现服务器异常的请求
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response error(T body) {
        return statusResponse(ResponseCode.INTERNAL_SERVER_ERROR, body);
    }

    /**
     * 访问权限不足的请求
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response forbidden(T body) {
        return statusResponse(ResponseCode.FORBIDDEN, body);
    }

    /**
     * 用户/访问记录不存在的请求
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response notFound(T body) {
        return statusResponse(ResponseCode.NOT_FOUND, body);
    }

    /**
     * 无法访问方法
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response notAllow(T body) {
        return statusResponse(ResponseCode.METHOD_NOT_ALLOWED, body);
    }

    /**
     * 响应成功的请求
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> Response ok(T body) {
        return statusResponse(ResponseCode.OK, body);
    }


    /**
     * 响应简单的OK成功的请求
     *
     * @return
     */
    public static Response ok() {
        return statusResponse(ResponseCode.OK, null);
    }

    public static <T> Response statusResponse(ResponseCode responseCode) {
        return new Response(responseCode.getCode(), responseCode.getMsg(), null);
    }

    public static <T> Response statusResponse(ResponseCode responseCode, T data) {
        return new Response(responseCode.getCode(), responseCode.getMsg(), data);
    }

    public static <T> Response statusResponse(int status, String message) {
        return new Response(status, message);
    }
    public static <T> Response statusResponse(int status, String message, T data) {
        return new Response(status, message, data);
    }

    public static <T> Response success(ResponseCode responseCode, T data) {
        return new Response(responseCode.getCode(), responseCode.getMsg(), data);
    }

    public static Response success(ResponseCode responseCode){
        return new Response(responseCode.getCode(), responseCode.getMsg());
    }
}
