package com.iot.device.config.pojo;

import com.iot.base.pojo.PageEntity;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/8 0008
 * @describe :指令模板配置
 */
public class CommandConfig extends PageEntity<CommandConfig> {
    private List<CommandTemplet> commandTemplets;

    public List<CommandTemplet> getCommandTemplets() {
        return commandTemplets;
    }

    public void setCommandTemplets(List<CommandTemplet> commandTemplets) {
        this.commandTemplets = commandTemplets;
    }

    /**
     * 根据value获取Templet，例：根据name获取value为name的Templet
     * @param value
     * @return
     */
    public CommandTemplet getByValue(String value){
        if(value == null){return null;}
        for(CommandTemplet templet : this.commandTemplets){
            if(value.equals(templet.getValue())){
                return templet;
            }
        }
        return null;
    }
}
