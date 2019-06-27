package com.iot.device.config.dao;

import com.iot.base.dao.BaseMapper;
import com.iot.device.config.pojo.ReportDataTemplet;
import java.util.List;
/**
 * @author zhengnaishan
 * @date 2019/4/3 0003
 * @describe :ReportDataTemplet dao
 */
public interface ReportDataTempletMapper extends BaseMapper<ReportDataTemplet> {
   List<ReportDataTemplet> getByConfigId(String configId);
}
