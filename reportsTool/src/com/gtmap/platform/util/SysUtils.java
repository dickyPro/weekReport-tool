package com.gtmap.platform.util;

import javax.servlet.http.HttpServletRequest;

/** 
 * �����ƣ�SysUtils
 * ���������ۺϹ�����
 * �����ˣ�byj
 * ����ʱ�䣺2014-12-23
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
 * @version 1.0 
 */
public class SysUtils {
	/**
	 * ��ȡϵͳ��·��
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
