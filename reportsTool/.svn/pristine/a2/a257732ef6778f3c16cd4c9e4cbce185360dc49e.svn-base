package com.gtmap.wechat.biz.weekreport.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtmap.platform.jdbc.JdbcDaoSupportEx;
import com.gtmap.wechat.biz.weekreport.dao.IWeekReportDao;

@Service("bean_reciveDao")
public class WeekReportDaoImpl implements  IWeekReportDao {
	
	@Autowired 
	@Qualifier("jdbcSupport_weekreport")
	private JdbcDaoSupportEx jdbcSupport_weekreport;

	/**
	 * 保存周报信息
	 */
	public void saveWeekreportInfo(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub	
		StringBuffer sql = new StringBuffer();
        sql.append("update WEEKREPORT set");
        sql.append(" LASTWEEKCONTENT="+"'"+params.get("lastWeekContent")+"',");     
        sql.append(" LASTWEEKPROGRESS="+"'"+params.get("lastWeekProgress")+"',");          
        sql.append(" NEXTWEEKCONTENT="+"'"+params.get("nextWeekContent")+"'");
        sql.append(" where NAME="+"'"+params.get("name")+"'");
        sql.append(" and ISHISTORY='1'");
        jdbcSupport_weekreport.jdbcTemplateEx.update(sql.toString());
   
	}

	public List<Map<String, Object>> getreportPersonsInfo() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("Select t.NAME,t.GRADE ,t.CLASS FROM PERSON t where t.GRADE !='毕业'");		
		return this.jdbcSupport_weekreport.jdbcTemplateEx.queryForList(sql.toString());
	}

	
	/**
	 * 将周报已有数据变为历史数据
	 */
	public Boolean setHistoryData() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("update WEEKREPORT set ISHISTORY='0' where ISHISTORY!='0'");	
		try {
			jdbcSupport_weekreport.jdbcTemplateEx.update(sql.toString());
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
		return true;
	}

	
	

    /**
     * 增加新一周人员记录到数据库表
     */
	public void addNewweekPersonstoDb(List<String> params, String time)
			throws Exception {
		// TODO Auto-generated method stub
		String[] strSql= new String[params.size()];
		for(int i=0;i<params.size();i++){
			StringBuffer sql = new StringBuffer();
	        sql.append("insert into WEEKREPORT values ('");
	        sql.append(params.get(i).trim()+"', ");
	        sql.append("'','','','','");
	        sql.append(time+"',");
	        sql.append("'1')");        
	        strSql[i]=sql.toString();
		}
		 jdbcSupport_weekreport.jdbcTemplateEx.batchUpdate(strSql);
		
	}

	public List<Map<String, Object>> queryNullsubmitInfo() throws Exception {
		// TODO Auto-generated method stub
		
		StringBuffer sql=new StringBuffer();
		sql.append("Select t.NAME FROM WEEKREPORT t where t.LASTWEEKCONTENT is null AND t.ISHISTORY='1'");		
		return this.jdbcSupport_weekreport.jdbcTemplateEx.queryForList(sql.toString());	
		
	}

	
	
	
	
	
}
