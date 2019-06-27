package com.iot.base.controller;

import com.iot.base.dao.PageMapper;
import com.iot.base.pojo.PageEntity;
import com.iot.base.result.IotResult;
import com.iot.base.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :带分页的Controller
 */
public class PageController<S extends PageService<T,M>,M extends PageMapper<T> , T extends PageEntity>
                                                             extends BaseController<S,M,T>{


    protected Object getByPage(T t){
        try{
            t = s.getByPage(t);
            Map result = new HashMap<>();
            result.put("count", t.getPageInfo().getTotal());
            result.put("data", t.getPageInfo().getList());
            return IotResult.ok(result);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }
}
