package com.gtmap.wechat.support.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.gtmap.wechat.support.contant.OttConst;
import com.gtmap.wechat.support.contant.WeChatContants;
import com.gtmap.wechat.support.entity.EventType;
import com.gtmap.wechat.support.entity.MsgType;
import com.gtmap.wechat.support.entity.OttType;
import com.gtmap.wechat.support.entity.WeChatMessage;
import com.gtmap.wechat.support.entity.WeChatNews;

/** 
 * 类名称：WeChatAccessHandleAdapter
 * 类描述：微信访问业务适配器实现
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class WeChatAccessHandleAdapter {
	private final static Logger LOGGER = Logger.getLogger(WeChatAccessHandleAdapter.class);
	/**
	 * 响应微信应用访问请求
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void handle(WeChatMessage ottMesssage,HttpServletRequest request, HttpServletResponse response) {
//		if(LOGGER.isDebugEnabled()){
//			LOGGER.debug("微信业务适配器接收到业务请求。"+ottMesssage);
//		}
		OttType ottType = (OttType) request.getAttribute(OttConst.OTT_TYPE);
		if(ottMesssage != null){
			request.setAttribute(WeChatContants.REQUEST_OTTMESSAGE, ottMesssage);
			//LOGGER.debug("注入ott对象成功。");
		}
		
		//优先处理事件
		if(MsgType.EVENT_MSG.equalsIgnoreCase(ottMesssage.getMsgType())){
			//新关注用户发送欢迎消息
			if(EventType.SUBCRIBE.equals(ottMesssage.getEvent())){
				try {
					Map params=new HashMap();
					params.put("menuKey", "welcome");
					List<Map<?,?>> messages = MsgTemplateHelper.queryPermissionsMenu(params);
					Map<?,?> msgMap = messages.get(0);
					List<WeChatNews> news = new ArrayList<WeChatNews>();
					WeChatNews weChatNews = new WeChatNews();
					String temp = String.valueOf(msgMap.get("TITLE"));
					temp = temp.equals("null") ?"":temp;
					weChatNews.setTitle(temp);
					temp = String.valueOf(msgMap.get("DESCRIPTION"));
					temp = temp.equals("null") ?"":temp;
					weChatNews.setDescription(temp);
					temp = String.valueOf(msgMap.get("PIC_URL"));
					temp = temp.equals("null") ?"":temp;
					weChatNews.setPicUrl(temp);
					temp = String.valueOf(msgMap.get("LINK_URL"));
					temp = temp.equals("null") ?"":temp;
					weChatNews.setUrl(temp);
					news.add(weChatNews);
					String messageXml = MessagePlaceholderParser.parse(WeChatNewsMessageBuilder.newNewsMessage(ottMesssage.getToUserName(),
							ottMesssage.getFromUserName(), news), null, request);
					
					WeChatMessageResponser.responseMessage(messageXml,response);
				} catch (Exception e) {
					//logger.error("发送欢迎订阅消息发生异常。",e);
				}
			}else if(EventType.UNSUBCRIBE.equals(ottMesssage.getEvent())){
				//取消关注
			}else if(EventType.CLICK.equals(ottMesssage.getEvent())){
				//菜单点击
				try {
//					if(logger.isDebugEnabled()){
//						logger.debug("粉丝访问菜单"+ottMesssage.getEventKey());
//					}
					String menu_key=ottMesssage.getEventKey();
					Map params=new HashMap();
					params.put("menuKey", menu_key);
					List<Map<?,?>> messages = MsgTemplateHelper.queryPermissionsMenu(params);
					List<WeChatNews> news = new ArrayList<WeChatNews>();
					for (int i = 0; i < messages.size(); i++) {
						Map<?,?> msgMap = messages.get(i);
						WeChatNews weChatNews = new WeChatNews();
						String temp = String.valueOf(msgMap.get("TITLE"));
						temp = temp.equals("null") ?"":temp;
						weChatNews.setTitle(temp);
						temp = String.valueOf(msgMap.get("DESCRIPTION"));
						temp = temp.equals("null") ?"":temp;
						weChatNews.setDescription(temp);
						temp = String.valueOf(msgMap.get("PIC_URL"));
						temp = temp.equals("null") ?"":temp;
						weChatNews.setPicUrl(temp);
						temp = String.valueOf(msgMap.get("LINK_URL"));
						temp = temp.equals("null") ?"":temp;
						weChatNews.setUrl(temp);
						news.add(weChatNews);
					}
					String messageXml = MessagePlaceholderParser.parse(WeChatNewsMessageBuilder.newNewsMessage(ottMesssage.getToUserName(),
							ottMesssage.getFromUserName(), news), null, request);
					WeChatMessageResponser.responseMessage(messageXml,response);
				} catch (Exception e) {
					//logger.error("分发菜单业务发生异常。",e);
				}
			}else if(EventType.LOCATION.equals(ottMesssage.getEvent())){
				
			}
		}else{
			//UNDO
		}
	}
}
