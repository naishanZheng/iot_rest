package com.iot.task.controller;

import com.iot.base.controller.IotController;
import com.iot.base.result.IotResult;
import com.iot.security.Permission;
import com.iot.task.TaskManager;
import com.iot.task.dao.TaskMapper;
import com.iot.task.pojo.Task;
import com.iot.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :
 */
@Controller
@RequestMapping("/iot/task")
public class TaskController extends IotController<TaskService, TaskMapper, Task> {
    @Autowired
    private TaskManager taskManager;
    @Autowired
    private TaskService taskService;
    @RequestMapping("/getByPage")
    @ResponseBody
    @Permission("task:list")
    public Object getByPage(Task task){
        return super.getByPage(task);
    }


    @RequestMapping("/save")
    @ResponseBody
    @Permission("task:edit")
    public Object save(Task task){
        return super.save(task);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(Task task){
        return super.get(task);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission("task:delete")
    public Object delete(Task task){
        return super.delete(task);
    }

    /**
     * 启动定时任务
     * @return
     */
    @RequestMapping("/startTask")
    @ResponseBody
    @Permission("task:startTask")
    public Object startTask(Task task){
        try {
            //
            task = taskService.get(task);
            task.setStatus("1");
            taskService.updataStatus(task);
            taskManager.doCreate(task);
            return IotResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }
    }

    /**
     * 停止定时任务
     * @param task
     * @return
     */
    @RequestMapping("/stopTask")
    @ResponseBody
    @Permission("task:stopTask")
    public Object stopTask(Task task){
        try {
            task = taskService.get(task);
            task.setStatus("0");
            taskService.updataStatus(task);
            taskManager.doStop(task);
            return IotResult.ok();
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }
}
