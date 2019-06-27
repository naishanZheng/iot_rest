package com.iot.device.manager.controller;

import com.iot.base.controller.PageController;
import com.iot.base.result.IotResult;
import com.iot.device.manager.dao.ReportDataMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.ReportData;
import com.iot.device.manager.service.DeviceManagerService;
import com.iot.device.manager.service.ReportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/7 0007
 * @describe :上报数据Controller
 */
@Controller
@RequestMapping("/device/reportData")
public class ReportDataController extends PageController<ReportDataService, ReportDataMapper, ReportData> {
    @Autowired
    private ReportDataService reportDataService;
    @Autowired
    private DeviceManagerService deviceManagerService;
    @RequestMapping("/getByDevice")
    @ResponseBody
    public Object getByDevice(Device device){

        Map result = reportDataService.getByDevice(device);
        return IotResult.ok(result);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAll(){
        return super.getAll();
    }
}
