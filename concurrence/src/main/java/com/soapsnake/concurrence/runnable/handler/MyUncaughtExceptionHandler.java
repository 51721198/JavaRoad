package com.soapsnake.concurrence.runnable.handler;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("i got the problem: " + e);
    }
}
