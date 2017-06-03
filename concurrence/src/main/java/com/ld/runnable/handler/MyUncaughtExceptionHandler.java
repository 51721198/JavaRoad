package com.ld.runnable.handler;

import java.util.concurrent.ThreadFactory;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("i got the problem: "+e);
	}
}