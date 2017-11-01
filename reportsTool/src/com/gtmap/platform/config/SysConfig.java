package com.gtmap.platform.config;

import java.util.Properties;

public class SysConfig {
	private static Properties CFG_PROPS = null;
	
	/**
	 * º”Ω‚√‹√‹‘ø
	 */
	public static String PASSWORD_CRYPTKEY  = "suypower";	
	
	private static String properties = "/configs/sysconfig.properties";
	
	static {
		try {
			CFG_PROPS = new Properties();
			CFG_PROPS.load(SysConfig.class.getResourceAsStream(properties));
			try{
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("SysConfig: not found sysconfig.properties in CLASSPATH.");
		}
	}
	
	
	public static Properties getProperties(){
		return CFG_PROPS;
	}
	
	public static String getPropValue(String key){
		return CFG_PROPS.getProperty(key);
	}
	
	
	private static PropConfig cfg = init();

	protected static PropConfig init() {
		return PropConfig.loadConfig(properties);
	}

	public static void reloadConfig() {
		cfg = PropConfig.loadConfig(properties);
		
	}
	
	public static void setConfig(String key, String value) {
		if (cfg != null) cfg.setConfig(key, value);
	}

	public static boolean storeConfig(String key, String value) {
		return cfg != null ? cfg.storeConfig(key, value) : false;
	}

	public static String getConfig(String entry, String defValue) {
		return cfg != null&&cfg.getConfig(entry, defValue)!=null ? cfg.getConfig(entry, defValue).trim() : defValue;
	}

	public static String getConfig(String entry) {
		return getConfig(entry, null);
	}

	public static int getConfigInt(String entry, int defValue) {
		return cfg != null ? cfg.getConfigInt(entry, defValue) : defValue;
	}

	public static int getConfigInt(String entry) {
		return getConfigInt(entry, 0);
	}

	public static long getConfigLong(String entry, long defValue) {
		return cfg != null ? cfg.getConfigLong(entry, defValue) : defValue;
	}

	public static long getConfigLong(String entry) {
		return getConfigLong(entry, 0L);
	}

	public static float getConfigFloat(String entry, float defValue) {
		return cfg != null ? cfg.getConfigFloat(entry, defValue) : defValue;
	}

	public static float getConfigFloat(String entry) {
		return getConfigFloat(entry, 0.0F);
	}

	public static boolean getConfigBoolean(String entry, boolean defValue) {
		return cfg != null ? cfg.getConfigBoolean(entry, defValue) : defValue;
	}

	public static boolean getConfigBoolean(String entry) {
		return getConfigBoolean(entry, false);
	}

	public static boolean isConfigChanged() {
		return cfg != null ? cfg.isConfigChanged() : false;
	}
}
