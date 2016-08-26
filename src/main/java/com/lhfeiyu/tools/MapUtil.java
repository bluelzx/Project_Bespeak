package com.lhfeiyu.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map相关工具类
 * @author 荣华
 */
public class MapUtil {

	public static void printMap(Map<String, ?> map){
		if(null == map){
			System.out.println("map为空");return;
		}
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = map.get(key);
			if(null != value){
				System.out.println(key+":"+value);
			}
		}
	}
	
}
