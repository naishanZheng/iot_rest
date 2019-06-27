package com.iot.task.dao;

import com.iot.base.dao.IotMapper;
import com.iot.task.pojo.Task;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :定时任务dao
 */
public interface TaskMapper extends IotMapper<Task> {
    List<Task> getAll(Task task);

    void updateStatus(Task task);
}
