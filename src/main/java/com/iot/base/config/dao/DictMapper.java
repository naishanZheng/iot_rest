package com.iot.base.config.dao;

import com.iot.base.dao.BaseMapper;

import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/4 0004
 * @describe :
 */
public interface DictMapper extends BaseMapper {
    Map getByType(String type);
}
