package com.hong.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

//JSON 核心处理器
public class JsonProcessor {
	public static final ObjectMapper JsonMapper = new ObjectMapper();
	
	public static ObjectMapper getObjectMapper() {
		return JsonMapper;
	}
	 
}


