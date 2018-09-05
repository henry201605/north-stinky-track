package com.nepa.wc.admin.controller;

import com.nepa.wc.commons.pojos.Response;
import com.nepa.wc.springboot.support.controller.BaseController;
import com.nepa.wc.springboot.support.exceptions.NepaException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Api(value = "ok", description = "项目运行情况")
@RequestMapping
@Controller
@Validated
public class OKRest extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(OKRest.class);


    @ApiOperation(value = "查看项目是否健在", notes = "查看项目是否健在", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    @ResponseBody
//    @LoginRequired
    public Response getUserInfo() {

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        logger.info("date -> {}", date);

        if(1+1==2){
            throw new NepaException(333, "");
        }

        return ok("项目正常运行，date:" + date);
    }
}