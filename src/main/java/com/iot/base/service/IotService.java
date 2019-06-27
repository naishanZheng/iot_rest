package com.iot.base.service;

import com.iot.base.dao.IotMapper;
import com.iot.base.pojo.IotEntity;
import com.iot.base.pojo.User;
import com.iot.base.utils.IDUtils;
import com.iot.base.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zhengnaishan
 * @date 2019/4/6 0006
 * @describe :与业务相关的service类，封装用户信息操作
 */
public class IotService<M extends IotMapper<T>,T extends IotEntity> extends PageService<T,M>{

    @Override
    @Transactional(readOnly = false)
    public void save(T t) {
        User user = UserUtils.getUser();
        Date time = new Date();
        if(StringUtils.isBlank(t.getId())){//新增
            t.setCreateBy(user);
            t.setCreateTime(time);
            t.setUpdateBy(user);
            t.setUpdateTime(time);
        }else{
            t.setUpdateBy(user);
            t.setUpdateTime(time);
        }
        super.save(t);
    }
}
