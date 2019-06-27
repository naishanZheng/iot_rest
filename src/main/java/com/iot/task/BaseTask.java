package com.iot.task;

import com.iot.base.jedis.JedisClient;
import com.iot.base.utils.IDUtils;
import com.iot.task.pojo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :定时器任务的基础接口
 * 定时任务类需要继承此类，实现doTask方法
 */
public abstract class BaseTask implements Runnable{
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TaskManager taskManager;
    @Value("${TASK_REDIS_KEY}")
    private String TASK_REDIS_KEY;
    protected final static Logger logger = LoggerFactory.getLogger(TaskManager.class);
    private Task task;
    protected abstract void doTask();//执行任务接口

    /**
     * 校验方法是否已经执行过了
     * @return
     */
    public boolean check(){//
       String key = jedisClient.get(TASK_REDIS_KEY+":"+task.getId());
       if(key != null){//任务在执行状态
           //校验key是否相同
           if(key.equals(task.getKey())){//相同，继续执行定时任务
               task.setKey(IDUtils.getRandomId());//更新key
               //更新数据
               jedisClient.set(TASK_REDIS_KEY+":"+task.getId(),task.getKey());
               return true;
           }else{
               System.out.println("方法已经被其它服务器执行过，不再执行");
               task.setKey(key);//更新key
               return false;//禁止继续执行定时任务
           }
       }else{
           //停止定时任务
           taskManager.doStop(task);
           return false;
       }
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        if(this.check()){//执行了
            this.doTask();
        }else {//跳过，下次执行
            return;
        }
    }
}
