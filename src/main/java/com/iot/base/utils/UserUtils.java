package com.iot.base.utils;

import com.iot.base.cache.EhCacheUtils;
import com.iot.base.config.Global;
import com.iot.base.pojo.User;
import com.iot.base.result.IotResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengnaishan
 * @date 2019/3/28 0028
 * @describe :获取用户工具类
 */
public class UserUtils {

    public static User getUser(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = CookieUtils.getCookieValue(request , Global.COOKIE_USER_KEY);
        if(null == token || "".equals(token)){
            return null;
        }
        if(getUserByCache(token) != null){
            return getUserByCache(token);
        }
        return getUserBySSO(token);
    }


    public static User getUser(HttpServletRequest request){
        String token = CookieUtils.getCookieValue(request , Global.COOKIE_USER_KEY);
        if(null == token || "".equals(token)){
            return null;
        }
        if(getUserByCache(token) != null){
            return getUserByCache(token);
        }
        return getUserBySSO(token);
    }

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    private static User getUserByCache(String token){
        return (User)EhCacheUtils.getInstance().get("userCache" , token);
    }

    /**
     * @param token
     * 获取用户信息，调用远程服务
     */
    private static User getUserBySSO(String token){
        String json = HttpClientUtil.doPost(Global.SSO_BASE_URL+Global.SSO_USER_TOKEN_URI+token);//获取User
        //把json转换成IotResult
        IotResult result = IotResult.formatToPojo(json , User.class);
        if(result.getStatus() == 200){
            User user = (User) result.getData();
            //将用户存放到EhCache中
            EhCacheUtils.getInstance().put("userCache",token,user);
            return user;
        }
        return null;
    }
}
