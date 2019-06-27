package com.iot.base.config.service;

import com.iot.base.config.dao.DictMapper;
import com.iot.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/4 0004
 * @describe :
 */
/*@Service*/
public class DictService extends BaseService {
    @Autowired
    private DictMapper dao;
   public Map<String,String> getByType(String type){
      return dao.getByType(type);
   }
}
