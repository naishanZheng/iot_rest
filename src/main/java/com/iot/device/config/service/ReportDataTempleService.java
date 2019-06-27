package com.iot.device.config.service;

import com.iot.base.service.BaseService;
import com.iot.device.config.dao.ReportDataTempletMapper;
import com.iot.device.config.pojo.ReportDataConfig;
import com.iot.device.config.pojo.ReportDataTemplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/3 0003
 * @describe :
 */
@Service
public class ReportDataTempleService extends BaseService<ReportDataTemplet, ReportDataTempletMapper> {
    @Autowired
    private ReportDataTempletMapper dao;

   public List<ReportDataTemplet> getByConfig(ReportDataConfig reportDataConfig){
       return dao.getByConfigId(reportDataConfig.getId());
   }
}
