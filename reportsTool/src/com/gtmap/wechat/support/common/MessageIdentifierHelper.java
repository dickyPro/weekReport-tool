package com.gtmap.wechat.support.common;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.gtmap.platform.contant.BaseContants;
import com.gtmap.platform.util.ContextHelper;
import com.gtmap.platform.util.StringUtil;
import com.gtmap.wechat.support.contant.WeChatContants;

/** 
 * �����ƣ�MessageIdentifier
 * ����������Ϣ��������ݼ�������
 * �����ˣ�byj
 * ����ʱ�䣺2014-11-12 ����11:57:55
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
 * @version 1.0 
 */
public class MessageIdentifierHelper {
	/**
	 * ��ݼ���
	 * @param request
	 * @return
	 */
	public static Boolean identify(HttpServletRequest request){
//		if(logger.isDebugEnabled()){
//			logger.debug("正在验证消息发�?��?�身份�?��?��??");
//		}
		if(null != request && isFromWeChatServer(request)){
			return true;
		}else{
			request.setAttribute(BaseContants.REQ_KEY_ERROR, "http/1.1 401 Unauthorized.");
		}
		return false;
	}
	
	//΢�Žӿ���֤  
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
    
    // ����ת�ַ���
    public static String arrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
  //sha1����   
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
	 * �������ʿͻ��ˣ��ܾ���΢�ſͻ��˷��ʣ�
	 * @param request
	 * @return
	 */
	public static Boolean identifyUserAgent(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		if(null != userAgent && userAgent.indexOf("MicroMessenger") == -1){
			request.setAttribute(BaseContants.REQ_KEY_ERROR, "��ҳ��ֻ����΢������ʾ��");
			return false;
		}
		else
		{
			return true;
		}
	}
}
