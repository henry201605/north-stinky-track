package com.nstepa.wc.test.admin;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.nstepa.wc.commons.utils.LocationUtils.getDistance;

public class LocationUtilsTest {
	public Logger logger = LoggerFactory.getLogger(LocationUtilsTest.class);

	@Test
	public void getDistanceTest() {
		double distance = getDistance(34.2675560000, 108.9534750000,
				34.2464320000, 108.9534750000);

		logger.info("距离" + distance / 1000 + "公里");
	}

}
