package com.lhfeiyu.util.base.dust;

import java.util.ResourceBundle;

public class PropertiesUtil {
	public static String getValue(String name, String key) {
		ResourceBundle rb = ResourceBundle.getBundle(name);
		return rb.getString(key);
	}
}
