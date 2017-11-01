package com.gtmap.wechat.biz.outputreport.vo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;



/**
 * excel工具类
 * @author hetao
 *
 */
public class ExportExcel {
	
	private final int lastWeekContentIndex=1;  //Excel上周工作内容列索引
	private final int nameIndex=2;  //Excel姓名列索引
	private final int progressIndex=3;
	//private final int planStartTime=3;
	//private final int planEndTime=4;
	//private final int planDays=5;
	
	
	public HSSFWorkbook wb = null;
	public HSSFSheet sheet = null;
	
	//excel内容
	public List<Map<String,Object>> sInfo=null;
	
	public ExportExcel(){
					
	}
	
	public void FillExcelContent(String param){
		
		//更改表头
		HSSFCell weektitleCell= sheet.getRow(0).getCell(1);
		weektitleCell.setCellValue(param);
			
		for(int i=0;i<=sInfo.size()-1;i++)
		{
			String sName= sInfo.get(i).get("NAME").toString().trim();
			List<Integer> indexList=queryRowsInExcel(sName);
			if(indexList.size()!=2)
			{
				continue;
			}
			//上周工作情况
			HSSFRow pLastWeekRow= sheet.getRow(indexList.get(0));
			if(pLastWeekRow!=null)
			{
			   Object oLastweek=sInfo.get(i).get("LASTWEEKCONTENT");
			   String sLastweek="";
			   if(oLastweek!=null)
			   {
				  sLastweek=(String)oLastweek;
			   }
			   if(pLastWeekRow.getCell(lastWeekContentIndex)!=null)
			   {
			      pLastWeekRow.getCell(lastWeekContentIndex).setCellValue(sLastweek);	
			   }
			   Object oLastweekpro=sInfo.get(i).get("LASTWEEKPROGRESS");
			   String sLastweekpro="";
			   if(oLastweekpro!=null)
			   {
				  sLastweekpro=(String)oLastweekpro;
			   }
			   if(pLastWeekRow.getCell(progressIndex)!=null)
			   {
			     pLastWeekRow.getCell(progressIndex).setCellValue(sLastweekpro);	
			   }
			}
			
			//下周工作情况
			HSSFRow pNextWeekRow= sheet.getRow(indexList.get(1));
			if(pNextWeekRow!=null)
			{
			   Object oNextweek=sInfo.get(i).get("NEXTWEEKCONTENT");
			   String sNextweek="";
			   if(oNextweek!=null)
			   {
				   sNextweek=(String)oNextweek;
			   }
			   if(pNextWeekRow.getCell(lastWeekContentIndex)!=null)
			   {
			      pNextWeekRow.getCell(lastWeekContentIndex).setCellValue(sNextweek);
			   }
			
			}
			
		}
	    	
		
		
	}
	
	
	/**
	 * 根据姓名查找Excel中的行号
	 * @param name
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private List<Integer> queryRowsInExcel(String name){
		
		 List<Integer> pResultlist = new ArrayList<Integer>();
		 int rowNum = sheet.getPhysicalNumberOfRows();
		 int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
		 for(int j=0;j<rowNum;j++)
		 {
		     HSSFRow row = sheet.getRow(j);
		     if(row==null)
		     {
		    	 continue;
		     }
		     HSSFCell cell = row.getCell((short)nameIndex);
		     if(cell==null)
		     {
		    	 continue;
		     }
		     String cellvalue="";   
		     if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		     {
		    	 cellvalue=cell.getStringCellValue();
		     } 
		     if(cellvalue=="")
		     {
		       continue;
		     }		     		     
		     if(cellvalue.trim().equals(name))
		     {
		    	pResultlist.add(j);
		     }
		 }
		return pResultlist;
	}
	
	
		
	/**
	 * 输出EXCEL文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void outputExcel(String fileName) {
	
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(fileName));
			wb.write(fos);
			wb.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}

		
}
