package com.gtmap.platform.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gtmap.platform.config.SysConfig;
import com.gtmap.platform.contant.BaseContants;

/** 
 * 类名称：SystemInitListener
 * 类描述：系统初始化监听器
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 * @param <T>
 */
@Component
public class SystemInitListener<T> implements ServletContextListener {

	//private Logger logger = Logger.getLogger(SystemInitListener.class);
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
//		if(logger.isInfoEnabled()){
//			logger.info("正在启动系统初始化监听器...");
//		}
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		loadConfig(servletContext);//加载初始化配置
	}

	
	@SuppressWarnings("unchecked")
	private void loadConfig(ServletContext servletContext){
//		if(logger.isInfoEnabled()){
//			logger.info("正在加载系统配置...");
//		}
		ApplicationContext application=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		Map<String,String> tmpMap = new HashMap<String, String>();
		tmpMap.put("WECHAT_TOKEN", SysConfig.getConfig("WECHAT_TOKEN"));
		servletContext.setAttribute(BaseContants.SYS_CONFIG, tmpMap);
//		if(logger.isInfoEnabled()){
//			logger.info("系统配置信息加载成功！");
//		}
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
//		if(logger.isInfoEnabled()){
//			logger.info("正在关闭系统初始化监听器...");
//		}
	}

}
