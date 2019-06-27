package com.iot.base.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * @author zhengnaishan
 * @date 2019/3/28 0028
 * @describe :cahce操作工具
 * 为了保证redis 中的数据尽量同步，cache中缓存时间尽量短【
 * */
public class EhCacheUtils {
    private static final String path = "/resource/ehcache.xml";

    private URL url;

    private CacheManager manager;

    private static EhCacheUtils ehCache;

    private EhCacheUtils(String path) {
        url = getClass().getResource(path);
        manager = CacheManager.create(url);
    }

    public static EhCacheUtils getInstance() {
        if (ehCache== null) {
            ehCache= new EhCacheUtils(path);
        }
        return ehCache;
    }

    public void put(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public Object get(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache get(String cacheName) {
        return manager.getCache(cacheName);
    }

    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }
}
