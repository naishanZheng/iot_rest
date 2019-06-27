package com.iot.device.reportForm.controller;

import com.iot.base.result.IotResult;
import com.iot.device.reportForm.service.ReportFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhengnaishan
 * @date 2019/4/15 0015
 * @describe :报表
 */
@Controller
@RequestMapping("/reportForm")
public class reportFormController {
    @Autowired
    private ReportFormService reportFormService;

    /**
     * 获取数据统计
     * @return
     */
    @RequestMapping("/reportCount")
    @ResponseBody
    public IotResult getReportCount(int count){
        try {
            return reportFormService.getReportCount(count);
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/deviceTypeCount")
    @ResponseBody
    public IotResult getDeviceTypeCount(){
        try {
            return reportFormService.getDeviceTypeCount();
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/inLineCount")
    @ResponseBody
    public IotResult getInlineCount(int count){
       return reportFormService.getInlineCount(count);
    }
}
