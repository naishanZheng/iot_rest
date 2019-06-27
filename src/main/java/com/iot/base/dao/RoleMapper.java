package com.iot.base.dao;

import com.iot.base.pojo.Role;
import com.iot.base.pojo.User;
import com.iot.base.pojo.User_Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/8 0004
 * @describe :
 */
public interface RoleMapper extends PageMapper<Role> {
   public List<Role> getAllRole();
   public List<User_Role> getAllRoleByUser(@Param("user") User user);
}
