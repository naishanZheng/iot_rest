package com.iot.product.smartTerminal.controller;

import com.iot.base.controller.PageController;
import com.iot.base.result.IotResult;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.service.DeviceManagerService;
import com.iot.product.smartTerminal.dao.SmartTerminalMapper;
import com.iot.product.smartTerminal.pojo.WarningInfo;
import com.iot.product.smartTerminal.service.SmartTerminalService;
import com.iot.product.smartTerminal.service.WarningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/10 0010
 * @describe :智慧路灯
 */
@Controller
@RequestMapping("/device/product/smartTerminal")
public class SmartTerminalController extends PageController<SmartTerminalService, SmartTerminalMapper,Device> {
   @Autowired
    private DeviceManagerService deviceManagerService;
   @Autowired
   private WarningInfoService warningInfoService;
    @RequestMapping("/getBaseInfo")
    @ResponseBody
    public Object getBaseInfo(Device device){
        try {
            device = deviceManagerService.get(device);
            Map temple = new HashMap<>();
            temple.put("deviceType.name","设备类型");
            temple.put("lastMessageTime","上一次上报时间");
            temple.put("name" , "设备名称");
            //temple.put("repairPerson.name","维修人员");
            temple.put("location","设备位置");
            temple.put("devSerial","序列号");
            Map result = new HashMap();
            result.put("data" , device);
            result.put("temple" , temple);
            return IotResult.ok(result);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }

    }

    @RequestMapping("/getWarnInfo")
    @ResponseBody
    public Object getDeviceInfo_warningsInfo(Device device){
        WarningInfo warningInfo = new WarningInfo();
        //设置查询条件
        warningInfo.setPage(device.getPage());
        warningInfo.setLimit(device.getLimit());
        device = deviceManagerService.get(device);
        warningInfo.setDevSerial(device.getDevSerial());
        //分页查询
        warningInfo =  warningInfoService.getByPage(warningInfo);
        List<WarningInfo> list =  warningInfo.getPageInfo().getList();
        return IotResult.ok(list);
    }

    /**
     *预警转维修状态
     */
    @RequestMapping("/repair")
    @ResponseBody
    public Object repair(Device device){
        try {
            device.setStatus(2);
            deviceManagerService.updateStatus(device);
            return IotResult.ok();
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    /**
     * 复位：维修转正常
     * @param device
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public Object reset(Device device){
        try {
            device.setStatus(0);
            deviceManagerService.updateStatus(device);
            return IotResult.ok();
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }
}
