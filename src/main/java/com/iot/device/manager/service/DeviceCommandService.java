package com.iot.device.manager.service;

import com.iot.base.result.IotResult;
import com.iot.base.service.IotService;
import com.iot.base.utils.HttpClientUtil;
import com.iot.base.utils.IDUtils;
import com.iot.base.utils.JsonUtils;
import com.iot.base.utils.UserUtils;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.config.pojo.CommandTemplet;
import com.iot.device.config.service.CommandConfigService;
import com.iot.device.handler.BaseCommandHandler;
import com.iot.device.handler.CommandHandlerManager;
import com.iot.device.manager.controller.ReportDataController;
import com.iot.device.manager.dao.DeviceCommandMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.DeviceCommand;
import com.iot.device.manager.pojo.DeviceCommand_format;
import com.iot.device.manager.pojo.DeviceType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhengnaishan
 * @date 2019/4/16 0016
 * @describe :
 */
@Service
public class DeviceCommandService extends IotService<DeviceCommandMapper, DeviceCommand> {
    private final static Logger logger = LoggerFactory.getLogger(ReportDataController.class);
    @Autowired
    private CommandConfigService commandConfigService;
    @Autowired
    private CommandHandlerManager commandHandlerManager;
    @Autowired
    private DeviceTypeService deviceTypeService;
    /**
     * 发送指令：0：等待接收，1：发送成功，2：发送失败
     * @param devSerials
     * @param params
     * @return
     */
/*    public IotResult sendCommand(List<String> devSerials , Map params){
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
            logger.info(paramsToString);
            String response = HttpClientUtil.doPostJsonWithHeader("https://www.easy-iot.cn/idev/3rdcap/dev-control/urt-command",
                    JsonUtils.objectToJson(commandMap), headerMap );
            commandResponse = JsonUtils.jsonToPojo(response, Map.class);
            if(!"0".equals(commandResponse.get("optResult"))){
                //响应码不为200
                failure.add(devSerial);
                DeviceCommand deviceCommand = new DeviceCommand(devSerial,paramsToString,2);
                save(deviceCommand);
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
    }*/
    public IotResult sendCommand(List<String> devSerials , Map params){
        DeviceType deviceType = deviceTypeService.getByDevSerial(devSerials.get(0));
        IotResult result = commandHandlerManager.execute(devSerials,params,deviceType);
        return result;
    }
    @Override
    public void save(DeviceCommand deviceCommand) {
        if(StringUtils.isBlank(deviceCommand.getId())){
           deviceCommand.setId(IDUtils.getRandomId());
        }
        deviceCommand.setCreateBy(UserUtils.getUser());
        deviceCommand.setCreateTime(new Date());
        dao.save(deviceCommand);
    }


   public IotResult getCommandFormat(Device device){
        //获取指令信息
       List<DeviceCommand> deviceCommands = dao.getByCount(device);
       //获取指令模板
       CommandConfig commandConfig = commandConfigService.getByDevice(device);
       List<DeviceCommand_format> formats = new ArrayList<>();
       for(DeviceCommand d : deviceCommands){
           DeviceCommand_format format = format(d,commandConfig);
           formats.add(format);
       }
       return IotResult.ok(formats);
   }

   private DeviceCommand_format format(DeviceCommand deviceCommand , CommandConfig commandConfig){
      DeviceCommand_format format = new DeviceCommand_format();
      String params = deviceCommand.getParams();
      Map map = JsonUtils.jsonToPojo(params,Map.class);
      Map genData = (Map)map.get("GenData");
       for(String key :  (Set<String>)genData.keySet()){
           if(!"Cmd".equals(key)){
               DeviceCommand_format.Entity entity = null;
               CommandTemplet templet = commandConfig.getByValue(key);
               if(templet != null){
                   entity = format.new Entity(templet.getKey(),genData.get(key));
               }else{
                   entity = format.new Entity(key,genData.get(key));
               }
               format.addEntity(entity);
           }else{//Cmd数组类型数据
               List<String> cmd = (List<String>)genData.get(key);
               DeviceCommand_format.Entity entity2 = null;
               for(String c : cmd ){
                   CommandTemplet templet2 = commandConfig.getByValue(c);
                   if(templet2 != null){
                       entity2 = format.new Entity(templet2.getKey(),c);
                   }else{
                       entity2 = format.new Entity(c,c);
                   }
                   format.addEntity(entity2);
               }
           }
       }
      format.setCreateBy(deviceCommand.getCreateBy());
      format.setCreateTime(deviceCommand.getCreateTime());
      format.setStatus(deviceCommand.getStatus());
       return format;
   }

}
