package com.gtmap.wechat.support.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gtmap.platform.util.StringUtil;
import com.gtmap.platform.util.SysUtils;
import com.gtmap.wechat.support.contant.OttConst;
import com.gtmap.wechat.support.contant.WeChatContants;
import com.gtmap.wechat.support.entity.OttType;
import com.gtmap.wechat.support.entity.WeChatMessage;

/** 
 * 类名称：MessagePlaceholderParser
 * 类描述：微信消息模板填解析器
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class MessagePlaceholderParser {
	//private static final Logger LOGGER = Logger.getLogger(MessagePlaceholderParser.class);
	private static String placeholer = "${var}";
	/**
	 * 解析占位符
	 * @param xml
	 * @param placeHoderMap
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String parse(String xml,Map<String,String> placeHoderMap,HttpServletRequest request){
		//LOGGER.debug(xml);
		if(!StringUtil.isEmpty(xml)){
			WeChatMessage ottMessage = (WeChatMessage) request.getAttribute(WeChatContants.REQUEST_OTTMESSAGE);
			OttType ottType = (OttType) request.getAttribute(OttConst.OTT_TYPE);
			if(ottMessage != null){
				xml = xml.replace("${fromUser}", ottMessage.getToUserName())
						.replace("${toUser}", ottMessage.getFromUserName());
			}
			if(ottType != null){
				xml = xml.replace("${ottType}", ottType.getTypeCode());
			}
			xml = xml.replace("${now}", String.valueOf(System.currentTimeMillis()))
			.replace("${basePath}", SysUtils.getBasePath(request));
			if(placeHoderMap != null && !placeHoderMap.isEmpty()){
				for (Map.Entry entry:placeHoderMap.entrySet()) {
					xml = xml.replace(placeholer.replace("var", String.valueOf(entry.getKey())), String.valueOf(entry.getValue()));
				}
			}
			String [] xmlArray=xml.split("\\\\n");
			if(null == xmlArray)
			{
				return xml;
			}
			StringBuffer resultXml=new StringBuffer("");
			for(int i=0;i<xmlArray.length;i++)
			{
				String xmlStr=xmlArray[i];
				resultXml.append(xmlStr);
				if(i<xmlArray.length-1)
				{
					resultXml.append("\n");
				}
			}
			return resultXml.toString();
			
		}
		return xml;
	}
}
