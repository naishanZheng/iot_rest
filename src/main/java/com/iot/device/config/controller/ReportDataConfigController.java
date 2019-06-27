package com.iot.device.config.controller;

import com.iot.base.result.IotResult;
import com.iot.device.config.pojo.ReportDataConfig;
import com.iot.device.config.pojo.ReportDataTemplet;
import com.iot.device.config.service.ReportDataConfigService;
import com.iot.device.manager.pojo.Device;
import com.iot.security.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/3 0003
 * @describe :数据模板controller
 */
@Controller
@RequestMapping("/device/reportDataConfig")
public class ReportDataConfigController {

    @Autowired
    private ReportDataConfigService configService;

    @RequestMapping("/save")
    @ResponseBody
    @Permission("reportDataConfig:edit")
    public Object save(ReportDataConfig reportDataConfig){
      configService.save(reportDataConfig);
      return IotResult.ok();
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(ReportDataConfig reportDataConfig){
        try {
            reportDataConfig = configService.get(reportDataConfig);
            return IotResult.ok(reportDataConfig);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/getByPage")
    @ResponseBody
    @Permission("reportDataConfigs:view")
    public Object getByPage(@RequestParam(value = "page") int currentPage,
                              @RequestParam(value = "limit") int pageSize) {
        ReportDataConfig reportDataConfig = new ReportDataConfig();
        reportDataConfig.setPage(currentPage);
        reportDataConfig.setLimit(pageSize);
        reportDataConfig = configService.getByPage(reportDataConfig);
        Map result = new HashMap<>();
        result.put("count", reportDataConfig.getPageInfo().getTotal());
        result.put("data", reportDataConfig.getPageInfo().getList());
        return IotResult.ok(result);
    }

    @RequestMapping("/getTemplets")
    @ResponseBody
    public IotResult getTemplets(ReportDataConfig reportDataConfig){
        try {
            List<ReportDataTemplet> templets = configService.getTemplets(reportDataConfig);
            return IotResult.ok(templets);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission("reportDataConfig:del")
    public IotResult delete(ReportDataConfig reportDataConfig){
        try {
            configService.delete(reportDataConfig);
            return IotResult.ok();
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public  Object getAll(){
        try {
          List<ReportDataConfig> list = configService.getAll();
          return IotResult.ok(list);
        }catch (Exception e){
          return IotResult.build(500,"系统出现异常");
        }
    }

    /**
     * 加载数据模板给前端页面
     * @return
     */
    @RequestMapping("/getReportDataTemplet")
    @ResponseBody
    public Object getReportDataTemplet(Device device){
        try{
            List<ReportDataTemplet> templets = configService.getReportDataTemplet(device);
            return IotResult.ok(templets);
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }

    }
}
