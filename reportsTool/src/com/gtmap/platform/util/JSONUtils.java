package com.gtmap.platform.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 后台数据转换成JSON操作的一些方法
 * 
 */
@SuppressWarnings("unchecked")
public class JSONUtils {

	private static final String AJAX_FLAG = "_ajax_op_flag";
	private static final String AJAX_MSG = "_ajax_op_msg";
	private static final String AJAX_DATA = "_ajax_data";

	/**
	 * json日期转换配置
	 */
	private static JsonConfig config = new JsonConfig();

	static {
		config.registerJsonValueProcessor(java.sql.Date.class, new JsDateJsonValueProcessorEx("yyyy-MM-dd"));
		
		config.registerJsonValueProcessor(java.sql.Time.class, new JsDateJsonValueProcessorEx("HH:mm:ss"));

		config.registerJsonValueProcessor(java.sql.Timestamp.class, new JsDateJsonValueProcessorEx("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 将返回消息转换为JSON数据格式返回
	 * 
	 * @param map 转换的对象 必须是List类型或Map类型的对象
	 * @param flag 成功失败标识
	 * @param msg  成功失败信息
	 * @return JSON格式的操作信息
	 */
	public static String operationMsgToJSON(Object object, String flag, String msg) {
		if (object instanceof Map) {
			Map map = new HashMap(0);
			map.put(JSONUtils.AJAX_FLAG, flag);
			map.put(JSONUtils.AJAX_DATA, object);
			map.put(JSONUtils.AJAX_MSG, msg);
			return convertMapToJSON(map);
		} else if (object instanceof List) {
			List list=(List)object;
			Map map = new HashMap(0);
			map.put(JSONUtils.AJAX_FLAG, flag);
			map.put(JSONUtils.AJAX_DATA, list);
			map.put(JSONUtils.AJAX_MSG, msg);
			return convertMapToJSON(map);
		} else {
			//UNDO
			return "";
		}

	}
	
	/**
	 * 将Map转json
	 * @param Map 
	 * @return jsonStr:JSON字符
	 */
	public static String convertMapToJSON(Map map){
		JSONObject jo = JSONObject.fromObject(map, config);	
		if(null != jo){
			return jo.toString();
		}
		return "{}";
	}
	
	/**
	 * 将List 转json
	 * @param list
	 * @return
	*/
	public static String convertListToJSONStr(List list){
		
		if(list==null){
			return "[]";
		}
		JSONArray ja = JSONArray.fromObject(list, config);
		return  ja.toString();
	} 
	
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 将Object转为JSON
	 * @param obj
	 * @return
	 * @throws java.lang.RuntimeException
     */
	public static String Object2JSON(Object obj) {
		if(obj == null){
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, obj);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}
	
	/**
	 * 将JOSN转为Object
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJSON(String json, Class<T> clazz) {
		if(json == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
