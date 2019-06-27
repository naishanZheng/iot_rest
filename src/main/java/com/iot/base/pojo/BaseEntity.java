package com.iot.base.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/3/5 0005
 * @describe :基础类
 */
public abstract class BaseEntity implements Serializable {
    protected  String id; //
    protected  String name;//
    protected String remarks;	// 备注
    protected Map<String, String> sqlMap = new HashMap<>();
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
