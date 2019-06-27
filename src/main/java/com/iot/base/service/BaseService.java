package com.iot.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.base.dao.BaseMapper;
import com.iot.base.pojo.BaseEntity;
import com.iot.base.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/8 0008
 * @describe :基础的service类
 */
public abstract class BaseService < T extends BaseEntity, D extends BaseMapper<T>>{
    @Autowired
    protected D dao;
    public T get(String id){
        return dao.get(id);
    }

    public T get(T t){
       return dao.get(t);
    }

    @Transactional(readOnly = false)
    public void save(T t){
        if(StringUtils.isBlank(t.getId())){
            t.setId(IDUtils.getRandomId());//先设置id
            dao.save(t);
        }else{
            dao.update(t);
        }
    }

    @Transactional(readOnly = false)
    public void delete(T t){
        dao.delete(t);
    }

    public List<T> getAll(){return dao.getAll();}
}
