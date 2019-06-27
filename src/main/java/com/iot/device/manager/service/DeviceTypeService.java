package com.iot.device.manager.service;

import com.iot.base.service.IotService;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.manager.dao.DeviceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :
 */
@Service
public class DeviceTypeService extends IotService<DeviceTypeMapper, DeviceType> {
    public DeviceType getByDevSerial(String devSerial){
        return dao.getByDevSerial(devSerial);
    }
}
