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
 * �����ƣ�WeChatMessageParser
 * ��������΢����Ϣ������ʵ��
 * �����ˣ�byj
 * ����ʱ�䣺2014-12-23
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
 * @version 1.0 
 */
public class WeChatMessageParser<T> {
	/**
	 * ������Ϣ
	 * @param message ��Ϣ�ı�
	 * @return WeChatMessage OTT��Ϣʵ��
	 */
	public static WeChatMessage parse(HttpServletRequest request) throws Exception{
//		if(logger.isDebugEnabled()){
//			logger.debug("��ʼ��������΢�ŷ���������Ϣ��");
//		}
		WeChatMessage ottMessage = null;
		try {
			String message = getMessageContent(request);
			
			if(message != null && message.length() > 0){
				try{
					ottMessage = getOttMessageFromXML(message);
					//logger.debug("��������΢�ŷ���������Ϣ:"+ottMessage);
				}catch (Exception e) {
					return null;
				}
			}
		} catch (Exception e) {
			//logger.debug("��������΢�ŷ���������Ϣ�����쳣��",e);
		}
//		if(logger.isDebugEnabled()){
//			logger.debug("��������΢�ŷ���������Ϣ��ϡ�");
//		}
		return ottMessage;
	}
	
	/**
	 * ��Request�����л�ȡ��Ϣ����
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private static String getMessageContent(HttpServletRequest request) throws IOException{
		InputStream is = request.getInputStream();
		byte b[] = new byte[1024];
		int len = 0;
		int temp = 0; // ���ж�ȡ�����ݶ�ʹ��temp����
		while ((temp = is.read()) != -1) {
			// ��û�ж�ȡ��ʱ��������ȡ
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
		if(MsgType.TXT_MSG.equals(eleMsgType.getText())){//�ı���Ϣ
			
			Element eleContent = (Element) doc.selectSingleNode("/xml/Content");
			weChatMessage.setContent(eleContent.getText());
			
		}else if(MsgType.IMAGE_MSG.equals(eleMsgType.getText())){//ͼƬ��Ϣ
			
			Element elePicUrl = (Element) doc.selectSingleNode("/xml/PicUrl");
			Element eleMediaId = (Element) doc.selectSingleNode("/xml/MediaId");
			weChatMessage.setPicUrl(elePicUrl.getText());
			weChatMessage.setMediaId(eleMediaId.getText());
			
		}else if(MsgType.VOICE_MSG.equals(eleMsgType.getText())){//������Ϣ
			
			Element eleFormat = (Element) doc.selectSingleNode("/xml/Format");
			Element eleMediaId = (Element) doc.selectSingleNode("/xml/MediaId");
			weChatMessage.setFormat(eleFormat.getText());
			weChatMessage.setMediaId(eleMediaId.getText());
			
		}else if(MsgType.VIDEO_MSG.equals(eleMsgType.getText())){//��Ƶ��Ϣ
			
			Element eleThumbMediaId = (Element) doc.selectSingleNode("/xml/ThumbMediaId");
			Element eleMediaId = (Element) doc.selectSingleNode("/xml/MediaId");
			weChatMessage.setThumbMediaId(eleThumbMediaId.getText());
			weChatMessage.setMediaId(eleMediaId.getText());
			
		}else if(MsgType.LOCATION_MSG.equals(eleMsgType.getText())){//λ����Ϣ
			
			Element eleLocationX = (Element) doc.selectSingleNode("/xml/Location_X");
			Element eleLocationY = (Element) doc.selectSingleNode("/xml/Location_Y");
			Element eleScale = (Element) doc.selectSingleNode("/xml/Scale");
			Element eleLabel = (Element) doc.selectSingleNode("/xml/Label");
			weChatMessage.setLocationX(eleLocationX.getText());
			weChatMessage.setLocationY(eleLocationY.getText());
			weChatMessage.setScale(eleScale.getText());
			weChatMessage.setLabel(eleLabel.getText());
			
		}else if(MsgType.EVENT_MSG.equals(eleMsgType.getText())){//�¼�����
			
			Element eleEvent = (Element) doc.selectSingleNode("/xml/Event");
			Element eleEventKey= (Element) doc.selectSingleNode("/xml/EventKey");
			weChatMessage.setEvent(eleEvent.getText());
			weChatMessage.setEventKey(eleEventKey==null?null:eleEventKey.getText());
			
		}
		return weChatMessage;
	}
}
