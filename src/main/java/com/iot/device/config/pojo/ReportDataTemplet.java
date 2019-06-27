package com.iot.device.config.pojo;

import com.iot.base.pojo.BaseEntity;

/**
 * @author zhengnaishan
 * @date 2019/4/3 0003
 * @describe :数据模板
 * 保存前通过index确定父子关系，保存后通过parentId确定父子关系
 * form时默认使index=id
 */
public class ReportDataTemplet extends BaseEntity {
    private String k;//例：名称
    private String v;//例：name
    private String type;//类型：数据模板
    private String parentId;//父节点id
    private String index;//index
    private String parentIndex;//父index
    private String reportDataConfigId;
    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(String parentIndex) {
        this.parentIndex = parentIndex;
    }

    public String getReportDataConfigId() {
        return reportDataConfigId;
    }

    public void setReportDataConfigId(String reportDataConfigId) {
        this.reportDataConfigId = reportDataConfigId;
    }
}
