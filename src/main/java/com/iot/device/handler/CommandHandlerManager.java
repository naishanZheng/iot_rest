package com.iot.device.handler;

import com.iot.base.result.IotResult;
import com.iot.device.manager.pojo.DeviceType;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/15 0015
 * @design：观察者模式
 * @describe :处理DeviceCommand
 * 如果找不到处理类，将返回null
 */
@Component
public class CommandHandlerManager {
    private List<BaseCommandHandler> handlers = new LinkedList<>();

    /**
     *
     * @param devSerials
     * @param params 所有设备的序列号，指令参数Map,设备类型
     * @param deviceType
     * @return
     */
    public IotResult execute(List<String> devSerials , Map params , DeviceType deviceType){
        for(BaseCommandHandler handler : handlers){
          IotResult result =  handler.execute(devSerials ,params , deviceType);
          if(result != null) return result;
        }
         return null;
    }

    public void addHandler(BaseCommandHandler handler){
        this.handlers.add(handler);
    }
}
