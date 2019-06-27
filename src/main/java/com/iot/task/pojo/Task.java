package com.iot.task.pojo;

import com.iot.base.pojo.IotEntity;
import com.iot.base.pojo.PageEntity;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :定时任务类
 */
public class Task extends IotEntity<Task> {
    private String ObjectName;//执行的类名
    private String taskConfig;//任务配置
    private String status;//状态
    private String key;//钥匙，保证任务不能执行多次

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    public String getTaskConfig() {
        return taskConfig;
    }

    public void setTaskConfig(String taskConfig) {
        this.taskConfig = taskConfig;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
