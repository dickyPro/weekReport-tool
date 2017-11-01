package com.gtmap.wechat.biz.outputreport.service.impl;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gtmap.wechat.biz.outputreport.dao.IOutputReportDao;
import com.gtmap.wechat.biz.outputreport.service.IOutputReportService;
import com.gtmap.wechat.biz.outputreport.vo.ExportExcel;

@Service("bean_OutputReportService")
public class OutputReportService implements IOutputReportService {


	//注入dao服务
	@Autowired 
	@Qualifier("bean_outputReportsDao")
    private IOutputReportDao beanOutputReportDao;

	
	/**
	 * 输出到Excel
	 */
	public boolean outputReportToExcel(String params) throws Exception {
		// TODO Auto-generated method stub
		
		//目标存储路径
		String sPath=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"../../outputfile/weekreportFile.xls";		
		//模板路径
		String sTemplePath=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"../../excelModel/weekreportFile.xls";       
		List<Map<String,Object>> lInfo= beanOutputReportDao.queryReportInfo(params);		
			
	    try {
	    	
	           // 打开HSSFWorkbook
	            FileInputStream fin = new FileInputStream(sTemplePath);
	            POIFSFileSystem fs = new POIFSFileSystem(fin);
	            HSSFWorkbook wb = new HSSFWorkbook(fs);	    	
				HSSFSheet sheet = wb.getSheetAt(0);	
				
				ExportExcel excelinfo=new ExportExcel();
				excelinfo.wb=wb;
				excelinfo.sheet=sheet;
				excelinfo.sInfo=lInfo;
				excelinfo.FillExcelContent(params);								
				excelinfo.outputExcel(sPath);
				fin.close();
	    } catch (Exception e) {
				// TODO: handle exception
	    	    System.out.println(e.toString());
				return false;
	    }
			
		return true;
	}


	public List<String> queryReportTimes() throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=beanOutputReportDao.queryReportTimeInfo();
		
		List<String> resultList=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
		 String time=list.get(i).get("TIME").toString().trim();
		 resultList.add(time);
		}
		return resultList;
	}


}
