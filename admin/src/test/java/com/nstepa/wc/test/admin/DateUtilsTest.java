package com.nstepa.wc.test.admin;

import com.nstepa.wc.admin.Application;
import com.nstepa.wc.commons.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: zhangwei
 * @Date: 2018/9/21 17:00
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles({"dev"})
public class DateUtilsTest {
	public Logger logger = LoggerFactory.getLogger(LocationUtilsTest.class);

	@Test
	public void beforeOneHourToNowDate(){
  /* HOUR_OF_DAY 指示一天中的小时 */
  Calendar calendar = Calendar.getInstance();
  calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
	System.out.println("当前的时间：" + df.format(new Date()));
		System.out.println("一个小时前的时间：" + DateUtil.beforeOneHourToNowDate());



	}

}
