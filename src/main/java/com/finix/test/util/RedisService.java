package com.finix.test.util;

import org.springframework.stereotype.Component;

@Component
public class RedisService {

	
	public ValueOperations boundValueOps(String key) {
		return new DefaultValueOperations(key);
	}
	
	
}
