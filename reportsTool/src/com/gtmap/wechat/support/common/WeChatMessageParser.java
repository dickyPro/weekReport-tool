package com.gtmap.wechat.support.common;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.gtmap.wechat.support.entity.MsgType;
import com.gtmap.wechat.support.entity.WeChatMessage;

/** 
 * 类名称：WeChatMessageParser
 * 类描述：微信消息解析器实现
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class WeChatMessageParser<T> {
	/**
	 * 解析消息
	 * @param message 消息文本
	 * @return WeChatMessage OTT消息实体
	 */
	public static WeChatMessage parse(HttpServletRequest request) throws Exception{
//		if(logger.isDebugEnabled()){
//			logger.debug("开始解析来自微信服务器的消息。");
//		}
		WeChatMessage ottMessage = null;
		try {
			String message = getMessageContent(request);
			
			if(message != null && message.length() > 0){
				try{
					ottMessage = getOttMessageFromXML(message);
					//logger.debug("解析来自微信服务器的消息:"+ottMessage);
				}catch (Exception e) {
					return null;
				}
			}
		} catch (Exception e) {
			//logger.debug("解析来自微信服务器的消息发生异常。",e);
		}
//		if(logger.isDebugEnabled()){
//			logger.debug("解析来自微信服务器的消息完毕。");
//		}
		return ottMessage;
	}
	
	/**
	 * 从Request对象中获取消息内容
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private static String getMessageContent(HttpServletRequest request) throws IOException{
		InputStream is = request.getInputStream();
		byte b[] = new byte[1024];
		int len = 0;
		int temp = 0; // 所有读取的内容都使用temp接收
		while ((temp = is.read()) != -1) {
			// 当没有读取完时，继续读取
			b[len] = (byte) temp;
			len++;
		}
		is.close();
		return new String(b, 0, len, "UTF-8");
	}
	
	private static WeChatMessage getOttMessageFromXML(String messageXml) throws DocumentException{
		Document doc = DocumentHelper.parseText(messageXml);
		Element eleToUser = (Element) doc
				.selectSingleNode("/xml/ToUserName");
		Element eleFromUser = (Element) doc
				.selectSingleNode("/xml/FromUserName");
		Element eleMsgType = (Element) doc.selectSingleNode("/xml/MsgType");
		Element eleCreateTime = (Element) doc.selectSingleNode("/xml/CreateTime");
		Element eleMsgId = (Element) doc.selectSingleNode("/xml/MsgId");
		WeChatMessage weChatMessage = new WeChatMessage(eleFromUser.getText(), 
				eleToUser.getText(), Long.parseLong(eleCreateTime.getText()), 
				eleMsgType.getText(), eleMsgId == null?null:eleMsgId.getText());
		weChatMessage.setFormateXml(messageXml);
		if(MsgType.TXT_MSG.equals(eleMsgType.getText())){//文本消息
			
			Element eleContent = (Element) doc.selectSingleNode("/xml/Content");
			weChatMessage.setContent(eleContent.getText());
			
		}else if(MsgType.IMAGE_MSG.equals(eleMsgType.getText())){//图片消息
			
			Element elePicUrl = (Element) doc.selectSingleNode("/xml/PicUrl");
			Element eleMediaId = (Element) doc.selectSingleNode("/xml/MediaId");
			weChatMessage.setPicUrl(elePicUrl.getText());
			weChatMessage.setMediaId(eleMediaId.getText());
			
		}else if(MsgType.VOICE_MSG.equals(eleMsgType.getText())){//语音消息
			
			Element eleFormat = (Element) doc.selectSingleNode("/xml/Format");
			Element eleMediaId = (Element) doc.selectSingleNode("/xml/MediaId");
			weChatMessage.setFormat(eleFormat.getText());
			weChatMessage.setMediaId(eleMediaId.getText());
			
		}else if(MsgType.VIDEO_MSG.equals(eleMsgType.getText())){//视频消息
			
			Element eleThumbMediaId = (Element) doc.selectSingleNode("/xml/ThumbMediaId");
			Element eleMediaId = (Element) doc.selectSingleNode("/xml/MediaId");
			weChatMessage.setThumbMediaId(eleThumbMediaId.getText());
			weChatMessage.setMediaId(eleMediaId.getText());
			
		}else if(MsgType.LOCATION_MSG.equals(eleMsgType.getText())){//位置消息
			
			Element eleLocationX = (Element) doc.selectSingleNode("/xml/Location_X");
			Element eleLocationY = (Element) doc.selectSingleNode("/xml/Location_Y");
			Element eleScale = (Element) doc.selectSingleNode("/xml/Scale");
			Element eleLabel = (Element) doc.selectSingleNode("/xml/Label");
			weChatMessage.setLocationX(eleLocationX.getText());
			weChatMessage.setLocationY(eleLocationY.getText());
			weChatMessage.setScale(eleScale.getText());
			weChatMessage.setLabel(eleLabel.getText());
			
		}else if(MsgType.EVENT_MSG.equals(eleMsgType.getText())){//事件推送
			
			Element eleEvent = (Element) doc.selectSingleNode("/xml/Event");
			Element eleEventKey= (Element) doc.selectSingleNode("/xml/EventKey");
			weChatMessage.setEvent(eleEvent.getText());
			weChatMessage.setEventKey(eleEventKey==null?null:eleEventKey.getText());
			
		}
		return weChatMessage;
	}
}
