package com.gtmap.wechat.support.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeHelper {
	/**
	 * 从list中组织树形结构
	 * @param list
	 * @param pid
	 * @param id
	 * @return
	 */
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String,Object>> format(List<Map<String,Object>> menus,Object pid,Object id){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.addAll(menus);
		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
		for (int i = 0;i <= list.size() - 1; i++) {
			Map<String,Object> map = list.get(i);
			if(map.get(pid) == null){
				newList.add(map);
				list.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < newList.size(); i++) {
			Map<String,Object> map = newList.get(i);
			List<Map<String,Object>> subMenus = new ArrayList<Map<String,Object>>();
			for (int j = 0; j <= list.size()-1 ; j++) {
				Map<String,Object> tmpMenu = list.get(j);
				if(tmpMenu.get(pid).equals(map.get(id))){
					subMenus.add(tmpMenu);
					list.remove(j);
					j--;
				}
			}
			map.put("sub_button", subMenus);
		}
		return newList;
	}
}
