/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gtmap.platform.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Transforms a java.util.Date property into a JSONObject ideal for JsDate
 * conversion
 *
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class JsDateJsonValueProcessorEx implements JsonValueProcessor {

   //private JsonBeanProcessor processor;
   
   public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";   
   private DateFormat dateFormat;   
  
   /**  
    * 构�?�方�?.  
    *  
    * @param datePattern 日期格式  
    */   
//   public DateJsonValueProcessor(String datePattern) {   
//       try {   
//           dateFormat = new SimpleDateFormat(datePattern);   
//       } catch (Exception ex) {   
//           dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);   
//       }   
//   } 
   

   public JsDateJsonValueProcessorEx(String datePattern) {
     try {   
    	 dateFormat = new SimpleDateFormat(datePattern);   
     } catch (Exception ex) {   
    	 dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN); 
     }
   }
   
//   public JsDateJsonValueProcessor() {
//	      //processor = new JsDateJsonBeanProcessor();
//		   //dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	}   

   public Object processArrayValue( Object value, JsonConfig jsonConfig ) {
      return process( value, jsonConfig );
   }

   public Object processObjectValue( String key, Object value, JsonConfig jsonConfig ) {
      return process( value, jsonConfig );
   }

   private Object process( Object value, JsonConfig jsonConfig ) {
	   //根据value转换为不同格式的字符串yyyy-MM-dd HH:mm:ss
	   //value = new Date( ((java.sql.Date) value).getTime() );
	   
	   return dateFormat.format(value);
	   
      //return processor.processBean( value, jsonConfig );
   }
}