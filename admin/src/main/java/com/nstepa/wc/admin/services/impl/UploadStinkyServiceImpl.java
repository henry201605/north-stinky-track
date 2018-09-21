package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.form.StinkyInfoForm;
import com.nstepa.wc.admin.services.UploadStinkyService;
import com.nstepa.wc.beans.UploadStinky;
import com.nstepa.wc.commons.utils.DateUtil;
import com.nstepa.wc.commons.utils.LocationUtils;
import com.nstepa.wc.mybatis.support.mappers.UploadStinkyMapper;
import com.nstepa.wc.springboot.support.ResponseCode;
import com.nstepa.wc.springboot.support.exceptions.NepaException;
import com.nstepa.wc.springboot.support.request.UserRequest;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Service
public class UploadStinkyServiceImpl extends ServiceImpl<UploadStinkyMapper, UploadStinky> implements UploadStinkyService {

	@Value("${upload.distanceconfig}")
	private Double distanceconfig;

	@Override
	public void uploadStinkyInfo(StinkyInfoForm stinkyInfoForm) {
		double latitude = stinkyInfoForm.getLatitude();
		double longitude = stinkyInfoForm.getLongitude();
		Integer userId = UserRequest.getCurrentUser().getId();
		validateUpload(latitude, longitude, userId);

		UploadStinky uploadStinky = new UploadStinky();
		uploadStinky.setFeelType(stinkyInfoForm.getFeelType());
		uploadStinky.setStinkyType(stinkyInfoForm.getStinkyType());
		uploadStinky.setWindDirect(stinkyInfoForm.getWindDirect());
		uploadStinky.setWindPower(stinkyInfoForm.getWindPower());
		uploadStinky.setLatitude(latitude);
		uploadStinky.setLongitude(longitude);
		uploadStinky.setOccurTime(stinkyInfoForm.getOccurTime());
		uploadStinky.setNote(stinkyInfoForm.getNote());
		uploadStinky.setUserName(UserRequest.getCurrentUser().getUserName());
//		uploadStinky.setUserId(userId);
		uploadStinky.setCreateUid(userId);
		insert(uploadStinky);
	}

	private void validateUpload(double latitude, double longitude, Integer userId) {
		EntityWrapper<UploadStinky> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("create_uid", userId).eq("del_flag",0);
		entityWrapper.between("create_time", DateUtil.beforeOneHourToNowDate(),new Date());
		List<UploadStinky> uploadStinkyList = selectList(entityWrapper);
		if (uploadStinkyList != null && uploadStinkyList.size() > 0) {
			uploadStinkyList.forEach(t -> {
				if (isOneHourAtTheSamePlace(t.getLatitude(), t.getLongitude(), latitude, longitude)) {
					throw new NepaException(ResponseCode.BAD_REQUEST.getCode(),"同一地点（1000米）内，1小时只能提交一次结果");
				}
			});
		}
	}

	/**
	 * 判断同一地点（1000米内）1个小时之内只能上传1次结果
	 * @param latitude
	 * @param longitude
	 * @param latitudeNew  新提交的纬度
	 * @param longitudeNew 新提交的经度
	 * @return true:已经上传过，不能再传；false:可以上传
	 */
	private boolean isOneHourAtTheSamePlace(double latitude, double longitude, double latitudeNew, double longitudeNew) {
		Double distance = LocationUtils.getDistance(latitude, longitude,
				latitudeNew, longitudeNew);
		if(distanceconfig.compareTo(distance/1000) < 0) return false;
		return true;
	}
}
