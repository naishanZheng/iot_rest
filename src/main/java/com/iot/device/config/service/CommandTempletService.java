package com.iot.device.config.service;

import com.iot.base.dao.BaseMapper;
import com.iot.base.service.BaseService;
import com.iot.device.config.dao.CommandTempletMapper;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.config.pojo.CommandTemplet;
import com.iot.device.config.pojo.ReportDataConfig;
import com.iot.device.config.pojo.ReportDataTemplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/9 0009
 * @describe :
 */
@Service
public class CommandTempletService extends BaseService<CommandTemplet, CommandTempletMapper> {
    @Autowired
    private CommandTempletMapper templetMapper;
    public List<CommandTemplet> getByConfig(CommandConfig commandConfig){
        return templetMapper.getByConfigId(commandConfig.getId());
    }
}
