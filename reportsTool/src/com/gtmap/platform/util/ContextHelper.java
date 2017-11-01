package com.gtmap.platform.util;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.gtmap.platform.contant.BaseContants;

/** 
 * 类名称：ContextHelper
 * 类描述：上下文助手
 * 创建人：byj
 * 创建时间：2014-11-14 下午2:31:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class ContextHelper {
	private static ServletContext servletContext = null;
	
	public static void setContext(ServletContext context){
		servletContext = context;
	}
	
	/**
	 * 获取系统上下文
	 * @return
	 */
	public static ServletContext getContext(){
		if(servletContext == null){
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
			if(null != webApplicationContext)
			{
				servletContext = webApplicationContext.getServletContext();
			}
			else
			{
				return servletContext;
			}
		}
		return servletContext;
	}
	
	/**
	 * 从上下文中的缓存Map中获取数据
	 * @param contextKey
	 * @param cacheKey
	 * @return
	 */
	public static Object getCache(String contextKey,String cacheKey){
		if(null == getContext())
		{
			return null;
		}
		Map<?,?> cacheMap = (Map<?, ?>) getContext().getAttribute(contextKey);
		if(cacheMap != null){
			return cacheMap.get(cacheKey);
		}
		return null;
	}
	
	/**
	 * 从上下文中的缓存Map中更新数据
	 * @param contextKey
	 * @param cacheKey
	 * @return
	 */
	public static void setCache(String contextKey,Map<?,?> value){
		if(null == getContext())
		{
			return;
		}
		getContext().setAttribute(contextKey, value);
	}
	
	/**
	 * 获取上下文中缓存的配置信息
	 * @param key
	 * @param servletContext
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getConfigValue(String key){
		if(null == getContext())
		{
			return null;
		}
		Map<String,String> configMap = (Map<String, String>) getContext().getAttribute(BaseContants.SYS_CONFIG);
		if(configMap != null){
			return configMap.get(key);
		}
		return null;
	}
	
	/**
	 * 设置上下文中缓存的配置信息
	 * @param key
	 * @param servletContext
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static void setConfigValue(String key,String value){
		if(null == getContext())
		{
			return;
		}
		Map<String,String> configMap = (Map<String, String>) getContext().getAttribute(BaseContants.SYS_CONFIG);
		if(configMap != null){
			configMap.put(key,value);
		}
	}
	
	/**
	 * 从上下文获取属性
	 * @param key
	 * @return
	 */
	public static Object getAttribuate(String key){
		if(null == getContext())
		{
			return null;
		}
		return getContext().getAttribute(key);
	}
	
}
