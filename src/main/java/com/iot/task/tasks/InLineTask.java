package com.iot.task.tasks;

import com.iot.base.config.Global;
import com.iot.base.jedis.JedisClient;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.service.DeviceManagerService;
import com.iot.device.manager.service.ReportDataService;
import com.iot.task.BaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengnaishan
 * @date 2019/4/26 0026
 * @describe :设备在线检测
 *             检测规则：3小时前接受到reportData则为在线
 */
@Component
public class InLineTask extends BaseTask {
    @Autowired
    private DeviceManagerService deviceManagerService;
    @Autowired
    private JedisClient jedisClient;
    @Override
    @Transactional(readOnly = false)
    protected void doTask() {
       Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM-dd^HH-mm");
//       logger.info("定时任务开始");
       Date threeHoursBefore = new Date(now.getTime()-3*60*60*1000);
       Device device = new Device();
       device.setLastMessageTime(dateFormat.format(threeHoursBefore));
       int inlineCount = deviceManagerService.getInlineCount(device);
       int offLineCount = deviceManagerService.getOffLineCount(device);
       //存放数据
       jedisClient.set(Global.INLINE_COUNT+":"+dateFormat2.format(now),""+inlineCount);
       jedisClient.expire(Global.INLINE_COUNT+":"+dateFormat2.format(now),Global.INLINE_COUNT_EXPIRE_TIME);
       jedisClient.set(Global.OFFLINE_COUNT+":"+dateFormat2.format(now),""+offLineCount);
       jedisClient.expire(Global.OFFLINE_COUNT+":"+dateFormat2.format(now),Global.OFFLINE_COUNT_EXPIRE_TIME);
     /*  logger.info("在线个数"+inlineCount);
       logger.info("离线个数"+offLineCount);*/
    }
}
