package com.gtmap.wechat.support.common;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.gtmap.platform.contant.BaseContants;
import com.gtmap.platform.util.ContextHelper;
import com.gtmap.platform.util.StringUtil;
import com.gtmap.wechat.support.contant.WeChatContants;

/** 
 * 类名称：MessageIdentifier
 * 类描述：消息发送者身份鉴定助手
 * 创建人：byj
 * 创建时间：2014-11-12 上午11:57:55
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class MessageIdentifierHelper {
	/**
	 * 身份鉴定
	 * @param request
	 * @return
	 */
	public static Boolean identify(HttpServletRequest request){
//		if(logger.isDebugEnabled()){
//			logger.debug("姝ｅ湪楠岃瘉娑堟伅鍙戦?佽?呰韩浠姐?傘?傘??");
//		}
		if(null != request && isFromWeChatServer(request)){
			return true;
		}else{
			request.setAttribute(BaseContants.REQ_KEY_ERROR, "http/1.1 401 Unauthorized.");
		}
		return false;
	}
	
	//微信接口验证  
    public static boolean isFromWeChatServer(HttpServletRequest request) {
    	try {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			if (StringUtil.isEmpty(signature) && StringUtil.isEmpty(timestamp)
					&& StringUtil.isEmpty(nonce)) {
				return false;
			}
			String token = ContextHelper.getConfigValue(WeChatContants.WECHAT_TOKEN);
			String[] tmpArr = { token, timestamp, nonce };
			String tmpStr = "";
			if (null != tmpArr && tmpArr.length > 0) {
				Arrays.sort(tmpArr);
				tmpStr = arrayToString(tmpArr);
				tmpStr = SHA1Encode(tmpStr);
			}
			if (tmpStr.equalsIgnoreCase(signature)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}  
    
    // 数组转字符串
    public static String arrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
  //sha1加密   
    public static String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");  
           resultString = byte2hexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  
    public static final String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    }  
	
	/**
	 * 鉴定访问客户端（拒绝非微信客户端访问）
	 * @param request
	 * @return
	 */
	public static Boolean identifyUserAgent(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		if(null != userAgent && userAgent.indexOf("MicroMessenger") == -1){
			request.setAttribute(BaseContants.REQ_KEY_ERROR, "该页面只能在微信中显示。");
			return false;
		}
		else
		{
			return true;
		}
	}
}
