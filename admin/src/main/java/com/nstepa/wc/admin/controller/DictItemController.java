package com.nstepa.wc.admin.controller;


import com.nstepa.wc.admin.services.DictItemService;
import com.nstepa.wc.beans.DictItem;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.springboot.support.annotations.LoginRequired;
import com.nstepa.wc.springboot.support.controller.BaseController;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Api(value = "DictItemController", description = "获取数据字典中的数据")
@RestController
@RequestMapping(value = "dictItem")
public class DictItemController extends BaseController{

	@Autowired
	private DictItemService dictItemService;

	@ApiOperation(value = "获取数据字典中的数据", notes = "获取数据字典中的数据")
	@PostMapping(value = "")
	@LoginRequired
	public Response<List<DictItem>> getDictItemByType(@ApiParam(value = "字典类型",required = true) @NotBlank(message = "字典类型不能为空") @RequestParam(required = false) String dictType) throws UnsupportedEncodingException {

		List<DictItem> dictItemList = dictItemService.getDictItemByType(dictType);

		return success(dictItemList);
	}
}

