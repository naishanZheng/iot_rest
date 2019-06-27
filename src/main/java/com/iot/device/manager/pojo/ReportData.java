package com.iot.device.manager.pojo;

import com.iot.base.pojo.PageEntity;

/**
 * @author zhengnaishan
 * @date 2019/4/7 0007
 * @describe :
 */
public class ReportData extends PageEntity<ReportData> {
    private String lastMessageTime;
    private String devSerial;
    private String serviceData;

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getDevSerial() {
        return devSerial;
    }

    public void setDevSerial(String devSerial) {
        this.devSerial = devSerial;
    }

    public String getServiceData() {
        return serviceData;
    }

    public void setServiceData(String serviceData) {
        this.serviceData = serviceData;
    }
}
