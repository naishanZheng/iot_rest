package com.iot.base.controller;

import com.iot.base.config.Global;
import com.iot.base.pojo.Menu;
import com.iot.base.pojo.User;
import com.iot.base.result.IotResult;
import com.iot.base.utils.UserUtils;
import com.iot.security.Permission;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/3/25 0025
 * @describe :
 */
@Controller
@RequestMapping("/base")
public class PermissionController {

    @RequestMapping("/noPermission")
    @ResponseBody
    public Object noPermission(){
        return IotResult.build(401 , "用户没有权限");
    }

    @RequestMapping("/unLogin")
    @ResponseBody
    public Object unLogin(){
        System.out.println(Global.SSO_BASE_URL+Global.SSO_PAGE_LOGIN_URI);
        return IotResult.build(400 , "用户未登录", Global.SSO_BASE_URL+Global.SSO_PAGE_LOGIN_URI);
    }


    @RequestMapping("/hadlock")
    @ResponseBody
    public Object hadlock(){
        return IotResult.build(405 , "重复访问", null);
    }


    @RequestMapping("/getUser")
    @ResponseBody
    @Permission("hadLogin")
    public Object getUser(HttpServletRequest request){
          User user = UserUtils.getUser(request);
          if(user == null){
              return IotResult.build(400,"用户未登录",Global.SSO_BASE_URL+Global.SSO_PAGE_LOGIN_URI);
          }else {
              return IotResult.ok(user);
          }
    }

    @RequestMapping(value ="/getMenus",produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    @Permission("hadLogin")
    public Object getMenus(HttpServletRequest request ,@RequestParam(value = "callback",required = false) String callback){
      User user = UserUtils.getUser(request);
        //String result = JsonUtils.objectToJson(IotResult.ok(getMenus(user)));
        //String result = JsonUtils.objectToJson(test());
        Object result =   IotResult.ok(getMenus(user));
        //判断是否为jsonp调用
        if(callback==null||"".equals(callback)){
            return result;
        }else{
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }


    public List<Map> getMenus(User user){
      List<Menu> menus = user.getMenus();
      List<Menu> m2 = getNodeByParent(menus , "-1");
      Menu first = m2.get(0);//第一个节点
      Map firstNode = new HashMap<>();
      loop(getNodeByParent(menus,first.getId()),firstNode ,first,menus);
      return (List<Map>)firstNode.get("children");//不需要第一个节点
    }

    private void loop(List<Menu> child , Map parentNode , Menu parent , List<Menu> all){
        List<Map> childs = new ArrayList<Map>();
        parentNode.put("title",parent.getName());
        parentNode.put("icon",parent.getIcon());
        parentNode.put( "href",Global.VIEW_BASE_URL+parent.getHref());
        for(Menu m : child){
            Map m2 = new HashMap<>();
            childs.add(m2);
            loop(getNodeByParent(all , m.getId()),m2 , m ,all);
        }
        if(child.size() > 0){
            parentNode.put("children" , childs);
            parentNode.put("spread",true);
        }

    }


    public List<Menu> getNodeByParent(List<Menu> menus , String parentId){
        List<Menu> m = new ArrayList<Menu>();
        for(int i=0;i<menus.size();i++){
            if(!menus.get(i).getIsShow()){//列表不可见
               menus.remove(i);//移除不可见的列表
               i--;
               continue;
            }
            if(parentId.equals(menus.get(i).getParentId())){
               m.add(menus.get(i));
            }
        }
        return m;
    }
}
