package com.iot.base.intercpetor;

import com.iot.base.config.Global;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengnaishan
 * @date 2019/3/27 0027
 * @describe :基础拦截器（功能不再使用）
 */
public class BaseIntercpetor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
       // request.setAttribute("restBaseUrl" , Global.REST_BASE_URL);//存放rest的基础地址
        response.setHeader("Access-Control-Allow-Origin", Global.ALL_CORS_URL);
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "1728000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, content-type, Accept");
        return true;
    }
    //执行方法之后执行
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }
    //最终执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
