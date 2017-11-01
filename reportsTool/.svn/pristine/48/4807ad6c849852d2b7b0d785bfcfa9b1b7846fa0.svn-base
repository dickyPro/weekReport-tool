package com.gtmap.wechat.support.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import com.gtmap.wechat.support.entity.WeChatMessage;

/** 
 * 类名称：WeChatMessageResponser
 * 类描述：微信消息回复器
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class WeChatMessageResponser {
	//private static Logger logger = Logger.getLogger(BaseAction.class);
	
	/**
	 * 回复来自微信服务器的消息
	 * @param message
	 * @param response
	 */
	public static void responseMessage(WeChatMessage message, HttpServletResponse response)
			throws Exception {
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(message.getFormateXml());
//			if(logger.isDebugEnabled()){
//				logger.debug("发送消息：" + message.getFormateXml());
//			}
		} catch (UnsupportedEncodingException e) {
			//logger.error("设置字符集发生异常。",e);
			throw new Exception("不被支持的字符集。");
		} catch (IOException e) {
			//logger.error("发送消息发生异常。",e);
			throw new Exception("IO异常，");
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	
	/**
	 * 回复来自微信服务器的消息
	 * @param message
	 * @param response
	 */
	public static void responseMessage(String message, HttpServletResponse response)
			throws Exception {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().write(message);
			response.getWriter().flush();
//			if(logger.isDebugEnabled()){
//				logger.debug("发送消息：\n" + message);
//			}
		} catch (UnsupportedEncodingException e) {
			//logger.error("设置字符集发生异常。",e);
			throw new Exception("不被支持的字符集。");
		} catch (IOException e) {
			//logger.error("发送消息发生异常。",e);
			throw new Exception("IO异常，");
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
}
