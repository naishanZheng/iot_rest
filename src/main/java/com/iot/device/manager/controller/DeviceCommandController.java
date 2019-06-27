package com.iot.device.manager.controller;

import com.iot.base.controller.IotController;
import com.iot.base.result.IotResult;
import com.iot.device.manager.dao.DeviceCommandMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.DeviceCommand;
import com.iot.device.manager.pojo.DeviceCommand_format;
import com.iot.device.manager.service.DeviceCommandService;
import com.iot.security.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/16 0016
 * @describe :发送指令
 */
@Controller
@RequestMapping("/device/command")
public class DeviceCommandController extends IotController<DeviceCommandService, DeviceCommandMapper, DeviceCommand> {

    /**
     * 发送指令
     *       */
    @RequestMapping("/sendCommand")
    @ResponseBody
    @Permission("device:sendCommand")
    public Object sendCommand(@RequestBody Map command){
        List<String> devSerials = (List)command.get("devSerials");
        Map params = (Map)command.get("params");
        try{
            IotResult result =  s.sendCommand(devSerials,params);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统异常");
        }

    }

    @RequestMapping("/getCommandFormat")
    @ResponseBody
    public Object getCommandFormat(Device device){
        try {
           return  s.getCommandFormat(device);
        }catch (Exception e){
            e.printStackTrace();
           return IotResult.build(500,"系统出现异常");
        }
    }
}
