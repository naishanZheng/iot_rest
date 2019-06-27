package com.iot.base.controller;

import com.iot.base.dao.IotMapper;
import com.iot.base.pojo.IotEntity;
import com.iot.base.service.IotService;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :业务Controller
 */
public class IotController<S extends IotService<M,T>,M extends IotMapper<T>, T extends IotEntity>
                                    extends PageController<S , M , T>{

}
