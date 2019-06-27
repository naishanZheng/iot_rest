package com.iot.device.config.pojo;

import com.iot.base.pojo.BaseEntity;

/**
 * @author zhengnaishan
 * @date 2019/4/8 0008
 * @describe :
 */
public class CommandTemplet extends BaseEntity {
    private String key;
    private String value;
    private String type;//类型
    private String defaultValue;//默认值
    private String commandConfigId;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCommandConfigId() {
        return commandConfigId;
    }

    public void setCommandConfigId(String commandConfigId) {
        this.commandConfigId = commandConfigId;
    }
}
