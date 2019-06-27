package com.iot.base.controller;

import com.iot.base.pojo.Role;
import com.iot.base.pojo.Role_Menu;
import com.iot.base.result.IotResult;
import com.iot.base.service.MenuService;
import com.iot.base.service.RoleService;
import com.iot.security.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/3/8 0008
 * @describe :角色Controller
 */
@Controller
@RequestMapping("/base/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/getAllRoles")
    @ResponseBody
    @Permission("roles:view")
    public Object getAllRoles( @RequestParam(value="page") int currentPage ,
                            @RequestParam(value="limit") int pageSize ){
         //PageInfo<Role> pageInfo= roleService.getAllRoles(currentPage , pageSize);
        try {
            Role role = new Role();
            role.setPage(currentPage);
            role.setLimit(pageSize);
            role = roleService.getByPage(role);
            Map result = new HashMap<>();
            result.put("count" ,role.getPageInfo().getTotal());
            result.put("data" , role.getPageInfo().getList());
            return IotResult.ok(result);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }

    @RequestMapping("/getRole")
    @ResponseBody
    @Permission("role:view")
    public Object getRole(String id ){
        try {
            Role role = roleService.get(id);
            return  IotResult.ok(role);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
    }


   @RequestMapping("/getAllMenus")
   @ResponseBody
   public Object getAllMenus(Role role){
        try{
            List<Role_Menu> menus = menuService.getMenusByRole(role);
            return IotResult.ok(menus);
        }catch (Exception e){
            return IotResult.build(500,"系统出现异常");
        }
   }

   @RequestMapping("/save")
   @ResponseBody
   @Permission("role:edit")
   public Object saveMenus(@RequestBody Role role){
        try{
            roleService.save(role);
            return IotResult.ok(role.getId());
        }catch (Exception e){
           return IotResult.build(500,"系统出现异常");
        }
   }

   @RequestMapping("/delete")
   @ResponseBody
   @Permission("role:delete")
   public Object delete(Role role){
        try {
            roleService.delete(role);
            return IotResult.ok();
        }catch (Exception e){
           return IotResult.build(500 , "系统出现异常");
        }
   }
}
