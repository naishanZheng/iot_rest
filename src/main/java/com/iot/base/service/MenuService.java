package com.iot.base.service;

import com.iot.base.dao.MenuMapper;
import com.iot.base.dao.RoleMapper;
import com.iot.base.pojo.Menu;
import com.iot.base.pojo.Role;
import com.iot.base.pojo.Role_Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/4 0004
 * @describe :
 */
@Service
public class MenuService extends BaseService<Menu, MenuMapper> {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    public List<Menu> getAllMenus(){
        return menuMapper.getAllEnums();
    }


    public List<Role_Menu> getMenusByRole(Role role){
        List result = new LinkedList<Role_Menu>();
            role = roleMapper.get(role);//查询role的数据，为了获得menuIds
            Role_Menu parentNode =  menuMapper.getByParentId("-1" , role).get(0);//获取根节点
            result.add(parentNode);
            getMenusByParentNodeANDRole(parentNode , role);
        return result;
    }


    private void getMenusByParentNodeANDRole(Role_Menu thisNode , Role role){
        List<Role_Menu> menus =  menuMapper.getByParentId(thisNode.getValue() , role);//获取父节点
        thisNode.setData(menus);
        for (Role_Menu r : menus){
            getMenusByParentNodeANDRole(r , role);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Menu menu){
        //先找出所有的子标签
        List<Menu> children = new ArrayList<>();
        this.getByParentId(menu.getId(),children);
        for (Menu m : children){
            super.delete(m);
        }
        super.delete(menu);
    }

    public void getByParentId(String id , List<Menu> all){
        List<Menu> menus =  menuMapper.getByParent(id);
        if(menus != null && menus.size()>0){
            all.addAll(menus);
            for(Menu m : menus){
              this.getByParentId(m.getId(),all);
            }
        }
    }

}
