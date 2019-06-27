package com.iot.security;

import com.iot.base.annotation.Idempotent;
import com.iot.base.config.Global;
import com.iot.base.jedis.JedisClient;
import com.iot.base.jedis.JedisClientSingle;
import com.iot.base.utils.SpringContextHolder;
import com.iot.base.utils.UserUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.iot.base.pojo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/3/24 0024
 * @describe :权限过滤器
 */
@Aspect
@Component
public class PermissionFilter {
    @Autowired
    private JedisClient jedisClient;

    @Pointcut("@annotation(com.iot.security.Permission)") //Authority是自定义的标签
    public void authority(){
    }


    @Pointcut("@annotation(com.iot.base.annotation.Idempotent)") //幂等拦截
    public void idempotent(){
    }


    @Around("idempotent()")
    public Object doIdempotentFilter(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response =  requestAttributes.getResponse();
        Object result = null;
        Idempotent idempotent = null;
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        //获取方法上的注解
        idempotent = method.getAnnotation(Idempotent.class);
        //获取注解值，方法id,方法id可以是一类id，例如所有save方法都共用一个id
        String methodId = idempotent.value();
        User user = UserUtils.getUser();
        //设置锁格式：锁前缀:用户id:方法id(注解值)  ,  值随便设置
        long in = jedisClient.setnx(Global.IDEMPOTENT_LOCK+":"+user.getId()+":"+methodId,methodId);
        if(in == 0){//锁存在，不执行方法
            //转发到有锁地址
            request.getRequestDispatcher(Global.HADLOCK_URL).forward(request, response);
        }else{//锁不存在，执行方法
            result = joinPoint.proceed();
        }
        //不管有没有锁，都设置时间，防止攻击
        jedisClient.expire(Global.IDEMPOTENT_LOCK+":"+user.getId()+":"+methodId,Global.IDEMPOTENT_LOCK_EXPIRE_TIME);
        return result;
    }


    @Around("authority()")
    public Object doPermissionFilter(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object result = null;
        Permission permission = null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response =  requestAttributes.getResponse();
        User user = UserUtils.getUser(request);
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        //获取方法上的注解
        permission = method.getAnnotation(Permission.class);
        String value = permission.value();
        if(user != null){//用户已经登录
            if(checkPermission(user , value)){//有权限，放行
                result = joinPoint.proceed();
            }else{//没有权限
               /* Type doResult = permission.type();
                result = doResult.doReturn(UserStatus.NOPERMISSION);*/
               // request.getRequestDispatcher("/base/test2").forward(request, response);
                request.getRequestDispatcher(Global.NOPERMISSION_FORWARD_URI).forward(request, response);
               // throw new NoPermissionException();
            }
        }else{
             /* Type doResult = permission.type();
               result = doResult.doReturn(UserStatus.UNLOGIN);*/
             request.getRequestDispatcher(Global.UNLOGIN_FORWARD_URI).forward(request, response);
             //throw new UnLoginException();
        }
        return result;
    }


    /**
     * 校验权限
     * @param user
     * @param permission 方法上面的权限标识
     * @return
     */
    private Boolean checkPermission(User user , String permission){
        List<String> permissions = user.getPermissions();
        if(permissions == null || permissions.size() == 0){
            return false;
        }
        return permissions.contains(permission);
    }

    public PermissionFilter(){
        System.out.println("PermissionFilter》》》》》》》");
    }
}
