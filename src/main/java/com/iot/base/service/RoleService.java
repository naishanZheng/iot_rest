package com.iot.base.service;

import com.iot.base.dao.RoleMapper;
import com.iot.base.dao.UserMapper;
import com.iot.base.pojo.Role;
import com.iot.base.pojo.User;
import com.iot.base.pojo.User_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/8 0008
 * @describe :roleService
 */
@Service
public class RoleService extends PageService<Role, RoleMapper> {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    public List<User_Role> getAllRoleByUser(User user){
        user = userMapper.get(user);
        return roleMapper.getAllRoleByUser(user);
    }
}
