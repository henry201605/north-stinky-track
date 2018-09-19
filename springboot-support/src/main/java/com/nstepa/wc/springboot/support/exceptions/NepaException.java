package com.nstepa.wc.springboot.support.exceptions;

import com.nstepa.wc.springboot.support.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NepaException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(NepaException.class);

    protected int code;

    public NepaException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
    }
    public NepaException(int code, String msg) {
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
