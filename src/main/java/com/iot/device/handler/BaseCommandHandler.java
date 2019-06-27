package com.iot.device.handler;

import com.iot.base.dao.BaseMapper;
import com.iot.base.pojo.BaseEntity;
import com.iot.base.result.IotResult;
import com.iot.base.service.BaseService;
import com.iot.device.manager.pojo.DeviceCommand;
import com.iot.device.manager.pojo.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/20 0015
 * @describe :指令处理器接口
 */
public abstract class BaseCommandHandler<S extends BaseService , T extends BaseEntity> {
    @Autowired
   private CommandHandlerManager handlerManager;
    @Autowired
   private S service;
    //执行方法
   protected abstract IotResult execute(List<String> devSerials , Map params , DeviceType deviceType);

    /**
     * 初始化
     */
    @PostConstruct
    private void init(){
        handlerManager.addHandler(this);
    }

    /**
     * 保存指令方法
     * @param t
     */
    @Transactional(readOnly = false)
    protected void save(T t){
        service.save(t);
    }

}
