package com.iot.device.manager.service;
import com.iot.base.service.PageService;
import com.iot.base.utils.JsonUtils;
import com.iot.device.config.pojo.ReportDataConfig;
import com.iot.device.config.pojo.ReportDataTemplet;
import com.iot.device.config.service.ReportDataConfigService;
import com.iot.device.config.service.ReportDataTempleService;
import com.iot.device.manager.dao.ReportDataMapper;
import com.iot.device.manager.pojo.Device;
import com.iot.device.manager.pojo.DeviceType;
import com.iot.device.manager.pojo.ReportData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/7 0007
 * @describe :
 */
@Service
public class ReportDataService extends PageService<ReportData , ReportDataMapper> {
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private ReportDataConfigService reportDataConfigService;
    @Autowired
    private ReportDataTempleService reportDataTempleService;
    @Autowired
    private DeviceManagerService deviceManagerService;

    /**
     * 根据上报数据模板获取上报数据
     * @param device
     * @return
     */
    public Map getByDevice(Device device){
        ReportData reportData = new ReportData();
        reportData.setPage(device.getPage());
        reportData.setLimit(device.getLimit());
        device = deviceManagerService.get(device);
        reportData.setDevSerial(device.getDevSerial());
        DeviceType deviceType = deviceTypeService.get(device.getDeviceType());
        ReportDataConfig config = reportDataConfigService.get(deviceType.getReportDataConfig());
        List<ReportDataTemplet> templets = reportDataTempleService.getByConfig(config);
        reportData = super.getByPage(reportData);
        List<ReportData> list =reportData.getPageInfo().getList();
        List<Map> result = new ArrayList<>();
        for(ReportData r : list){
           List<Map> datas = JsonUtils.jsonToPojo(r.getServiceData() , List.class);
           Map result2 = new HashMap();
           result2.put("lastMessageTime",r.getLastMessageTime());
           for(Map m : datas){//变历一条数据的多个serviceData
               String serviceId =(String)m.get("serviceId");
               Map serviceData = (Map)m.get("serviceData");
               List<ReportDataTemplet> templets2 = getByServiceId(serviceId ,templets);
               if(templets2.size() > 0){//serviceData类型
                 for(ReportDataTemplet t : templets2){
                     List<ReportDataTemplet> templets3 = getByParent(t,templets);
                     for (ReportDataTemplet t2 : templets3){
                         result2.put(t2.getV(),serviceData.get(t2.getV()));
                     }
                 }
               }
           }
           result.add(result2);
        }
        Map result3 = new HashMap<>();
        result3.put("data",result);
        result3.put("count",reportData.getPageInfo().getTotal());
        return result3;
    }

    /**
     * 更据lastMessageTime删除上报数据
     */
    @Transactional(readOnly = false)
    public void deleteReportDataBeforeLastMessageTime(ReportData reportData){
        dao.deleteReportDataBeforeLastMessageTime(reportData);
    }

    public List<ReportDataTemplet> getByServiceId(String serviceId , List<ReportDataTemplet> templets){
        List result =  new ArrayList<ReportDataTemplet>();
        for(ReportDataTemplet t : templets){
            if("10".equals(t.getType()) && t.getV().equals(serviceId)){
                result.add(t);
            }
        }
        return result;
    }

    public List<ReportDataTemplet> getByParent(ReportDataTemplet parent , List<ReportDataTemplet> all){
         List<ReportDataTemplet> result = new ArrayList<>();
        for(ReportDataTemplet t : all){
            if(t.getParentId().equals(parent.getId())){
                result.add(t);
            }
        }
        return result;
    }
}
