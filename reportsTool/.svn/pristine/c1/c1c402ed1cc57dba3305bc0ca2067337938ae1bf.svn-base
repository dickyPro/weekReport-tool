package com.gtmap.platform.util;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/** 
 * 类名称：HttpResponseUtil
 * 类描述：Http消息返回工具
 */
public class HttpResponseUtil {
	
	public static String charsetEncoding = "UTF-8";
	
	public static void response(HttpServletResponse response,String CONTENT_TYPE,
			String outstring)throws Exception{
		if(CONTENT_TYPE!=null){
			String encding=CONTENT_TYPE.split("=").length>1?CONTENT_TYPE
					.split("=")[1]:charsetEncoding;
			response.setContentType(CONTENT_TYPE);
			OutputStream out=response.getOutputStream();
			if(null==out || null==outstring){
				return;
			}
			out.write(outstring.getBytes(encding));
			out.flush();
			out.close();
		}
	}
}
