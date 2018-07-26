package com.bpg.lr.config;





import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @Title:EhcacheUtil
 * @Description: 缓存工具类	
 * @Author hjp	
 * @Date 2018年6月20日
 */
public class EhcacheUtil {
	
	private static final String path = EhcacheUtil.class.getClassLoader().getResource("config/ehcache-shiro.xml").getPath(); 
	  
    private CacheManager manager;  
    
  
    private static EhcacheUtil ehCache;  
  
    private EhcacheUtil(String path) {  
        manager = CacheManager.create(path);  
    }  
  
    public static EhcacheUtil getInstance() {  
        if (ehCache== null) {  
            ehCache= new EhcacheUtil(path);  
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
