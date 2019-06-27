package com.iot.base.controller;

import com.iot.base.annotation.Idempotent;
import com.iot.base.dao.BaseMapper;
import com.iot.base.pojo.BaseEntity;
import com.iot.base.result.IotResult;
import com.iot.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :CRUD Controller
 */
public class BaseController<S extends BaseService<T,M>, M extends BaseMapper<T> ,T extends BaseEntity> {
    @Autowired
    protected S s;

    protected Object get(T t){
        try {
            return IotResult.ok(s.get(t));
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }
    }

    protected Object delete(T t){
        try{
            s.delete(t);
            return IotResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }
    }


    protected Object save(T t){
        try {
            s.save(t);
            return IotResult.ok(t);
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }
    }

    public Object getAll(){
        try{
            List<T> list = s.getAll();
            return IotResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return IotResult.build(500,"系统出现异常");
        }
    }
}
