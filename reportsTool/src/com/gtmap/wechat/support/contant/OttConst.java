package com.gtmap.wechat.support.contant;

import com.gtmap.wechat.support.entity.OttType;

/** 
 * 类名称：OttConst
 * 类描述：ott应用环境变量
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class OttConst {
	/**
	 * OTT类型 KEY
	 */
	public static final String OTT_TYPE = "OTT_TYPE";
	
	/**
	 * OTT类型 微信
	 */
	public static final OttType OTT_WECHAT = new OttType("01","微信");
	
	/**
	 * OTT类型 新浪微博
	 */
	public static final OttType OTT_SINA = new OttType("02","新浪微博");
	
	/**
	 * OTT类型 陌陌
	 */
	public static final OttType OTT_MOMO = new OttType("03","陌陌");
	
	/**
	 * 首次关注 消息模板key
	 */
	public static final String FIRST_SUBSCRIBE_MSG = "FIRST_SUBSCRIBE_MSG";
	/**
	 * 重新关注 消息模板key
	 */
	public static final String RE_SUBSCRIBE_MSG = "RE_SUBSCRIBE_MSG";
	
	/**
	 * 文本消息
	 */
	public static final String TXT_MSG_TYPE = "01";
	
	/**
	 * 图文消息
	 */
	public static final String NEWS_MSG_TYPE = "02";
}
