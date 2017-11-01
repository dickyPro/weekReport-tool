package com.gtmap.wechat.support.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gtmap.platform.config.SysConfig;
import com.gtmap.platform.util.HttpRequestUtil;
import com.gtmap.platform.util.HttpResponseEntity;
import com.gtmap.platform.util.JSONUtils;

public class AccessTokenService {
	/**
	 * 获取访问令牌（有效时间7200秒）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getToken(){
//		if(LOGGER.isDebugEnabled()){
//			LOGGER.debug("正在获取access_token。。。");
//		}
		HttpRequestUtil helper = new HttpRequestUtil();
		try {
			HttpResponseEntity httpResponseEntity = helper.sendGet(SysConfig.getConfig("ACCESS_TOKEN_SERVICE")
					.replace("CORPID", SysConfig.getConfig("CORPID")).replace("CORPSECRET", SysConfig.getConfig("CORPSECRET")));
			Map<String,String> map = JSONUtils.fromJSON(httpResponseEntity.getContent(), HashMap.class);
//			if(LOGGER.isDebugEnabled()){
//				LOGGER.debug("获取access_token成功！");
//				LOGGER.debug(map);
//			}
			return map.get("access_token");
		} catch (IOException e) {
			//LOGGER.error("获取access_token发生异常。",e);
		}
		return null;
	}
	
	/**
	 * 获取JSTicket（有效时间7200秒）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getJSTicket(String access_token){
//		if(LOGGER.isDebugEnabled()){
//			LOGGER.debug("正在获取access_token。。。");
//		}
		String url=SysConfig.getConfig("GET_JSAPI_TICKET")+access_token;
		HttpRequestUtil helper = new HttpRequestUtil();
		try {
			HttpResponseEntity httpResponseEntity = helper.sendGet(url);
			Map<String,String> map = JSONUtils.fromJSON(httpResponseEntity.getContent(), HashMap.class);
//			if(LOGGER.isDebugEnabled()){
//				LOGGER.debug("获取access_token成功！");
//				LOGGER.debug(map);
//			}
			return map.get("ticket");
		} catch (IOException e) {
			//LOGGER.error("获取access_token发生异常。",e);
		}
		return null;
	}
}
