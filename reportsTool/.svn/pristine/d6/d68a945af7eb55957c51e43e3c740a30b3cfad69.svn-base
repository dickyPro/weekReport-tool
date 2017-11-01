package com.gtmap.platform.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import sun.net.www.ParseUtil;

@SuppressWarnings("restriction")
public class PropConfig {
	private Properties prop = new Properties();
	private Map<String,String> preProp=new HashMap<String,String>(); 
	private String fileName;
	
	/**
	 * 实例化PropConfig对象的静态方法，propFile文件全路径名
	 * @param  propFile 相对路径文件名
	 * @return PropConfig
	 */
    public static PropConfig loadConfig(String propFile){
    	PropConfig config=new PropConfig();
    	if(config.load(propFile)){
    		return config;
    	}else{
    		return null;
    	}
    }
    /**
	 * 实例化PropConfig对象的静态方法，propPathFile系统绝对路径名
	 * @param  propPathFile 绝对路径文件名
	 * @return PropConfig
	 */
    public static PropConfig loadAbsolutePathConfig(String propPathFile){
    	PropConfig config=new PropConfig();
    	if(config.loadAbsolutePath(propPathFile)){
    		return config;
    	}else{
    		return null;
    	}
    }
    /**
	 * 加载文件，propFile为文件全路径名
	 * @param  propFile 相对路径文件名
	 * @return boolean
	 */
    public boolean load(String propFile)
    {
    	InputStream in=null;
    	try {
    		ClassLoader loader =this.getClass().getClassLoader();
    		in = loader.getResourceAsStream(propFile);
			prop.load(in);
			Set<?> set=prop.keySet();
			preProp.clear();
			Iterator<?> it=set.iterator();
			while(it.hasNext()){
				String entity=(String)it.next();
				String value=prop.getProperty(entity);
				preProp.put(entity, value);
			}
			this.fileName=propFile;
			return true;
		} catch (Exception e) {
			return false;
		}
		finally
		{
			if(null != in)
			{
				try {
					in.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
    }
    /**
	 * 加载文件，propPathFile为系统绝对路径名
	 * @param  propPathFile  绝对路径文件名
	 * @return boolean
	 */
    public boolean loadAbsolutePath(String propPathFile)
    {
    	InputStream in=null;
    	try {
//    		ClassLoader loader =this.getClass().getClassLoader();
    		in =ClassLoader.getSystemResourceAsStream(propPathFile);
			prop.load(in);
			this.fileName=propPathFile;
			return true;
		} catch (Exception e) {
			return false;
		}
		finally
		{
			if(null != in)
			{
				try {
					in.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
    }
    /**
	 * 设置临时键值对，不保存到文件中
	 * @param  key 健
	 * @param  value 值
	 * @throws java.lang.NullPointerException
     */
    public void setConfig(String key,String value){
    	if(null != prop)
    	{
    		prop.setProperty(key, value);
    	}
	}
    /**
	 * 获得保存解析文件键值对的Properties对象
	 * @return Properties
	 */
    public Properties getProp(){
    	return prop;
    }
    /**
     *  将修改或新增的键值对保存到文件中
     * @param key 健
     * @param value 值
     * @return boolean
     */
    public boolean storeConfig(String key,String value){
    	OutputStream out=null;
    	try {
    		ClassLoader loader =this.getClass().getClassLoader();
    		URL url = loader.getResource(fileName);
            String s1 = ParseUtil.decode(url.getPath());
            s1 = s1.replace('/', File.separatorChar);
            s1 = s1.replace('|', ':');
            File file=new File(s1);
    		out =new FileOutputStream(file);
			
			if(prop.getProperty(key)!=null){
				prop.remove(key);
			}
			prop.setProperty(key, value);
			
			prop.store(out, "changed file");
			
			return true;
		} catch (Exception e) {
			return false;
		}
		finally
		{
			if(null != out)
			{
				try {
					out.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
    }
    /**
	 * 获得键entry对应的值，若无值，默认放回defValue
	 * @param  entry 健
	 * @param  defValue 默认值
	 * @return String
	 */
    public String getConfig(String entry, String defValue)
    {
       return prop.getProperty(entry, defValue);
    }
    /**
	 * 获得键entry对应的值，若无值，默认放回null
	 * @param  entry 健
	 * @return String
	 */
    public String getConfig(String entry)
    {
        return getConfig(entry, null);
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为int类型，若无值，默认放回defValue
	 * @param  entry 健
	 * @param  defValue 默认值
	 * @return int
	 */
    public int getConfigInt(String entry, int defValue)
    {
        String value = getConfig(entry);
        if(value != null && !value.equals("")){
        	 return Integer.parseInt(value);
        }     
        return defValue;
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为int类型，若无值，默认放回0
	 * @param  entry 健
	 * @return int
	 */
    public int getConfigInt(String entry)
    {
        return getConfigInt(entry, 0);
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为long类型，若无值，默认放回defValue
	 * @param  entry 健
	 * @param  defValue 默认值
	 * @return long
	 */
    public long getConfigLong(String entry, long defValue)
    {
        String value = getConfig(entry);
        if(value != null&& !value.equals(""))
            return Long.parseLong(value);

        return defValue;
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为long类型，若无值，默认放回0L
	 * @param  entry 健
	 * @return long 
	 */
    public long getConfigLong(String entry)
    {
        return getConfigLong(entry, 0L);
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为float类型，若无值，默认放回defValue
	 * @param  entry 健
	 * @param  defValue 默认值
	 * @return float
	 */
    public float getConfigFloat(String entry, float defValue)
    {
        String value = getConfig(entry);
        if(value != null&& !value.equals(""))
            return Float.parseFloat(value);

        return defValue;
    } 
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为float类型，若无值，默认放回0.0F
	 * @param  entry 健
	 * @return float
	 */
    public float getConfigFloat(String entry)
    {
        return getConfigFloat(entry, 0.0F);
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为double类型，若无值，默认放回defValue
	 * @param  entry 健
	 * @param  defValue 默认值
	 * @return double
	 */
    public double getConfigDouble(String entry,double defValue){
    	 String value = getConfig(entry);
         if(value != null&& !value.equals("")){
             return Double.parseDouble(value);
         }    
         return defValue;
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为double类型，若无值，默认放回0.0D
	 * @param  entry 健
	 * @return double
	 */
    public double getConfigDouble(String entry){
    	return getConfigDouble(entry,0.0D);
    } 
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为boolean类型，若无值，默认放回defValue
	 * @param  entry 健
	 * @param  defValue 默认值
	 * @return boolean
	 */
    public boolean getConfigBoolean(String entry, boolean defValue)
    {
        String value = getConfig(entry);
        if(value == null&& !"".equals(value))
            return defValue;
        return value.equals("1") || value.equalsIgnoreCase("true");
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为boolean类型，若无值，默认放回false
	 * @param  entry 健
	 * @return boolean
	 */
    public boolean getConfigBoolean(String entry)
    {
        return getConfigBoolean(entry, false);
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为Date类型，若无值，默认返回defValue,配置文件中的格式为: key=2004-05-14
	 * @param  entry 健
	 * @param  format 返回时间的格式　如：yyyy-MM-dd
	 * @param  defValue 默认值
	 * @return java.sql.Date
	 */
    public java.sql.Date getConfigDate(String entry,String format,java.sql.Date defValue){
    	if(format==null||"".equals(format))format="yyyy-MM-dd";
    	String value=getConfig(entry);
    	if(value!=null&& !value.equals("")){
    		Date ddate=null;
    		try{
    		    SimpleDateFormat sbf =new SimpleDateFormat(format);
    		    ddate = sbf.parse(value);
    		    }
    			catch (Exception ex) { }
    		return new java.sql.Date(ddate.getTime());
    	}
    	return  defValue;
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为Date类型，若无值，默认返回"1970-1-1"
	 * @param  entry 健
	 * @param  format 返回时间的格式　如：yyyy-MM-dd
	 * @return java.sql.Date
	 */
    public java.sql.Date getConfigDate(String entry,String format)throws Exception{
    	return getConfigDate(entry,format,new java.sql.Date(0));
    } 
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为Date类型，若无值，默认返回defValue,配置文件中的格式为: key=2004-05-14 21:29:51
	 * @param  entry 健
	 * @param  format 返回时间的格式　如：yyyy-MM-dd kk:mm:ss
	 * @param  defValue 默认值
	 * @return Timestamp
	 */
    public Timestamp getConfigTimestamp(String entry,String format,Timestamp defValue){
    	if(format==null||"".equals(format))format="yyyy-MM-dd kk:mm:ss";
    	String value=getConfig(entry);
    	if(value!=null&& !value.equals("")){
    		Date ddate=null;
    		try{
    		    SimpleDateFormat sbf =new SimpleDateFormat(format);
    		    ddate = sbf.parse(value);
    		    }
    			catch (Exception ex) { }
    		return new Timestamp(ddate.getTime());
    	}
    	return  defValue;
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为Date类型，若无值，默认返回"1970-1-1 00:00:00"
	 * @param  entry 健
	 * @param  format 返回时间的格式　如：yyyy-MM-dd kk:mm:ss
	 * @return Timestamp
	 */
    public Timestamp getConfigTimestamp(String entry,String format){
    	return getConfigTimestamp(entry,format,new Timestamp(0));
    } 
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为Date类型，若无值，默认返回defValue,配置文件中的格式为: key=21:29:51
	 * @param  entry 健
	 * @param  format 返回时间的格式　如：kk:mm:ss
	 * @param  defValue 默认值
	 * @return Time
	 */
    public Time getConfigTime(String entry,String format,Time defValue){
    	if(format==null||"".equals(format))format="kk:mm:ss";
    	String value=getConfig(entry);
    	if(value!=null&& !value.equals("")){
    		Date ddate=null;
    		try{
    		    SimpleDateFormat sbf =new SimpleDateFormat(format);
    		    ddate = sbf.parse(value);
    		    }
    			catch (Exception ex) { }
    		
    		return new Time(ddate.getTime());
    	}
    	return  defValue;
    }
    /**
	 * 获得键entry对应的值，将键entry对应的值转换为Date类型，若无值，默认返回"00:00:00"
	 * @param  entry 健
	 * @param  format 返回时间的格式　如：kk:mm:ss
	 * @return Time
	 */
    public Time getConfigTime(String entry,String format){
    	return getConfigTime(entry,format,new Time(0));
    } 
    /**
	 * 判断目前的健值对与解析时的初始健值对是否发生变化
	 * @return boolean
	 */
    public boolean isConfigChanged(){
    	//比较preProp和prop中值是否相同
    	Set<?> s=preProp.keySet();
    	Iterator<?> it=s.iterator();
    	while(it.hasNext()){
    		String key=(String)it.next();
    		if(isConfigChanged(key))return true;
    	}
    	return false;
    }
    /**
	 * 判断目前的entity健值对与解析时的初始健值对是否发生变化
	 * @return boolean
	 */
    public boolean isConfigChanged(String entity){
    	//比较preProp和prop中值是否相同
    		String value=prop.getProperty(entity);
    		String valuemap=(String)preProp.get(entity);
    		if(value==null&&valuemap==null)return false;
    		if(value==null||valuemap==null)return true;
    		if(value.equals(valuemap)){
    			return false;
    		}else{
    			return true;
    		}
    }
}
