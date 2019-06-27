package com.iot.base.dao;

import com.iot.base.pojo.PageEntity;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/2 0005
 * @describe :分页查询接口
 */
public interface PageMapper<D extends PageEntity> extends BaseMapper<D>{
    List<D> getByPage(D d);//分页查询
}
