package com.nstepa.wc.admin.services;


import com.baomidou.mybatisplus.service.IService;
import com.nstepa.wc.admin.form.StinkyInfoForm;
import com.nstepa.wc.beans.UploadStinky;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

	/**
	 * "取特定恶臭类型的信息，展示在地图上
	 如果stinkyType默认是全部/或特定类型，全部时可以为空"
	 * @param minLongitude
	 * @param maxLongitude
	 * @param minLatitude
	 * @param maxLatitude
	 * @param stinkyType
	 * @return
	 */
	List<Map<Object, Object>> getNowAreaStinkyInfos(BigDecimal minLongitude, BigDecimal maxLongitude, BigDecimal minLatitude, BigDecimal maxLatitude,
													Integer stinkyType);
}
