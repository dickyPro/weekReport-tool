package com.gtmap.wechat.biz.weekreport.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gtmap.platform.contant.BaseContants;
import com.gtmap.platform.controller.BaseController;
import com.gtmap.platform.util.HttpResponseUtil;
import com.gtmap.platform.util.JSONUtils;
import com.gtmap.wechat.biz.outputreport.vo.settingConfig;
import com.gtmap.wechat.biz.weekreport.service.IWeekReportService;




@Controller
@Scope(value="prototype")
@RequestMapping("/weekreport")
public class WeekReportController<T> extends BaseController<T> {
	/*
     *注入service服务 
     */
    @Autowired 
    @Qualifier("bean_reciveService")
    private IWeekReportService weekreportService;
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/getweekReportInfo")
	public ModelAndView GetweekReportInfo() throws Exception{
		if(request!=null){
		
			Map<String, String> params = new HashMap<String, String>();
			params.put("name", request.getParameter("cc").toString().trim());				
			params.put("lastWeekContent",request.getParameter("lastWeekReport").toString());
			params.put("lastWeekProgress",request.getParameter("lastWeekProgress").toString());
			params.put("nextWeekContent",request.getParameter("nextWeekReport").toString());		
			//params.put("bzInfo", request.getParameter(""));
			boolean issucess= weekreportService.getWeekreportInfo(params);
			if(issucess==true)
			{
				    Map<String,String> maps=new HashMap<String,String>();
					maps.put("TITLE", "周报提交");
					String msg="提交成功!快去找小伙伴愉快的玩耍吧";
					maps.put("MSG", msg);
					modelAndView.addObject("result", maps);
					modelAndView.setViewName("message4");
			}
			return modelAndView;
		}
		
		return null;
	}
	
    
    /**
     * 查询周报人员信息
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/queryReportPersonsInfo")
	public void queryreportPersonsInfo() throws Exception{
    
	  
	   List<Map<String,String>> listres=new ArrayList<Map<String,String>>();
	   List<Map<String,Object>> list= weekreportService.getreportPersonsInfo();
	   
	   for(int i=0;i<list.size();i++)
	   {
		   Map map =new HashMap();
		   String name= list.get(i).get("NAME").toString().trim();
		   String grade=list.get(i).get("GRADE").toString().trim();
		   map.put("name", name);
		   map.put("grade", grade);
		   listres.add(map);
	   }
	   
	   String info= JSONUtils.operationMsgToJSON(listres, BaseContants.SUCCESS,BaseContants.RET_MSG);	   
	   HttpResponseUtil.response(response, BaseContants.CONTENT_TYPE_JSON,info);
    }
    
    
    /**
     * 发起周报流程
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/startreportProcess")  
    public ModelAndView startreportProcess(){
    	
       try {
		//创建周报标题
		String datestart = request.getParameter("fname").replace("-", "、");
		String dateend = request.getParameter("lname").replace("-", "、");
		String tilte = "技术发展中心周总结与计划(" + datestart + "--" + dateend + ")";
		//保存周报标题到配置信息类
		settingConfig.reportTitle=tilte.trim();
		
		//将已有周报信息数据库记录全部变为历史数据		
		weekreportService.setHistoryData();
		//根据读取的人员信息创建数据库新一周记录
		List<Map<String, Object>> list = weekreportService.getreportPersonsInfo();
		weekreportService.addNewweekPersons(list, tilte.trim());
	} catch (Exception e) {
		// TODO: handle exception
		 Map<String,String> maps=new HashMap<String,String>();
		 maps.put("TITLE", "发起周报流程");
		String msg="发起失败!";
		maps.put("MSG", msg);
		modelAndView.addObject("result", maps);
		modelAndView.setViewName("message4"); 
		
		return null;	
	}
		  
	    Map<String,String> maps=new HashMap<String,String>();
		maps.put("TITLE", "发起周报流程");
		String msg="发起成功!快去通知小伙伴参与周报活动吧";
		maps.put("MSG", msg);
		modelAndView.addObject("result", maps);
		modelAndView.setViewName("message4"); 
		
	   return modelAndView;
	   
    }
    
    /**
     * 查询未提交人员信息
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/queryNullsumbitInfo")  
    public void queryNullsumbitInfo(){
    	
      try {
		
       List<String> list =weekreportService.queryNullsbmit();
		
       String info= JSONUtils.operationMsgToJSON(list, BaseContants.SUCCESS,BaseContants.RET_MSG);	   
   	   HttpResponseUtil.response(response, BaseContants.CONTENT_TYPE_JSON,info);	
		
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    	
    }
}
