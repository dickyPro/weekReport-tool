package com.gtmap.wechat.biz.weekreport.dao;

import java.util.List;
import java.util.Map;

public interface IWeekReportDao {
	
	/**
	 * 保存周报信息
	 * @param params
	 * @throws Exception
	 */
	public void saveWeekreportInfo(Map<String,String>params) throws Exception;
	
	/**
	 * 读取周报人员的信息
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getreportPersonsInfo() throws Exception; 
	
	
	/**
	 * 将周报信息数据变成历史数据
	 * @return
	 * @throws Exception
	 */
	public Boolean setHistoryData() throws Exception;
	
	
	/**
	 * 增加一周人员记录到数据库
	 * @throws Exception
	 */
	public void addNewweekPersonstoDb(List<String> params,String time) throws Exception;
	
	/**
	 * 查询未提交人员的信息
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryNullsubmitInfo() throws Exception;
}
