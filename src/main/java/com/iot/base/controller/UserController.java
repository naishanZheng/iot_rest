package com.iot.base.controller;

import com.iot.base.annotation.Idempotent;
import com.iot.base.dao.UserMapper;
import com.iot.base.pojo.User;
import com.iot.base.pojo.User_Role;
import com.iot.base.result.IotResult;
import com.iot.base.service.RoleService;
import com.iot.base.service.UserService;
import com.iot.base.utils.IDUtils;
import com.iot.security.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * @author zhengnaishan
 * @date 2019/3/9
 * @describe :用户Controller
 */
@Controller
@RequestMapping("/base/user")
public class UserController extends IotController<UserService, UserMapper,User>{


    @Autowired
    private RoleService roleService;

    @RequestMapping("/getUser")
    @ResponseBody
    @Permission("user:view")
    public Object getUser(User user){
       return super.get(user);
    }

    @RequestMapping("/getAllRole")
    @ResponseBody
    public Object getAllRole(User user){
        List<User_Role> result = roleService.getAllRoleByUser(user);
        return IotResult.ok(result);
    }

    @RequestMapping("/getAllUsers")
    @ResponseBody
    @Permission("user:view")
    public Object getAllUsers(User user) {
        return super.getByPage(user);
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    @Permission("user:edit")
    @Idempotent("save")
    public Object save(@RequestBody User user){
        return super.save(user);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @Permission("user:delete")
    public Object delete(User user){
        return super.delete(user);
    }

    /**
     * 上传用户头像
     * id 用户Id
     */
    @ResponseBody
    @RequestMapping(value = "/uploadUserHead", method = RequestMethod.POST)
    public Object apiUploadUserHeadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 请求map
            if (file == null) {
                return null;
            }
            // 上传文件大小 //第一步：获取服务器的实际路径
            String realPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println(realPath);
            //需要上传的路径，我的路径根据用户的和当前日期划分路径
            String resourcePath = "static/images/userHead";
            //文件名
            //String name = imageFile.getOriginalFilename();
            //获取时间的路径
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            resourcePath += "/" + year + "/" + month + "/" + day + "/";
            File dir = new File(realPath + resourcePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //先把用户上传到原图保存到服务器上
            Date date = new Date();
            String id = IDUtils.getRandomId();
            File file2 = new File(dir,id  + ".jpg");
            file.transferTo(file2);
            // 上传文件成功(realPath+resourcePath);
            if(file2.exists()){
              Map result = new HashMap<>();
              result.put("code" , 0);
              result.put("msg","success");
              result.put("data",resourcePath+id+".jpg");
              return result;
            }
        } catch (Exception e) {
            Map result = new HashMap<>();
            result.put("code" , 500);
            result.put("msg","success");
            result.put("data" , e.toString());
            e.printStackTrace();
            return result;
        }
        return  null;
    }
}