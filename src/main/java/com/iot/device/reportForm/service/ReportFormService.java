package com.iot.device.reportForm.service;

import com.iot.base.config.Global;
import com.iot.base.jedis.JedisClient;
import com.iot.base.result.IotResult;
import com.iot.device.manager.dao.DeviceTypeMapper;
import com.iot.device.reportForm.pojo.DeviceTypeCount;
import com.iot.device.reportForm.pojo.ReportCount;
import com.iot.device.reportForm.pojo.ReportInLineCount;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhengnaishan
 * @date 2019/4/15 0015
 * @describe :数据报表service
 */
@Service
public class ReportFormService {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    private final SimpleDateFormat df = new SimpleDateFormat("MM-dd-HH");//设置日期格式
    /**
     * 获取每小时上报数据个数
     * @Param count : 获取个数
     */
    public IotResult getReportCount(int count){
     List<ReportCount> result = new ArrayList();

     Date now = new Date();
     String time = df.format(now);
     String reportCount = jedisClient.get(Global.REPORT_COUNTS+":"+time);
     if(reportCount != null){
         //加入当前时间的统计个数
         result.add(new ReportCount(now,Integer.parseInt(reportCount)));
     }
     //遍历之前的个数
     for(int i=1;i<count;i++){
        Date pre = new Date(now.getTime()-i*60*60*1000);
        String preTime = df.format(pre);
        String preReportCount = jedisClient.get(Global.REPORT_COUNTS+":"+preTime);
        if(preReportCount != null){
            result.add(new ReportCount(pre,Integer.parseInt(preReportCount)));
        }else{
            break;
        }
     }
     return IotResult.ok(result);
    }


    public IotResult getDeviceTypeCount(){
     List<DeviceTypeCount> list = deviceTypeMapper.getTypeWithCount();
     return IotResult.ok(list);
    }


    /**
     * 从redis中获取指定个数的在线设备、离线统计
     * @param count
     * @return
     */
    public IotResult getInlineCount(int count){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd^HH-mm");
        Date now = new Date();
        List<ReportInLineCount> result = new ArrayList();
        for(int i=0,index=0; i <count;index++){//index为次数，每次一分钟
           Date pre = new Date(now.getTime()-index*60*1000);
           String inlineCount = jedisClient.get(Global.INLINE_COUNT+":"+dateFormat.format(pre));
           String offlineCount = jedisClient.get(Global.OFFLINE_COUNT+":"+dateFormat.format(pre));
           if(inlineCount!=null&&offlineCount!=null){
               ReportInLineCount report =
                       new ReportInLineCount(pre,Integer.parseInt(inlineCount),Integer.parseInt(offlineCount));
               result.add(report);
               i++;
           }
           //时间判断，必须加入，否则可能进入死循环(两边都是秒为单位)
            if(index*60 > 5*60*60){//5小时之内
                System.out.println(dateFormat.format(pre));
                break;
            }

        }
        return IotResult.ok(result);
    }
}
