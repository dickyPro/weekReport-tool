package com.gtmap.wechat.biz.outputreport.dao.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gtmap.platform.jdbc.JdbcDaoSupportEx;
import com.gtmap.wechat.biz.outputreport.dao.IOutputReportDao;
import com.gtmap.wechat.biz.outputreport.vo.settingConfig;


@Repository("bean_outputReportsDao")
public class OutputReportDaoImpl implements IOutputReportDao {

	@Autowired 
	@Qualifier("jdbcSupport_weekreport")
	private JdbcDaoSupportEx jdbcSupport_weekreport;
	
	public List<Map<String, Object>> queryReportInfo(String params) throws Exception {
		// TODO Auto-generated method stub
	
		  StringBuffer sb=new StringBuffer();
		  sb.append("select t.NAME,t.LASTWEEKCONTENT, t.LASTWEEKPROGRESS ,t.NEXTWEEKCONTENT from WEEKREPORT t where t.TIME='"+params+"'");	  
		  return this.jdbcSupport_weekreport.jdbcTemplateEx.queryForList(sb.toString());		
			
	}

	public List<Map<String, Object>> queryReportTimeInfo() throws Exception {
		// TODO Auto-generated method stub
		  StringBuffer sb=new StringBuffer();
		  sb.append("SELECT DISTINCT TIME FROM WEEKREPORT");	  
		  return this.jdbcSupport_weekreport.jdbcTemplateEx.queryForList(sb.toString());	
	}
}