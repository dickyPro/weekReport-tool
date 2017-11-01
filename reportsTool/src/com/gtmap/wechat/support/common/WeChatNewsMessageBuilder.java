package com.gtmap.wechat.support.common;

import java.util.List;

import com.gtmap.wechat.support.entity.WeChatNews;


/** 
 * 类名称：WeChatNewsMessageBuilder
 * 类描述：微信富文本消息构建器
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class WeChatNewsMessageBuilder {
	/**
	 * 创建多图文消息
	 * @param fromUserName
	 * @param toUserName
	 * @param newz
	 * @return
	 */
	public static String newNewsMessage(final String fromUserName,
			final String toUserName, List<WeChatNews> newz) {
        final int count = newz.size();
        final StringBuilder out = new StringBuilder();
        out.append("<xml>");
        out.append("<ToUserName><![CDATA[").append(toUserName).append("]]></ToUserName>");
        out.append("<FromUserName><![CDATA[").append(fromUserName).append("]]></FromUserName>");
        out.append("<CreateTime>").append(System.currentTimeMillis()).append("</CreateTime>");
        out.append("<MsgType><![CDATA[news]]></MsgType>");
        out.append("<ArticleCount>").append(count).append("</ArticleCount>");
        out.append("<Articles>");

        for (int i = 0; i < count; i++) {
			final WeChatNews news = newz.get(i);
			if (null != news) {
				out.append("<item>");
				out.append("<Title><![CDATA[").append(news.getTitle()).append("]]></Title>");
				out.append("<Description><![CDATA[").append(news.getDescription()).append("]]></Description>");
				out.append("<PicUrl><![CDATA[").append(news.getPicUrl()).append("]]></PicUrl>");
				out.append("<Url><![CDATA[").append(news.getUrl()).append("]]></Url>");
				out.append("</item>");
			}
		}
        out.append("</Articles>");
        out.append("<FuncFlag>1</FuncFlag>");
        out.append("</xml>");
        return out.toString();
	}
}
