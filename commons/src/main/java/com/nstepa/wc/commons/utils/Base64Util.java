package com.nstepa.wc.commons.utils;

import java.util.Base64;

public class Base64Util {

    public static String encoder(String str) {
        String result = str;

        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] textByte = str.getBytes("UTF-8");
            result = encoder.encodeToString(textByte);
        } catch (Exception e) {
            // ignore
        }

        return result;
    }

    public static String decoder(String str) {
        String result = str;

        try {
            Base64.Decoder decoder = Base64.getDecoder();

            result = new String(decoder.decode(str), "UTF-8");
        } catch (Exception e) {
            // ignore
        }

        return result;
    }
}
