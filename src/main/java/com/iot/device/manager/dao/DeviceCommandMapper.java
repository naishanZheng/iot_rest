package com.iot.device.manager.dao;

import com.iot.base.dao.IotMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.DeviceCommand;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/16 0016
 * @describe :
 */
public interface DeviceCommandMapper extends IotMapper<DeviceCommand> {
    List<DeviceCommand> getByCount(Device device);
}
