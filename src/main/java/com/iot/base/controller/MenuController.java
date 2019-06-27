package com.iot.base.controller;

import com.iot.base.dao.MenuMapper;
import com.iot.base.pojo.Menu;
import com.iot.base.result.IotResult;
import com.iot.base.service.MenuService;
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
 * @date 2019/3/4 0004
 * @describe :菜单
 */
@RequestMapping("/base/menu")
@Controller
public class MenuController extends BaseController<MenuService, MenuMapper,Menu>{

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getAllMenus")
    @ResponseBody
    @Permission("menus:view")
    public Object getAllMenus(){
        try{
            List<Menu> menus = menuService.getAllMenus();
            return IotResult.ok(menus);
        }catch (Exception e){
            return IotResult.build(500,"出现异常");
        }
    }


    @RequestMapping("/getMenu")
    @ResponseBody
    @Permission("menu:view")
    public Object getMenu(Menu menu){
       return super.get(menu);
    }

    @RequestMapping("/saveMenu")
    @ResponseBody
    @Permission("menu:edit")
    public Object save(@RequestBody Menu menu){
      return super.save(menu);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permission("menu:delete")
    public Object delete(Menu menu){
       return super.delete(menu);
    }
}
