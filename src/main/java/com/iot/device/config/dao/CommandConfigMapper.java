package com.iot.device.config.dao;

import com.iot.base.dao.PageMapper;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.manager.pojo.Device;

/**
 * @author zhengnaishan
 * @date 2019/4/9 0009
 * @describe :
 */
public interface CommandConfigMapper extends PageMapper<CommandConfig> {
    CommandConfig getByDevice(Device device);
}
