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
 * �����ƣ�SystemInitListener
 * ��������ϵͳ��ʼ��������
 * �����ˣ�byj
 * ����ʱ�䣺2014-12-23
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
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
//			logger.info("��������ϵͳ��ʼ��������...");
//		}
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		loadConfig(servletContext);//���س�ʼ������
	}

	
	@SuppressWarnings("unchecked")
	private void loadConfig(ServletContext servletContext){
//		if(logger.isInfoEnabled()){
//			logger.info("���ڼ���ϵͳ����...");
//		}
		ApplicationContext application=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		Map<String,String> tmpMap = new HashMap<String, String>();
		tmpMap.put("WECHAT_TOKEN", SysConfig.getConfig("WECHAT_TOKEN"));
		servletContext.setAttribute(BaseContants.SYS_CONFIG, tmpMap);
//		if(logger.isInfoEnabled()){
//			logger.info("ϵͳ������Ϣ���سɹ���");
//		}
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
//		if(logger.isInfoEnabled()){
//			logger.info("���ڹر�ϵͳ��ʼ��������...");
//		}
	}

}
