package com.iot.base.dao;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/5 0005
 * @describe :基础的接口
 */
public interface BaseMapper <T>{
    T get(T t);

    T get(String id);

    void save(T t);

    void update(T t);

    void delete(T t);

    List<T> getByPage(T t);//分页查询

    List<T> getAll();//获取所有
}
