package com.gtmap.wechat.biz.weekreport.service;

import java.util.List;
import java.util.Map;

public interface IWeekReportService {
	/**
	 * 获取周报信息
	 * @param params
	 * @throws Exception
	 */
	public boolean getWeekreportInfo(Map<String,String> params) throws Exception;
	
	public List<Map<String, Object>> getreportPersonsInfo() throws Exception;
	
	public boolean setHistoryData() throws Exception;
	
	public void addNewweekPersons(List<Map<String,Object>> list,String time) throws Exception;
	
	public List<String> queryNullsbmit()throws Exception;
}
