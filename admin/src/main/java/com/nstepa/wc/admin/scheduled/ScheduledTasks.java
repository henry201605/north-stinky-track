package com.nstepa.wc.admin.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 取当前时间、当前位置的天气信息，包括温湿度、风向、风强、天气，天气的更新时刻
     *每隔30分钟到指定的接口获取
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void getCurrentWeather() {
        log.info("The time is now {}-- half an hour --->", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}--- one minute--->", dateFormat.format(new Date()));
    }
}