package com.finix.test.util;

import java.util.concurrent.TimeUnit;

public interface ValueOperations {
	void set(String value, long timeout, TimeUnit unit);

	void set(String value);

	String get();
}
