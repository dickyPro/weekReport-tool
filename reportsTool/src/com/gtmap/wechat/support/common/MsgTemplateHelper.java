package com.gtmap.wechat.support.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.gtmap.platform.util.ContextHelper;
import com.gtmap.platform.util.XmlReader;
import com.gtmap.wechat.support.contant.WeChatContants;


/** 
 * 类名称：MsgTemplateHelper
 * 类描述：微信消息模板助手
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class MsgTemplateHelper {
	//private static final Logger LOGGER = Logger.getLogger(MsgTemplateHelper.class);
	/**
	 * 从消息模板中创建消息
	 * @param key 模板key
	 * @param request request对象，从该对象中获取OttMessage
	 * @param placeHoderMap 占位符扩展
	 * @return String 微信消息XML
	 */
	public static String getMessageFromTemplate(String key,HttpServletRequest request,Map<String,String> placeHoderMap){
		Map<?,?> templates = (Map<?, ?>) ContextHelper.getContext().getAttribute(WeChatContants.WECHAT_MSG_TEMPLATE);
		String template = String.valueOf(templates.get(key));
		return MessagePlaceholderParser.parse(template,placeHoderMap, request);
	}
	
	/**
	 * 从消息模板中创建消息
	 * @param key 模板key
	 * @param request request对象，从该对象中获取OttMessage
	 * @return String 微信消息XML
	 */
	public static String getMessageFromTemplate(String key,HttpServletRequest request){
		//LOGGER.debug(key);
		if(null == ContextHelper.getContext() || null == request)
		{
			//LOGGER.error("没有获取到上下文。");
			return null;
		}
		Map<?,?> templates = (Map<?, ?>) ContextHelper.getContext().getAttribute(WeChatContants.WECHAT_MSG_TEMPLATE);
		if( null == templates)
		{
			//LOGGER.error("没有获取到消息模板缓存。");
			return null;
		}
		String template = String.valueOf(templates.get(key));
		//LOGGER.debug(template);
		return MessagePlaceholderParser.parse(template,null, request);
	}
	
	/**
     * 加载图文模板
     * @param params
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
	public static List queryPermissionsMenu(Map params) throws Exception {
		// TODO Auto-generated method stub
		String findKey=(String)params.get("menuKey");
		List result=new ArrayList();
		List list=new ArrayList();
		Document document = XmlReader.getParseXmlDocument("/messTemplate.xml");
		org.dom4j.Document doc = org.dom4j.DocumentHelper.parseText(document.asXML());
		List templateList = doc.selectNodes("//messtemplates/template");
		List messageList=null;
	    for (Object obj:templateList)
        {
            Element userEl = (Element)obj;
			Node node_key = userEl.selectSingleNode("key");
			Node node_messages = userEl.selectSingleNode("messages");
            if(node_key.getText()!=null && node_key.getText().equals(findKey)){
            	messageList=node_messages.selectNodes("message");
            }
        }
	    if(messageList!=null){
    	  for (Object obj:messageList)
          {
    		  Element userEl = (Element)obj;
    		  Node nodeTitle = userEl.selectSingleNode("TITLE");
    		  Node nodeDescription = userEl.selectSingleNode("DESCRIPTION");
    		  Node nodePicurl = userEl.selectSingleNode("PIC_URL");
    		  Node nodeLinkurl = userEl.selectSingleNode("LINK_URL");
              Map map=new HashMap();
              map.put("TITLE", nodeTitle.getText());
              map.put("DESCRIPTION", nodeDescription.getText());
              map.put("PIC_URL", nodePicurl.getText());
              map.put("LINK_URL", nodeLinkurl.getText());
              result.add(map);
          }
	    }
		return result;
	}
}
