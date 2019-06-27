package com.iot.device.reportForm.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author zhengnaishan
 * @date 2019/4/26 0026
 * @describe :在线离线设备统计
 */
public class ReportInLineCount {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;//上报时间
    private int inLineCount;//在线个数
    private int offLineCount;//离线个数

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getInLineCount() {
        return inLineCount;
    }

    public void setInLineCount(int inLineCount) {
        this.inLineCount = inLineCount;
    }

    public int getOffLineCount() {
        return offLineCount;
    }

    public void setOffLineCount(int offLineCount) {
        this.offLineCount = offLineCount;
    }

    public ReportInLineCount(Date time , int inLineCount , int offLineCount){
        this.time = time;
        this.inLineCount = inLineCount;
        this.offLineCount = offLineCount;
    }

    public ReportInLineCount(){

    }
}
