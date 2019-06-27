package com.iot.task.tasks;

import com.iot.task.BaseTask;
import org.springframework.stereotype.Component;

/**
 * @author zhengnaishan
 * @date 2019/4/11 0011
 * @describe :
 */
@Component
public class MyTask extends BaseTask {
    @Override
    public void doTask(){
        logger.info("执行doTask方法");
    }
}
