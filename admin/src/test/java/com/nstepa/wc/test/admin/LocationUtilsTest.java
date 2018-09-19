package com.nstepa.wc.test.admin;

import com.nstepa.wc.admin.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.nstepa.wc.commons.utils.LocationUtils.getDistance;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles({"dev"})
public class LocationUtilsTest {
	public Logger logger = LoggerFactory.getLogger(LocationUtilsTest.class);

	@Test
	public void getDistanceTest() {
		double distance = getDistance(34.2675560000, 108.9534750000,
				34.2464320000, 108.9534750000);

		logger.info("距离" + distance / 1000 + "公里");
	}

}
