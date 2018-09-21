package com.nstepa.wc.admin.controller;


import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.springboot.support.annotations.LoginRequired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Api(value = "UserController", description = "用户")
@RestController
@RequestMapping(value = "user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@LoginRequired
	@ApiOperation(value="系统登录验证", notes="验证用户登录信息")
	@PostMapping(value = "")
	public Response<Map<String,String>> doLogin(){
		logger.info("user");
		return null;
	}
}

