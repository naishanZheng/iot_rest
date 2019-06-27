package com.iot.product.SmartPot.handler;

import com.alibaba.druid.support.json.JSONUtils;
import com.iot.base.result.IotResult;
import com.iot.base.utils.JsonUtils;
import com.iot.device.handler.BaseCommandHandler;
import com.iot.device.manager.pojo.DeviceCommand;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.manager.service.DeviceCommandService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/20 0020
 * @describe :智能盆栽指令执行控制器
 *
 * {"autoLight":1,"autoWater":1,"light_max":15,"light_min":3,
 * "water_max":30,"water_min":12, "waterNow":0, "lightNow":0, "getLastData":0}
 */
@Component
public class SmartPotCommandHandler extends BaseCommandHandler<DeviceCommandService, DeviceCommand> {
    @Override
    protected IotResult execute(List<String> devSerials, Map params, DeviceType deviceType) {
        if(deviceType!=null&&"SmartPot".equals(deviceType.getEname())){
            List<String> success = new ArrayList<>();
          for(String devSerial : devSerials){
              //保存数据时要把GenData放入，否则解析获取参数别名会报错，除非定义了通用指令模板
              DeviceCommand deviceCommand = new DeviceCommand(devSerial, JsonUtils.objectToJson(params),0);
              super.save(deviceCommand);
              success.add(devSerial);
          }
          Map result = new HashMap<>();
          result.put("success",success);
          return IotResult.ok(result);
        }else{
            return null;
        }
    }
}
