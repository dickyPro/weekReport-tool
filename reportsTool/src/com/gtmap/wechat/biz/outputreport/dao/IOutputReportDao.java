package com.gtmap.wechat.biz.outputreport.dao;

import java.util.List;
import java.util.Map;


public interface IOutputReportDao {
   
	 /**
	 * �����ܱ���Ϣ��ѯ
	 * @return
	 */
	public List<Map<String,Object>> queryReportInfo(String params) throws Exception;
	

	/**
	 * ��ѯ�ܱ�����
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryReportTimeInfo() throws Exception;
}
