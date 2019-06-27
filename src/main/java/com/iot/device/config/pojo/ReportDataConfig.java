package com.iot.device.config.pojo;

import com.iot.base.pojo.PageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/3 0003
 * @describe :设备接收回调的数据模板
 */
public class ReportDataConfig extends PageEntity<ReportDataConfig> {
    private String name;
    private List<ReportDataTemplet> templets ;

    public List<ReportDataTemplet> getTemplets() {
        return templets;
    }

    public void setTemplets(List<ReportDataTemplet> templets) {
        this.templets = templets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
