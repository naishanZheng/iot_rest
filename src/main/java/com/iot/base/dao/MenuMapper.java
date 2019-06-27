package com.iot.base.dao;

import com.iot.base.pojo.Menu;
import com.iot.base.pojo.Role;
import com.iot.base.pojo.Role_Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/4 0004
 * @describe :
 */
public interface MenuMapper extends BaseMapper<Menu> {
   List<Menu> getAllEnums();

   List<Role_Menu> getByParentId(@Param("parentId") String parentId, @Param("role") Role role);

   List<Menu> getByParent(String parentId);
}
