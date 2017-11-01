package com.gtmap.platform.filter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gtmap.platform.contant.BaseContants;
import com.gtmap.platform.util.HttpResponseUtil;
import com.gtmap.wechat.support.common.MessageIdentifierHelper;
import com.gtmap.wechat.support.common.WeChatAccessHandleAdapter;
import com.gtmap.wechat.support.common.WeChatMessageParser;
import com.gtmap.wechat.support.entity.WeChatMessage;

@Component
@Scope("singleton")
public class GlobalEntranceFilter<T> implements Filter{ 
	private final Logger LOGGER = Logger.getLogger(GlobalEntranceFilter.class);
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		//LOGGER.info("过滤器GlobalEntranceFilter启动");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8"); 
		String echostr = request.getParameter("echostr");
		if(echostr!=null){
			if(MessageIdentifierHelper.isFromWeChatServer(httpServletRequest)){
				try {
					OutputStream os = response.getOutputStream();
					BufferedWriter bufferedWriter = new BufferedWriter(
							new OutputStreamWriter(os));
					bufferedWriter.write(echostr);
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch (IOException e) {
					//LOGGER.error("返回消息发生异常。",e);
				}
				return;
			}
		}
		//鉴别身份加密字符串
		if(MessageIdentifierHelper.isFromWeChatServer(httpServletRequest)){
			WeChatMessage ottMessage = null;
			try {
				//[3]身份鉴定通过，解析请求信息
				ottMessage = WeChatMessageParser.parse(httpServletRequest);
				if(ottMessage != null){
					//[4]解析请求信息成功，分发请求到微信业务适配器
					WeChatAccessHandleAdapter.handle(ottMessage,httpServletRequest, httpServletResponse);
				}
			} catch (Exception e) {
				//LOGGER.error("分析消息报文发生异常。",e);
			}
		}else{
			//鉴别访问客户端是否为微信
//			if(!MessageIdentifierHelper.identifyUserAgent(httpServletRequest)){
//				try {
//					HttpResponseUtil.response(httpServletResponse, BaseContants.CONTENT_TYPE_JSON, "不支持在非微信浏览器中打开");
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return;
//			}else{
//				chain.doFilter(httpServletRequest, httpServletResponse);
//			}
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		
	}

}
