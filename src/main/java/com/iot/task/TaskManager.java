package com.iot.task;

import com.iot.base.jedis.JedisClient;
import com.iot.base.utils.IDUtils;
import com.iot.task.dao.TaskMapper;
import com.iot.task.pojo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :任务管理配置类
 */

@Component
public class TaskManager {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JedisClient jedisClient;
    @Value("${TASK_REDIS_KEY}")
    private String TASK_REDIS_KEY;
    @Autowired
    private TaskMapper taskMapper;
    private final static Logger logger = LoggerFactory.getLogger(TaskManager.class);
    Map<String,ScheduledFuture> futures = new HashMap<String,ScheduledFuture>();



    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();

    }

    public void doStop(Task task){
        if(task != null){
            ScheduledFuture future = futures.get(task.getId());
            if(future != null){
                future.cancel(true);
                futures.remove(task.getId());
                logger.info("停止定时任务");
            }
            jedisClient.del(TASK_REDIS_KEY+":"+task.getId());
        }
    }

    /**
     * 启动定时任务
     * @param task
     */
    public void doCreate(Task task){
        if(null != futures.get(task.getId())){//任务已经启动
            logger.info("任务已经启动了，不需要重启");
            return;
        }
        String key = jedisClient.get(TASK_REDIS_KEY+":"+task.getId());
        if (key == null){//任务没有发布上去
            task.setKey(IDUtils.getRandomId());
            //将taskKey发布到redis中
            jedisClient.set(TASK_REDIS_KEY+":"+task.getId(),task.getKey());
        }else {
            //任务已经发布到redis
            //更新task的key
            task.setKey(key);
        }
        BaseTask baseTask = (BaseTask)applicationContext.getBean(task.getObjectName());
        if(baseTask == null){
            logger.error("创建执行类出现异常");
        }
        //设置定时任务对象
        baseTask.setTask(task);
        ScheduledFuture future = threadPoolTaskScheduler.schedule(baseTask, new CronTrigger(task.getTaskConfig()));
        futures.put(task.getId(),future);
        logger.info("启动定时任务");
    }


    @PostConstruct
    public void init() throws Exception{
        Task t = new Task();
        t.setStatus("1");
        List<Task> tasks = taskMapper.getAll(t);//获取在线的任务
        for(Task task : tasks){//执行定时任务
            String key = jedisClient.get(TASK_REDIS_KEY+":"+task.getId());
            if (key == null){//任务没有发布上去
                task.setKey(IDUtils.getRandomId());
                //将taskKey发布到redis中
                jedisClient.set(TASK_REDIS_KEY+":"+task.getId(),task.getKey());
                this.doCreate(task);
            }else {
                //任务已经发布到redis
                //更新task的key
                task.setKey(key);
                this.doCreate(task);
            }
        }
    }
}
