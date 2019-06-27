package com.iot.device.config.service;

import com.iot.base.service.PageService;
import com.iot.device.config.dao.ReportDataConfigMapper;
import com.iot.device.config.pojo.ReportDataConfig;
import com.iot.device.config.pojo.ReportDataTemplet;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.manager.service.DeviceManagerService;
import com.iot.device.manager.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/3 0003
 * @describe :
 */
@Service
public class ReportDataConfigService extends PageService<ReportDataConfig, ReportDataConfigMapper> {
    @Autowired
    private ReportDataConfigMapper dao;
    @Autowired
    private ReportDataTempleService templetService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private DeviceManagerService deviceManagerService;
    @Transactional(readOnly = false)
    public void save(ReportDataConfig reportDataConfig){
        super.save(reportDataConfig);
        //保存reportDataConfig
        List<ReportDataTemplet> firstNodes = getFirstTemplet(reportDataConfig.getTemplets());
        for(ReportDataTemplet t : firstNodes){
            t.setReportDataConfigId(reportDataConfig.getId());
            t.setParentId("-1");//根节点为-1
            templetService.save(t);
            saveTemplet(t,reportDataConfig.getTemplets());
        }
    }


    public void saveTemplet(ReportDataTemplet parent , List<ReportDataTemplet> templets){
         for(ReportDataTemplet t : templets){
             if(parent != null && parent.getIndex().equals(t.getParentIndex())
                                               && (!"10".equals(t.getType()))){//不为serviceData类型数据
                 //设置t的父id
                 t.setParentId(parent.getId());
                 t.setReportDataConfigId(parent.getReportDataConfigId());
                 templetService.save(t);
                 saveTemplet(t,templets);
             }
         }
    }

    public List getFirstTemplet(List<ReportDataTemplet> templets){
        List<ReportDataTemplet> parents = new ArrayList<>();
        for(ReportDataTemplet t : templets){
            if("10".equals(t.getType())){//serviceData类型数据
               parents.add(t);
            }
        }
        return parents;
    }

    public List<ReportDataTemplet> getTemplets(ReportDataConfig reportDataConfig){
       return templetService.getByConfig(reportDataConfig);
    }

    @Transactional
    public void delete(ReportDataConfig reportDataConfig){
        super.delete(reportDataConfig);
        //删除对应的templets
        List<ReportDataTemplet> templets = templetService.getByConfig(reportDataConfig);
        for (ReportDataTemplet t : templets){
            templetService.delete(t);
        }
    }

    //获取模板给前端页面
    public List<ReportDataTemplet> getReportDataTemplet(Device device){
      device = deviceManagerService.get(device);
      DeviceType deviceType = deviceTypeService.get(device.getDeviceType());
      ReportDataConfig config = this.get(deviceType.getReportDataConfig());
      List<ReportDataTemplet> templets = templetService.getByConfig(config);
      return templets;
    }
}
