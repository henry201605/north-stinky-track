package com.nepa.wc.springboot.support.exceptions;

import com.google.common.collect.Lists;
import com.nepa.wc.commons.pojos.Response;
import com.nepa.wc.springboot.support.ResponseCode;
import com.nepa.wc.springboot.support.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 * henry
 */
@ControllerAdvice
public class ExceptionAdvice {

    protected Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Response errorHandler(Throwable ex) {
        logger.error(ex.getMessage(), ex);
        return BaseController.statusResponse(ResponseCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 NepaException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NepaException.class)
    public Response NepaExceptionHandler(NepaException ex) {
        logger.debug(ex.getMessage(), ex);
        return BaseController.statusResponse(ex.getCode(), ex.getMsg());
    }

    /**
     * 拦截捕捉自定义异常 ConstraintViolationException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.debug(ex.getMessage(), ex);
        List<String> msgList = ex.getConstraintViolations().stream().map(c -> c.getMessageTemplate()).collect(Collectors.toList());
        return BaseController.statusResponse(ResponseCode.BAD_REQUEST, msgList);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response handle(MethodArgumentNotValidException exception) {
        logger.debug(exception.getMessage(), exception);
        List<String> msgList = null;
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult instanceof BeanPropertyBindingResult) {
            BeanPropertyBindingResult beanPropertyBindingResult = (BeanPropertyBindingResult) bindingResult;
            msgList = beanPropertyBindingResult.getAllErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.toList());
        } else {
            msgList = Lists.newArrayList(exception.toString());
        }
        return BaseController.statusResponse(ResponseCode.BAD_REQUEST, msgList);
    }
}