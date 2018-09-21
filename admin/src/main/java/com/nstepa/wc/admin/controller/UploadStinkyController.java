package com.nstepa.wc.admin.controller;


import com.nstepa.wc.admin.form.StinkyInfoForm;
import com.nstepa.wc.admin.services.UploadAdviceService;
import com.nstepa.wc.admin.services.UploadStinkyService;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.springboot.support.annotations.LoginRequired;
import com.nstepa.wc.springboot.support.controller.BaseController;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Api(value = "UploadStinkyController", description = "用户上传恶臭信息")
@RestController
@RequestMapping(value = "uploadStinky")
public class UploadStinkyController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UploadStinkyController.class);

	@Autowired
	private UploadStinkyService uploadStinkyService;

	@LoginRequired
	@ApiOperation(value="用户上传恶臭信息", notes="用户提交当前位置恶臭信息")
	@PostMapping(value = "")
	public Response uploadStinkyInfo(@RequestBody @Valid StinkyInfoForm stinkyInfoForm){
		uploadStinkyService.uploadStinkyInfo(stinkyInfoForm);
		logger.info("stinkyInfoForm---->" + stinkyInfoForm.toString());
		return success();
	}

}

