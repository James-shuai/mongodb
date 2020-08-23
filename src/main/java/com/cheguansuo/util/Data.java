package com.cheguansuo.util;

import java.util.HashMap;

/**
 * 无限连击map
 * 
 * @author makangming
 * @date 2017-11-23
 * 
 */
public class Data extends HashMap {
	

	public Data put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
