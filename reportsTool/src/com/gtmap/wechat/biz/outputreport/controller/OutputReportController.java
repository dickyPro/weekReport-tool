package com.gtmap.wechat.biz.outputreport.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gtmap.platform.contant.BaseContants;
import com.gtmap.platform.controller.BaseController;
import com.gtmap.platform.util.HttpResponseUtil;
import com.gtmap.platform.util.JSONUtils;
import com.gtmap.wechat.biz.outputreport.service.IOutputReportService;
import com.gtmap.wechat.biz.outputreport.vo.settingConfig;



@Controller
@Scope(value="prototype")
@RequestMapping("/outputReport")
public class OutputReportController <T> extends BaseController<T> {
    
	/*
     *注入service服务 
     */
    
	@Autowired 
    @Qualifier("bean_OutputReportService")
	private IOutputReportService bean_outputReportService;
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/outputReportsInfo")
    public void outputReport() throws Exception{
    	if(request!=null){
    	    
    		String tilte=request.getParameter("seltime");
    		if(tilte==null){
    		  return;
    		}
    		boolean isture=bean_outputReportService.outputReportToExcel(tilte.trim());
			if(isture)
			{

				String filePath=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"../../outputfile/weekreportFile.xls";				           
                downLoad(filePath,response,false);
                
			} 
				
    	}
    }
    
    public void downLoad(String filePath, HttpServletResponse response,boolean isOnLine) throws Exception {  
        File f = new File(filePath);  
        /*  
         * if (!f.exists()) { response.sendError(404, "File not found!");  
         * return; }  
         */  
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));  
        byte[] buf = new byte[1024];  
        int len = 0;  
        response.reset(); // 非常重要  
        //在线打开方式  
        if (isOnLine) {  
            URL u = new URL(filePath); 
             
            response.setContentType(u.openConnection().getContentType());  
            response.setHeader("Content-Disposition", "inline; filename="+ toUTF8(f.getName()));  
            // 文件名应该编码成UTF-8  
        }  
        // 纯下载方式  
        else {  
            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                    + toUTF8(f.getName()));  
        }  
        OutputStream out = response.getOutputStream();  
        while ((len = br.read(buf)) > 0)  
            out.write(buf, 0, len);  
        out.flush();  
        br.close();  
        out.close();  
    }  
    
    public String toUTF8(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
    
    
    
    
  
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/queryReportTimes")
    public void queryReportTimes() throws Exception{
    	
      try {
		
		List<String> list=bean_outputReportService.queryReportTimes();
		String info= JSONUtils.operationMsgToJSON(list, BaseContants.SUCCESS,BaseContants.RET_MSG);	   
		HttpResponseUtil.response(response, BaseContants.CONTENT_TYPE_JSON,info);
	  } catch (Exception e) {
		// TODO Auto-generated catch block
	  }
    }
	
	
}
