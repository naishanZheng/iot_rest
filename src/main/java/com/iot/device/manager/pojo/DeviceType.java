package com.iot.device.manager.pojo;

import com.iot.base.pojo.IotEntity;
import com.iot.base.pojo.PageEntity;
import com.iot.device.config.pojo.CommandConfig;
import com.iot.device.config.pojo.ReportDataConfig;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :设备类型
 */
public class DeviceType extends IotEntity<DeviceType> {
    private String ename;//英文名(实名，与iot平台对应)
    private ReportDataConfig reportDataConfig;//上报数据模板
    private CommandConfig commandConfig;//指令模板
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public ReportDataConfig getReportDataConfig() {
        return reportDataConfig;
    }

    public void setReportDataConfig(ReportDataConfig reportDataConfig) {
        this.reportDataConfig = reportDataConfig;
    }
    public CommandConfig getCommandConfig() {
        return commandConfig;
    }

    public void setCommandConfig(CommandConfig commandConfig) {
        this.commandConfig = commandConfig;
    }
}
