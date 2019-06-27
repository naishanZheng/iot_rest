package com.iot.device.reportForm.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author zhengnaishan
 * @date 2019/4/15 0015
 * @describe :上报数据计数
 */
public class ReportCount {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date reportTime;//上报时间
    private int counts;//上报个数

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public ReportCount(Date reportTime , int counts){
        this.counts = counts;
        this.reportTime = reportTime;
    }
}
