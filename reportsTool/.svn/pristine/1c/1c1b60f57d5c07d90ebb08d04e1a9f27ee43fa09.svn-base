package com.gtmap.wechat.biz.weekreport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtmap.wechat.biz.weekreport.dao.IWeekReportDao;
import com.gtmap.wechat.biz.weekreport.service.IWeekReportService;



@Service("bean_reciveService")
public class WeekReportService implements IWeekReportService {
	
	//×¢Èëdao·þÎñ
	@Autowired 
	@Qualifier("bean_reciveDao")
    private  IWeekReportDao weekReportDao;

	public boolean getWeekreportInfo(Map<String, String> params)
			throws Exception {
		// TODO Auto-generated method stub
		weekReportDao.saveWeekreportInfo(params);
		return true;		
	}

	public List<Map<String, Object>> getreportPersonsInfo() throws Exception {
		// TODO Auto-generated method stub
		 return weekReportDao.getreportPersonsInfo();
	}
	

	public void addNewweekPersons(List<Map<String, Object>> list, String time)throws Exception {
		// TODO Auto-generated method stub
        List<String> listoj = new ArrayList<String>();
		
		for(int i=0;i<list.size();i++){		
			String name=list.get(i).get("NAME").toString().trim();	
			listoj.add(name);
		}
		weekReportDao.addNewweekPersonstoDb(listoj,time);
	}

	public boolean setHistoryData() throws Exception {
		// TODO Auto-generated method stub
		return weekReportDao.setHistoryData();
	}

	public List<String> queryNullsbmit() throws Exception {
		// TODO Auto-generated method stub	
		List<String> nameList=new ArrayList<String>();
		List<Map<String, Object>> list= weekReportDao.queryNullsubmitInfo();
		if(list==null){
			return null;
		}		
		for(int i=0;i<list.size();i++){
			nameList.add(list.get(i).get("NAME").toString().trim());				
		}
		return nameList;	
	}



}