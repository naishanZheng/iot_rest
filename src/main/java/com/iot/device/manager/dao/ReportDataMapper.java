package com.iot.device.manager.dao;

import com.iot.base.dao.PageMapper;
import com.iot.device.manager.pojo.ReportData;

/**
 * @author zhengnaishan
 * @date 2019/4/7 0007
 * @describe :
 */
public interface ReportDataMapper extends PageMapper<ReportData> {
    //根据lastMessageTime之前的上报数据
    void deleteReportDataBeforeLastMessageTime(ReportData reportData);
}
