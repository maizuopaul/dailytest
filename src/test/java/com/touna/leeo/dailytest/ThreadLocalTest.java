package com.touna.leeo.dailytest;

public class ThreadLocalTest {
	public static void test(){
		ThreadLocal<String> tl = new ThreadLocal<>();
		tl.set("a");
	}
}
