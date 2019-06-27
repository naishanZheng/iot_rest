package com.iot.product.smartTerminal.dao;

import com.iot.base.dao.BaseMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.product.smartTerminal.pojo.WarningConfig;

/**
 * @author zhengnaishan
 * @date 2019/5/9 0009
 * @describe :
 */
public interface WarningConfigMapper extends BaseMapper<WarningConfig> {
 WarningConfig  getByDevice(Device device);
}
