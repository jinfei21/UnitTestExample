package com.finix.test.util;

import java.util.concurrent.TimeUnit;

public class DefaultValueOperations implements ValueOperations {

	
	public DefaultValueOperations(String key){
		
	}
	
	public void set(String value, long timeout, TimeUnit unit) {
		System.out.println(value);
	}

	public void set(String value) {
		System.out.println(value);
	}
	
	public String get(){
		return "OK";
	}
}
