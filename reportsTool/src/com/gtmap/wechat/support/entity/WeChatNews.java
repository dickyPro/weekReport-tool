package com.gtmap.wechat.support.entity;

/** 
 * 类名称：WeChatNews
 * 类描述：微信富文本消息条目
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class WeChatNews {
	private String title;
	private String description;
	private String PicUrl;
	private String url;

	public WeChatNews() {
		super();
	}

	public WeChatNews(String title, String description, String picUrl,
			String url) {
		super();
		this.title = title;
		this.description = description;
		PicUrl = picUrl;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
