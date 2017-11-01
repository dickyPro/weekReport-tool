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
 * �����ƣ�MsgTemplateHelper
 * ��������΢����Ϣģ������
 * �����ˣ�byj
 * ����ʱ�䣺2014-12-23
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
 * @version 1.0 
 */
public class MsgTemplateHelper {
	//private static final Logger LOGGER = Logger.getLogger(MsgTemplateHelper.class);
	/**
	 * ����Ϣģ���д�����Ϣ
	 * @param key ģ��key
	 * @param request request���󣬴Ӹö����л�ȡOttMessage
	 * @param placeHoderMap ռλ����չ
	 * @return String ΢����ϢXML
	 */
	public static String getMessageFromTemplate(String key,HttpServletRequest request,Map<String,String> placeHoderMap){
		Map<?,?> templates = (Map<?, ?>) ContextHelper.getContext().getAttribute(WeChatContants.WECHAT_MSG_TEMPLATE);
		String template = String.valueOf(templates.get(key));
		return MessagePlaceholderParser.parse(template,placeHoderMap, request);
	}
	
	/**
	 * ����Ϣģ���д�����Ϣ
	 * @param key ģ��key
	 * @param request request���󣬴Ӹö����л�ȡOttMessage
	 * @return String ΢����ϢXML
	 */
	public static String getMessageFromTemplate(String key,HttpServletRequest request){
		//LOGGER.debug(key);
		if(null == ContextHelper.getContext() || null == request)
		{
			//LOGGER.error("û�л�ȡ�������ġ�");
			return null;
		}
		Map<?,?> templates = (Map<?, ?>) ContextHelper.getContext().getAttribute(WeChatContants.WECHAT_MSG_TEMPLATE);
		if( null == templates)
		{
			//LOGGER.error("û�л�ȡ����Ϣģ�建�档");
			return null;
		}
		String template = String.valueOf(templates.get(key));
		//LOGGER.debug(template);
		return MessagePlaceholderParser.parse(template,null, request);
	}
	
	/**
     * ����ͼ��ģ��
     * @param params
     * @return
     * @throws Exception
     * @see [�ࡢ��#��������#��Ա]
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
