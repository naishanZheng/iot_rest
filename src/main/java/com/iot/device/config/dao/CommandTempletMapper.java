package com.iot.device.config.dao;

import com.iot.base.dao.BaseMapper;
import com.iot.base.pojo.BaseEntity;
import com.iot.device.config.pojo.CommandTemplet;
import com.iot.device.config.pojo.ReportDataTemplet;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/9 0009
 * @describe :
 */
public interface CommandTempletMapper extends BaseMapper<CommandTemplet> {
    List<CommandTemplet> getByConfigId(String configId);
}
