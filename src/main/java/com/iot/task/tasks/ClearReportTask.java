package com.iot.task.tasks;

import com.iot.device.manager.pojo.ReportData;
import com.iot.device.manager.service.ReportDataService;
import com.iot.task.BaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengnaishan
 * @date 2019/4/25 0025
 * @describe :定时清理ReportData数据任务
 */
@Component
public class ClearReportTask extends BaseTask {
    @Autowired
    private ReportDataService reportDataService;

    /**
     * 每天12点执行清理3天之前的数据
     */
    @Override
    public void doTask() {
        logger.info("=================定时清除上报数据开始===================");
        ReportData reportData = new ReportData();
        Date now = new Date();
        //3天之前
        Date preTime = new Date(now.getTime()-3*24*60*60*1000);
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置三天前时间
        logger.info("当前时间>>>"+dateFormat.format(now));
        logger.info("3天前时间>>>"+dateFormat.format(preTime));
        reportData.setLastMessageTime(dateFormat.format(preTime));
        reportDataService.deleteReportDataBeforeLastMessageTime(reportData);
        logger.info("=================定时清除上报数据结束===================");
    }
}
