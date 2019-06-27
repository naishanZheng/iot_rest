package com.iot.device.config.controller;

import com.iot.base.controller.PageController;
import com.iot.base.result.IotResult;
import com.iot.device.config.dao.CommandConfigMapper;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.config.pojo.CommandTemplet;
import com.iot.device.config.service.CommandConfigService;
import com.iot.security.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/9 0009
 * @describe :
 */
@Controller
@RequestMapping("/device/commandConfig")
public class CommandConfigController extends PageController<CommandConfigService, CommandConfigMapper,CommandConfig> {

    @Autowired
    private CommandConfigService configService;
    @RequestMapping("/save")
    @Permission("commandConfig:edit")
    @ResponseBody
    public Object save(CommandConfig commandConfig){
      return super.save(commandConfig);
    }

    @RequestMapping("/getByPage")
    @ResponseBody
    public Object getByPage(CommandConfig commandConfig){
        return super.getByPage(commandConfig);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(CommandConfig commandConfig){
        return super.get(commandConfig);
    }

    @RequestMapping("/getTemplets")
    @ResponseBody
    public Object getTemplets(CommandConfig commandConfig){
        try {
            List<CommandTemplet> list = configService.getTemplets(commandConfig);
            return IotResult.ok(list);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission("commandConfig:delete")
    public Object delete(CommandConfig commandConfig){
        return super.delete(commandConfig);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object getAll(){
       return super.getAll();
    }
}
