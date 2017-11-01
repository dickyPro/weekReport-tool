package com.gtmap.platform.util;

import java.util.regex.PatternSyntaxException;


public class StringUtil {

	public static Boolean isEmpty(String str) {
		return str == null || str.length() == 0 || str.toLowerCase().equals("null");
	}

	public static boolean isNullOrEmpty(Object o) {
		return o == null || String.valueOf(o).trim().length() == 0
				|| String.valueOf(o).trim().equals("null");
	}
	
    public static String formatString(String s, String spiltter) throws PatternSyntaxException {
        if (StringUtil.isEmpty(s))
            return "";
        StringBuffer result = new StringBuffer();
        String[] temp = s.split(spiltter);
        for (String str : temp)
        {
            if (str.trim().length() > 0)
                result.append(spiltter).append("'").append(str).append("'");
        }
        
        return result.length() > 0 ? result.substring(1) : result.toString();
    }

    public static String formatString(String s) {
        return formatString(s, ",");
    }

    public static String escapeHtml(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            case 10:
            case 13:
                break;
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        //
        String flt ="'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|-|+|,"; 
    	String[] filter = flt.split("|"); 
    	for (int i = 0; i < filter.length; i++) {
    		if (html.indexOf(filter[i]) != -1) {
    			html.replace(filter[i], "");
    		}
		}
        return html;
    }
}
