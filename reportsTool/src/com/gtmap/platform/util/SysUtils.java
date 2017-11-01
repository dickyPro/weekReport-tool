package com.gtmap.platform.util;

import javax.servlet.http.HttpServletRequest;

/** 
 * 类名称：SysUtils
 * 类描述：综合工具类
 * 创建人：byj
 * 创建时间：2014-12-23
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 1.0 
 */
public class SysUtils {
	/**
	 * 获取系统根路径
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request){
//		String host = SysConfig.getConfig("HOST");
//		if(!StringUtil.isEmpty(host)){
//			return host;
//		}
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
}
