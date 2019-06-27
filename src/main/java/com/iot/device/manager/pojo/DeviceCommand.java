package com.iot.device.manager.pojo;

import com.iot.base.pojo.IotEntity;

/**
 * @author zhengnaishan
 * @date 2019/4/16 0016
 * @describe :指令pojo
 */
public class DeviceCommand extends IotEntity<DeviceCommand> {
    private String params;//参数
    private String devSerial;//设备序列号
    private int status;//状态0：等待接收，1：发送成功，2：发送失败
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDevSerial() {
        return devSerial;
    }

    public void setDevSerial(String devSerial) {
        this.devSerial = devSerial;
    }

    public DeviceCommand(String devSerial , String params , int status){
        this.devSerial = devSerial;
        this.params = params;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DeviceCommand(){

    }
}
