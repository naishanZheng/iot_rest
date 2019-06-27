package com.iot.product.smartTerminal.controller;

import com.iot.base.controller.BaseController;
import com.iot.base.result.IotResult;
import com.iot.device.manager.pojo.Device;
import com.iot.product.smartTerminal.dao.WarningConfigMapper;
import com.iot.product.smartTerminal.pojo.WarningConfig;
import com.iot.product.smartTerminal.service.WarningConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhengnaishan
 * @date 2019/5/9 0009
 * @describe :预警配置
 */
@Controller
@RequestMapping("/device/product/smartTerminal/warningConfig")
public class WarningConfigController extends BaseController<WarningConfigService, WarningConfigMapper, WarningConfig> {

    @RequestMapping("/get")
    @ResponseBody
    public Object getByDevice(WarningConfig config){
        return super.get(config);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(WarningConfig warningConfig){
        return super.save(warningConfig);
    }
}
