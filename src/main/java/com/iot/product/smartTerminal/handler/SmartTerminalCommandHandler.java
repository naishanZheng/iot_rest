package com.iot.product.smartTerminal.handler;

import com.iot.base.result.IotResult;
import com.iot.base.utils.HttpClientUtil;
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
 * @describe :智能路灯指令控制器
 */
@Component
public class SmartTerminalCommandHandler extends BaseCommandHandler<DeviceCommandService , DeviceCommand> {

    @Override
    protected IotResult execute(List<String> devSerials , Map params ,DeviceType deviceType) {
        if(deviceType!=null && "SmartTerminal".equals(deviceType.getEname())){
                //登录参数
                String loginJson = "{\"serverId\":\""+"jiayingdev01"+"\",\"password\":\""+"jsj2018"+"\"}";
                //登录
                String json = HttpClientUtil.doPostJson("https://www.easy-iot.cn/idev/3rdcap/server/login", loginJson);
                Map loginResult = JsonUtils.jsonToPojo(json, Map.class);
                //获取登录token
                String accessToken = (String) loginResult.get("accessToken");

                Map headerMap = new HashMap<String, String>();
                headerMap.put("serverID", "jiayingdev01");
                headerMap.put("accessToken", accessToken);

                //设置指令参数
                Map commandMap = new HashMap();
                commandMap.put("method", "MSG_DOWN");
                commandMap.put("params", params);

                //发送成功设备
                List<String> success = new ArrayList<>();
                List<String> failure = new ArrayList<>();

                String paramsToString = JsonUtils.objectToJson(params);//command Map类型转String
                for(String devSerial : devSerials){
                    Map commandResponse = null;
                    commandMap.put("devSerial", devSerial);
                    //发送指令
                    //logger.info(paramsToString);
                    String response = HttpClientUtil.doPostJsonWithHeader("https://www.easy-iot.cn/idev/3rdcap/dev-control/urt-command",
                            JsonUtils.objectToJson(commandMap), headerMap );
                    commandResponse = JsonUtils.jsonToPojo(response, Map.class);
                    if(!"0".equals(commandResponse.get("optResult"))){
                        //响应码不为200
                        failure.add(devSerial);
                        DeviceCommand deviceCommand = new DeviceCommand(devSerial,paramsToString,2);
                        super.save(deviceCommand);
                        continue;
                    }else{
                        DeviceCommand deviceCommand = new DeviceCommand(devSerial,paramsToString,0);
                        deviceCommand.setId((String)commandResponse.get("commandId"));
                        save(deviceCommand);
                        success.add(devSerial);
                    }

                }
                Map result = new HashMap<>();
                result.put("success",success);
                result.put("failure",failure);
                return IotResult.ok(result);
            }else {
            return  null;
           }
    }

}
