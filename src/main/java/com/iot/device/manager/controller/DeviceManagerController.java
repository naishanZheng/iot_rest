package com.iot.device.manager.controller;

import com.iot.base.controller.IotController;
import com.iot.base.result.IotResult;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.config.pojo.CommandTemplet;
import com.iot.device.config.service.CommandConfigService;
import com.iot.device.manager.dao.DeviceManagerMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.manager.service.DeviceManagerService;
import com.iot.device.manager.service.DeviceTypeService;
import com.iot.security.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0003
 * @describe :设备管理controller
 */
@Controller
@RequestMapping("/device/manager")
public class DeviceManagerController extends IotController<DeviceManagerService, DeviceManagerMapper,Device> {

    @Autowired
    private DeviceManagerService deviceManagerService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private CommandConfigService commandConfigService;
    @RequestMapping("/getByPage")
    @ResponseBody
    public Object getByPage(Device device){
       return   super.getByPage(device);
    }


    @RequestMapping("/get")
    @ResponseBody
    public Object get(Device device){
        return  super.get(device);
    }

    @RequestMapping("/save")
    @ResponseBody
    @Permission("device:edit")
    public Object save(Device device){
        return  super.save(device);
    }


    /**
     * 将设备注册到easyiot平台
     * @param device
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    @Permission("device:synchronous")
    public Object register(Device device){
      return deviceManagerService.iotRegister(device);
    }



    @RequestMapping("/getCommandTemplets")
    @ResponseBody
    public Object getCommandTemplets(Device device){
        try {
            DeviceType deviceType = deviceTypeService.get(device.getDeviceType());
            CommandConfig commandConfig = commandConfigService.get(deviceType.getCommandConfig());
            List<CommandTemplet> list = commandConfigService.getTemplets(commandConfig);
            return IotResult.ok(list);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }


}
