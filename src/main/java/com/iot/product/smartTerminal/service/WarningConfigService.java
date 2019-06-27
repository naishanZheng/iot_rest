package com.iot.product.smartTerminal.service;

import com.iot.base.service.BaseService;
import com.iot.device.manager.pojo.Device;
import com.iot.product.smartTerminal.dao.WarningConfigMapper;
import com.iot.product.smartTerminal.pojo.WarningConfig;
import org.springframework.stereotype.Service;

/**
 * @author zhengnaishan
 * @date 2019/5/9 0009
 * @describe :
 */
@Service
public class WarningConfigService extends BaseService<WarningConfig, WarningConfigMapper> {

    public WarningConfig getByDevice(Device device){
        return dao.getByDevice(device);
    }
}
