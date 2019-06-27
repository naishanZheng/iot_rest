package com.iot.device.config.service;

import com.iot.base.cache.EhCacheUtils;
import com.iot.base.service.PageService;
import com.iot.device.config.dao.CommandConfigMapper;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.config.pojo.CommandTemplet;
import com.iot.device.config.pojo.ReportDataConfig;
import com.iot.device.config.pojo.ReportDataTemplet;
import com.iot.device.manager.pojo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/9 0009
 * @describe :指令配置service
 */
@Service
public class CommandConfigService extends PageService<CommandConfig, CommandConfigMapper> {

    @Autowired
    private CommandTempletService commandTempletService;

    @Override
    @Transactional(readOnly = false)
    public void save(CommandConfig commandConfig){
        super.save(commandConfig);
        //移除原有的templet
        List<CommandTemplet> pastTemplets = commandTempletService.getByConfig(commandConfig);//旧模板
        List<CommandTemplet> nowTemplets = commandConfig.getCommandTemplets();//最新的模板
        for(CommandTemplet p : pastTemplets){//移除旧模板
            for(int i=0;i< nowTemplets.size();i++ ){
                if(p.getId().equals(nowTemplets.get(i).getId())){//原有的模板还存在
                    break;
                }
                if(i == nowTemplets.size()-1){//已经遍历完所有的最新模板，旧模板在前端删除了
                    commandTempletService.delete(p);
                }
            }
        }

        for (CommandTemplet t : commandConfig.getCommandTemplets()){
           t.setCommandConfigId(commandConfig.getId());
           commandTempletService.save(t);
        }
    }

    public List<CommandTemplet> getTemplets(CommandConfig commandConfig){
        return commandTempletService.getByConfig(commandConfig);
    }


    @Override
    public void delete(CommandConfig commandConfig){
        List<CommandTemplet> templets = commandTempletService.getByConfig(commandConfig);
        for (CommandTemplet templet : templets){
            commandTempletService.delete(templet);
        }
        super.delete(commandConfig);
    }

    public CommandConfig getByDevice(Device device){
        CommandConfig commandConfig = dao.getByDevice(device);
        List<CommandTemplet> templets = null;
        //从cache中获取
        try {
            templets = (List<CommandTemplet>)EhCacheUtils.getInstance().get("commandTemplet" , commandConfig.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        if(templets == null){
            templets = commandTempletService.getByConfig(commandConfig);
            EhCacheUtils.getInstance().put("commandTemplet",commandConfig.getId(),templets);
        }
        commandConfig.setCommandTemplets(templets);
        return commandConfig;
    }
}
