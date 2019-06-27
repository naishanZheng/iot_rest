package com.iot.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhengnaishan
 * @date 2019/3/27 0026
 * @describe :全局配置
 */
@Component
public class Global {
    public static String COOKIE_USER_KEY; //cookie的key
    public static String SSO_BASE_URL; //sso基础地址
    public static String SSO_USER_TOKEN_URI;//获取user地址
    public static String SSO_PAGE_LOGIN_URI;//获取登录地址

    public static String VIEW_BASE_URL;//iot_view服务地址

    public static String UNLOGIN_FORWARD_URI;//用户未登录,系统转发uri
    public static String NOPERMISSION_FORWARD_URI;//没有权限，系统转发uri

    public static String ALL_CORS_URL;//允许跨域的主机地址：即iot_view地址

    public static String REPORT_COUNTS;//上报数据计数key

    public static String IDEMPOTENT_LOCK;//锁前缀
    public static int IDEMPOTENT_LOCK_EXPIRE_TIME;//时间
    public static String HADLOCK_URL;//幂等锁转发地址


    public static String INLINE_COUNT;
    public static int INLINE_COUNT_EXPIRE_TIME;
    public static String OFFLINE_COUNT;
    public static int OFFLINE_COUNT_EXPIRE_TIME;

    @Value("${COOKIE_USER_KEY}")
    public void setCOOKIE_USER_KEY(String COOKIE_USER_KEY) {
        this.COOKIE_USER_KEY = COOKIE_USER_KEY;
    }

    @Value("${SSO_BASE_URL}")
    public void setSSO_BASE_URL(String SSO_BASE_URL) {
        this.SSO_BASE_URL = SSO_BASE_URL;
    }

    @Value("${SSO_USER_TOKEN_URI}")
    public void setSSO_USER_TOKEN_URI(String SSO_USER_TOKEN_URI) {
        this.SSO_USER_TOKEN_URI = SSO_USER_TOKEN_URI;
    }

    @Value("${SSO_PAGE_LOGIN_URI}")
    public void setSSO_PAGE_LOGIN_URI(String SSO_PAGE_LOGIN_URI) {
        this.SSO_PAGE_LOGIN_URI = SSO_PAGE_LOGIN_URI;
    }

    @Value("${UNLOGIN_FORWARD_URI}")
    public  void setUnloginForwardUri(String unloginForwardUri) {
        this.UNLOGIN_FORWARD_URI = unloginForwardUri;
    }

    @Value("${NOPERMISSION_FORWARD_URI}")
    public void setNopermissionForwardUri(String nopermissionForwardUri) {
        this.NOPERMISSION_FORWARD_URI = nopermissionForwardUri;
    }

    @Value("${ALL_CORS_URL}")
    public  void setAllCorsUrl(String allCorsUrl) {
        ALL_CORS_URL = allCorsUrl;
    }

    @Value("${VIEW_BASE_URL}")
    public  void setViewBaseUrl(String viewBaseUrl) {
        VIEW_BASE_URL = viewBaseUrl;
    }

    @Value("${REPORT_COUNTS}")
    public void setReportCounts(String reportCounts) {
        REPORT_COUNTS = reportCounts;
    }

    @Value("${HADLOCK_URL}")
    public void setHadlockUrl(String hadlockUrl) {
        HADLOCK_URL = hadlockUrl;
    }

    @Value("${IDEMPOTENT_LOCK}")
    public void setIdempotentLock(String idempotentLock) {
        IDEMPOTENT_LOCK = idempotentLock;
    }

    @Value("${IDEMPOTENT_LOCK_EXPIRE_TIME}")
    public void setIdempotentLockExpireTime(int idempotentLockExpireTime) {
        IDEMPOTENT_LOCK_EXPIRE_TIME = idempotentLockExpireTime;
    }


    @Value("${INLINE_COUNT}")
    public void setInlineCount(String inlineCount) {
        INLINE_COUNT = inlineCount;
    }

    @Value("${INLINE_COUNT_EXPIRE_TIME}")
    public void setInlineCountExpireTime(int inlineCountExpireTime) {
        INLINE_COUNT_EXPIRE_TIME = inlineCountExpireTime;
    }

    @Value("${OFFLINE_COUNT}")
    public void setOfflineCount(String offlineCount) {
        OFFLINE_COUNT = offlineCount;
    }


    @Value("${OFFLINE_COUNT_EXPIRE_TIME}")
    public void setOfflineCountExpireTime(int offlineCountExpireTime) {
        OFFLINE_COUNT_EXPIRE_TIME = offlineCountExpireTime;
    }
}
