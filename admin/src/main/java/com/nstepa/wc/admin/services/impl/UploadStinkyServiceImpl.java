package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.consts.NstepaConst;
import com.nstepa.wc.admin.form.StinkyInfoForm;
import com.nstepa.wc.admin.services.UploadStinkyService;
import com.nstepa.wc.beans.UploadStinky;
import com.nstepa.wc.commons.utils.BeanUtils;
import com.nstepa.wc.commons.utils.DateUtil;
import com.nstepa.wc.commons.utils.LocationUtils;
import com.nstepa.wc.mybatis.support.mappers.UploadStinkyMapper;
import com.nstepa.wc.redis.utils.RedisUtils;
import com.nstepa.wc.springboot.support.ResponseCode;
import com.nstepa.wc.springboot.support.exceptions.NepaException;
import com.nstepa.wc.springboot.support.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Service
public class UploadStinkyServiceImpl extends ServiceImpl<UploadStinkyMapper, UploadStinky> implements
		UploadStinkyService {
	private Logger logger = LoggerFactory.getLogger(UploadStinkyServiceImpl.class);

	@Value("${upload.distanceconfig}")
	private Double distanceconfig;
	@Resource
	private RedisUtils redisUtils;

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
		uploadStinky.setUserId(userId);
		uploadStinky.setCreateUid(userId);
		insert(uploadStinky);

		//上传的信息存入到redis里，并设置过期时间(1hour)
		StringBuffer keySb = new StringBuffer();
		keySb.append(NstepaConst.STINKY_PRE).append(uploadStinky.getId());
		redisUtils.hmset(keySb.toString(), BeanUtils.transBean2Map(uploadStinky), NstepaConst.STINKY_EXPIRE_TIME);
	}


	public List<UploadStinky> getStinkyInfoByPageSize(Integer nowPage, int pageSize) {
		Page<UploadStinky> uploadStinkyPage = selectPage(new Page<UploadStinky>(nowPage, pageSize),
				new EntityWrapper<UploadStinky>()
						.eq("create_uid", UserRequest.getCurrentUser().getId())
						.eq("del_flag", 0)
						.orderBy("create_time", false)
		);
		return uploadStinkyPage.getRecords();
	}


	private void validateUpload(double latitude, double longitude, Integer userId) {
		EntityWrapper<UploadStinky> entityWrapper = new EntityWrapper<>();
		entityWrapper.eq("create_uid", userId).eq("del_flag", 0);
		entityWrapper.between("create_time", DateUtil.beforeOneHourToNowDate(), new Date());
		List<UploadStinky> uploadStinkyList = selectList(entityWrapper);
		if (uploadStinkyList != null && uploadStinkyList.size() > 0) {
			uploadStinkyList.forEach(t -> {
				if (isOneHourAtTheSamePlace(t.getLatitude(), t.getLongitude(), latitude, longitude)) {
					throw new NepaException(ResponseCode.BAD_REQUEST.getCode(), "同一地点（1000米）内，1小时只能提交一次结果");
				}
			});
		}
	}

	/**
	 * 判断同一地点（1000米内）1个小时之内只能上传1次结果
	 *
	 * @param latitude
	 * @param longitude
	 * @param latitudeNew  新提交的纬度
	 * @param longitudeNew 新提交的经度
	 * @return true:已经上传过，不能再传；false:可以上传
	 */
	private boolean isOneHourAtTheSamePlace(double latitude, double longitude, double latitudeNew, double longitudeNew) {
		Double distance = LocationUtils.getDistance(latitude, longitude, latitudeNew, longitudeNew);
		if (distanceconfig.compareTo(distance / 1000) < 0) return false;
		return true;
	}

	@Override
	public List<Map<Object, Object>> getNowAreaStinkyInfos(BigDecimal minLongitude, BigDecimal maxLongitude, BigDecimal
			minLatitude, BigDecimal maxLatitude, Integer stinkyType) {

		Set<String> keys = redisUtils.searchLike(NstepaConst.STINKY_PRE);
		List<Map<Object,Object>> stinkyInfoMapList = new ArrayList<>();
		keys.forEach((String t) -> {
			Map<Object, Object> hMap = redisUtils.hmget(t);

			BigDecimal longitude = new BigDecimal((String) hMap.get("longitude"));
			BigDecimal latitude = new BigDecimal((String) hMap.get("latitude"));
			Integer stinkyTypeId = Integer.valueOf((String)hMap.get("stinkyType"));

			if (stinkyType != null && !stinkyTypeId.equals(stinkyType)) return;

			if (longitude.compareTo(minLongitude) >= 0 && longitude.compareTo(maxLongitude) <= 0 && latitude.compareTo(minLatitude) >= 0 && latitude
					.compareTo(maxLatitude) <= 0) { //经纬度范围内

				stinkyInfoMapList.add(hMap);
			}

			logger.info("stinkyInfoMapList--->" + stinkyInfoMapList);
		});

		return stinkyInfoMapList;
	}
}
