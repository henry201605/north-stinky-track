package com.nstepa.wc.admin.services;


import com.baomidou.mybatisplus.service.IService;
import com.nstepa.wc.admin.form.StinkyInfoForm;
import com.nstepa.wc.beans.UploadStinky;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
public interface UploadStinkyService extends IService<UploadStinky> {
	/**
	 * 上传本人提交的恶臭信息
	 * @param stinkyInfoForm
	 */
	void uploadStinkyInfo(StinkyInfoForm stinkyInfoForm);

	List<UploadStinky> getStinkyInfoByPageSize(Integer nowPage, int pageSize);
}
