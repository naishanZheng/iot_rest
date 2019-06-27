package com.iot.device.manager.controller;

import com.iot.base.controller.IotController;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.manager.dao.DeviceTypeMapper;
import com.iot.device.manager.service.DeviceTypeService;
import com.iot.security.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :设备类型管理controller
 */
@Controller
@RequestMapping("/device/deviceType")
public class DeviceTypeController extends IotController<DeviceTypeService, DeviceTypeMapper, DeviceType> {

    @RequestMapping("/getByPage")
    @ResponseBody
    public Object getByPage(DeviceType deviceType){
      return super.getByPage(deviceType);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(DeviceType deviceType){
        return super.get(deviceType);
    }

    @RequestMapping("/save")
    @ResponseBody
    @Permission("deviceType:edit")
    public Object save(DeviceType deviceType){
        return super.save(deviceType);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission("deviceType:delete")
    public Object delete(DeviceType deviceType){
        return super.delete(deviceType);
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public Object getAll(){
        return super.getAll();
    }
}
