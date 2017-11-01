package com.gtmap.wechat.biz.uploadAttachment.controller;

import java.io.File;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gtmap.platform.contant.BaseContants;
import com.gtmap.platform.controller.BaseController;
import com.gtmap.platform.util.HttpResponseUtil;
import com.gtmap.platform.util.JSONUtils;
import com.jspsmart.upload.SmartUpload;


@Controller
@Scope(value="prototype")
@RequestMapping("/uploadAttachment")
public class uploadAttachement <T> extends BaseController<T>{

	
    /**
     * 上传附件
     */
	@SuppressWarnings({ "unchecked", "deprecation" })
    @RequestMapping(value="/uploaddata")
	public void upload() throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");	
		//获取附件存储路径
		String path=request.getSession().getServletContext().getRealPath("/")+"upload";	
		File file=new File(path);
		if(!file.exists()){
			file.mkdir();
		}	
		String resultinfo="上传成功";	
		SmartUpload su = new SmartUpload();
		try {
			su.initialize(request.getSession().getServletContext(),request.getSession(),request, response, null);
			// 限制每个上传文件的最大长度。
			su.setMaxFileSize(1024*1024*100);
			// 限制总上传数据的长度。
			su.setTotalMaxFileSize(1024*1024*500);
			// 设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
			su.setAllowedFilesList("doc,txt,jpg,pdf");
			// 设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,sp,htm,html扩展名的文件和没有扩展名的文件。
			//su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
			// 上传文件
			su.upload();
			// 将上传文件全部保存到指定目录
			int count = su.save(path);
			System.out.print("共上传了"+count+"个文件");
			
		} catch (Exception e) {
			resultinfo="上传失败！";
			if(e.getMessage().indexOf("1015")!=-1){
				resultinfo="上传失败：上传类型不正确！";
			}else if(e.getMessage().indexOf("1010")!=-1){			
				resultinfo="上传失败:上传文件类型不正确";
			}else if(e.getMessage().indexOf("1105")!=-1){			
				resultinfo="上传失败：上传文件超过单个文件最大值";
			}else if(e.getMessage().indexOf("1110")!=-1){			
				resultinfo="上传失败：上传文件总大小超出文件最大值";
			}
			e.printStackTrace();
		}
		
		//String info= JSONUtils.operationMsgToJSON(resultinfo, BaseContants.SUCCESS,BaseContants.RET_MSG);	   
	    //HttpResponseUtil.response(response, BaseContants.CONTENT_TYPE_JSON,info);
        request.getRequestDispatcher("/html/uploadAttachment.html").forward(request, response);
	}
}
