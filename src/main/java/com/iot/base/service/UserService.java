package com.iot.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.base.dao.PageMapper;
import com.iot.base.dao.UserMapper;
import com.iot.base.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/9 0009
 * @describe :用户service
 */
@Service
public class UserService extends IotService< UserMapper,User> {

}
