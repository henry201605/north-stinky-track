package com.nepa.wc.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

/**
 * Cookie工具类,封装Cookie常用操作
 */
public class CookieUtil {

    /**
     * 设置cookie有效期，根据需要自定义[本系统设置为30天]
     */
    private final static int COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 1;

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    public static void disPlayCookie(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        Iterator<String> it = cookieMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Cookie value = cookieMap.get(key);
            System.out.println("key: " + key + "; value: " + value);
        }
    }

    /**
     * @param request
     * @param name
     * @desc 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0)
            return null;
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(name))
                continue;
            cookie = cookies[i];
            if (request.getServerName().equals(cookie.getDomain()))
                break;
        }

        return cookie;
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * @param request
     * @param name
     * @return
     * @desc 根据Cookie名称得到Cookie的值，没有返回Null
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * @param response
     * @param name
     * @param value
     * @desc 添加一条新的Cookie信息，默认有效时间为一个月
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, COOKIE_MAX_AGE);
    }

    /**
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @desc 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        if (value == null)
            value = "";
        Cookie cookie = new Cookie(name, value);
        if (maxAge != 0) {
            cookie.setMaxAge(maxAge);
        } else {
            cookie.setMaxAge(COOKIE_MAX_AGE);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 保存Cookies
     *
     * @param response servlet请求
     * @param value    保存值
     * @author jxf
     */
    public static HttpServletResponse setCookie2(HttpServletResponse response, String name, String value, int time) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }

    /**
     * @param response
     * @param cookie
     * @desc 删除指定Cookie
     */
    public static void removeCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * @param response
     * @param cookie
     * @param domain
     * @desc 删除指定Cookie
     */
    public static void removeCookie(HttpServletResponse response, Cookie cookie, String domain) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    /**
     * <p>
     * 删除无效cookie
     * </p>
     * <p>
     * 无效☞1.过时 2.未发布
     * </p>
     *
     * @param request
     * @param response
     * @param list
     */
    public void delectCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey)
            throws NullPointerException {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (key == deleteKey && key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                cookie.setMaxAge(0);// 设置cookie有效时间为0
                cookie.setPath("/");// 不设置存储路径
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 编码
     * @param cookieStr
     * @return
     */
    public static String encodeBase64(String cookieStr){

        try {
            cookieStr = new String(Base64.encodeBase64(cookieStr.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookieStr;
    }

    /**
     * 解码
     * @param cookieStr
     * @return
     */
    public static String decodeBase64(String cookieStr){
        try {
            cookieStr = new String(Base64.decodeBase64(cookieStr.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookieStr;
    }
}