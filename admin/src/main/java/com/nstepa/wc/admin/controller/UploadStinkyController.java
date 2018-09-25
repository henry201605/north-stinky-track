package com.nstepa.wc.admin.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nstepa.wc.admin.form.StinkyInfoForm;
import com.nstepa.wc.admin.services.UploadStinkyService;
import com.nstepa.wc.beans.UploadStinky;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.mybatis.support.PageInfo;
import com.nstepa.wc.springboot.support.annotations.LoginRequired;
import com.nstepa.wc.springboot.support.controller.BaseController;
import com.nstepa.wc.springboot.support.request.UserRequest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Api(value = "UploadStinkyController", description = "用户恶臭信息管理")
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

	@LoginRequired
	@ApiOperation(value="获取用户上传的恶臭信息", notes="获取用户上传的恶臭信息")
	@GetMapping(value = "getStinkyInfo")
	public Response<List<UploadStinky>> getStinkyInfo(@ApiParam(value = "页码",required = true)  @NotNull(message = "页码不能为空") @RequestParam(required = false) Integer nowPage){

		List<UploadStinky> uploadStinkyList = uploadStinkyService.getStinkyInfoByPageSize(nowPage, PageInfo.pageSize);
		logger.info("uploadStinkyList---->" + uploadStinkyList.toString());
		return success(uploadStinkyList);
	}

}

