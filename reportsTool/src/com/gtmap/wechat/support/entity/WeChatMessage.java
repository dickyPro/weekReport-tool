package com.gtmap.wechat.support.entity;

import java.io.Serializable;

/** 
 * 类名称：WeChatMessage
 * 类描述：微信富消息实体
 * 创建人：byj
 * 创建时间：2014-12-22
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class WeChatMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fromUserName;
	private String toUserName;
	private Long createTime;
	private String msgType;
	private String msgId;
	private String formateXml;
	private String picUrl;
	private String mediaId;
	private String format;
	private String thumbMediaId;
	private String locationX;
	private String locationY;
	private String scale;
	private String label;
	private String title;
	private String description;
	private String url;
	private String content;
	private String event;
	private String eventKey;
	
	public WeChatMessage() {
		super();
	}
	
	public WeChatMessage(String fromUserName, String toUserName, Long createTime,
		String msgType,String msgId) {
		super();
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.msgId = msgId;
	}

	/**
	 * fromUserName
	 *
	 * @return  the fromUserName
	 */
	
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * toUserName
	 *
	 * @return  the toUserName
	 */
	
	public String getToUserName() {
		return toUserName;
	}

	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * createTime
	 *
	 * @return  the createTime
	 */
	
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * msgType
	 *
	 * @return  the msgType
	 */
	
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * msgId
	 *
	 * @return  the msgId
	 */
	
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * formateXml
	 *
	 * @return  the formateXml
	 */
	
	public String getFormateXml() {
		return formateXml;
	}

	/**
	 * @param formateXml the formateXml to set
	 */
	public void setFormateXml(String formateXml) {
		this.formateXml = formateXml;
	}

	/**
	 * picUrl
	 *
	 * @return  the picUrl
	 */
	
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * mediaId
	 *
	 * @return  the mediaId
	 */
	
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * format
	 *
	 * @return  the format
	 */
	
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * thumbMediaId
	 *
	 * @return  the thumbMediaId
	 */
	
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	/**
	 * locationX
	 *
	 * @return  the locationX
	 */
	
	public String getLocationX() {
		return locationX;
	}

	/**
	 * @param locationX the locationX to set
	 */
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	/**
	 * locationY
	 *
	 * @return  the locationY
	 */
	
	public String getLocationY() {
		return locationY;
	}

	/**
	 * @param locationY the locationY to set
	 */
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	/**
	 * scale
	 *
	 * @return  the scale
	 */
	
	public String getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * label
	 *
	 * @return  the label
	 */
	
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * title
	 *
	 * @return  the title
	 */
	
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * description
	 *
	 * @return  the description
	 */
	
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * url
	 *
	 * @return  the url
	 */
	
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * content
	 *
	 * @return  the content
	 */
	
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * event
	 *
	 * @return  the event
	 */
	
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * eventKey
	 *
	 * @return  the eventKey
	 */
	
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
