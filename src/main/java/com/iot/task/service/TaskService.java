package com.iot.task.service;

import com.iot.base.service.IotService;
import com.iot.task.dao.TaskMapper;
import com.iot.task.pojo.Task;
import org.springframework.stereotype.Service;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :定时任务service
 */
@Service
public class TaskService extends IotService<TaskMapper,Task> {
    public void updataStatus(Task task){
        d.updateStatus(task);
    }
}
