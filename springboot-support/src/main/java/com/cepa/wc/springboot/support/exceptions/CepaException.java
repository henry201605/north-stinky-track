package com.cepa.wc.springboot.support.exceptions;

import com.cepa.wc.springboot.support.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CepaException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(CepaException.class);

    protected int code;

    public CepaException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
    }

    public CepaException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return getMessage();
    }
}
