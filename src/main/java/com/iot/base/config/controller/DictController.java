package com.iot.base.config.controller;

import com.iot.base.config.service.DictService;
import com.iot.base.result.IotResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/4 0004
 * @describe :数据字典controller
 */
public class DictController {
    @Autowired
    private DictService dictService;

    @RequestMapping("/getByType")
    @ResponseBody
    public Object getByType(String type){
        try{
            Map dict = dictService.getByType(type);
            return IotResult.ok(dict);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }
}
