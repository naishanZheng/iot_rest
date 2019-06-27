package com.iot.device.manager.dao;

import com.iot.base.dao.IotMapper;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.reportForm.pojo.DeviceTypeCount;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :设备类型dao
 */
public interface DeviceTypeMapper extends IotMapper<DeviceType> {

   List<DeviceTypeCount> getTypeWithCount();

   DeviceType getByDevSerial(String devSerial);
}
