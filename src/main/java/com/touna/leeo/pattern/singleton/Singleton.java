package com.touna.leeo.pattern.singleton;

public class Singleton {
	
	private Singleton(){};
	
	private static class HolderClass{
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance(){
		return HolderClass.instance;
	}
	
}
